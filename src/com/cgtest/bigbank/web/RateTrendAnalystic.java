package com.cgtest.bigbank.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cgtest.biz.pojo.MonthCumulativeData;
import com.cgtest.biz.pojo.MonthData;
import com.cgtest.biz.pojo.WeekCumulativeData;
import com.cgtest.biz.pojo.WeekData;
import com.cgtest.registration.model.Setting;
import com.cgtest.registration.model.Record.HankouSequence;
import com.cgtest.registration.model.Setting.SettingType;
import com.cgtest.registration.service.RecordManager;
import com.cgtest.registration.service.SettingManager;
import com.cgtest.utils.CalendarUtils;

/**
 * ������ڡ��·��ۼƺϸ�������ͼ
 * @author chairy
 *
 */
@Controller
public class RateTrendAnalystic {

	public static final int PAGE_SIZE = 16; 
	
	@Autowired
	RecordManager recordManager;
	
	@Autowired
    private SettingManager settingManager;
	
	/**
	 * ��������һ�κϸ��������������
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/weekAnalystic.do")
    public String weekAnalystic(HttpServletRequest request, ModelMap model) throws Exception {
		String yearString = request.getParameter("year");
		HankouSequence seq = HankouSequence.FIRST;
		SettingType type = SettingType.WeekAlertLimit;
		getWeekAnalystic(model, yearString, seq, type);
        return "/weekAnalystic/weekAnalystic";
    }

	@RequestMapping("/weekAnalysticSecondRT.do")
    public String weekAnalysticSecondRT(HttpServletRequest request, ModelMap model) throws Exception {
		String yearString = request.getParameter("year");
		HankouSequence seq = HankouSequence.SECOND;
		SettingType type = SettingType.WeekAlertLimitSecondRT;
		getWeekAnalystic(model, yearString, seq, type);
        return "/weekAnalystic/weekAnalysticSecondRT";
    }
	
	private void getWeekAnalystic(ModelMap model, String yearString,
			HankouSequence seq, SettingType type) {
		int year = 0;
    	Calendar cal = Calendar.getInstance();
    	Date now = new Date();
    	if(yearString == null){
    		//���ǰ̨ҳ��δ������year������Ϊ�ǵ�ǰ���
    		year =  cal.get(Calendar.YEAR);
    		cal.set(Calendar.MONTH, 0);
    		cal.set(Calendar.DAY_OF_YEAR, 1);
    	}else{
    		year = Integer.valueOf(yearString);
    		cal.set(year, 0, 1);	
    	}
	    DateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
	    String nowString = dateformat.format(now);
	    Calendar nextweekDate = null;
	    int weekSeq = 1;
	    int latestAlertWeek = 0;
	    int currentWeek=0;
	    List<WeekData> list = new ArrayList<WeekData>();
	    List<WeekCumulativeData> weekSumDataList = new ArrayList<WeekCumulativeData>();
	    Setting setting = settingManager.getUserByUsername(type);
	    int alertWeekNumber =  Integer.valueOf(settingManager.getUserByUsername(SettingType.AlertWeekNumber).getValue());
		
	    long pipianNumberSum = 0;//Ƭ������
		long fanxiuNumberSum = 0;//��������
	    
	    do{
	    	Date weekStartDate = CalendarUtils.getFirstDayOfWeek(cal.getTime());//���ܵ�һ��
	    	cal.setTime(weekStartDate);//calendar�����ɱ��ܵ�һ��
	    	String weekStartDateString = dateformat.format(weekStartDate);
	    	cal.add(Calendar.DAY_OF_MONTH, 6);//�������һ��
	    	Date weekEndDate = cal.getTime();
	    	String weekEndDateString = dateformat.format(weekEndDate);
	    	cal.add(Calendar.DAY_OF_MONTH, 1);//���ܵ�һ��
	    	WeekData data;
	    	WeekCumulativeData weekDataSum = new WeekCumulativeData();
	    	if(nowString.compareTo(weekStartDateString) >= 0){
	    		data = recordManager.getWeekData(weekStartDateString, weekEndDateString, seq);
	    		pipianNumberSum = pipianNumberSum + data.getDipianNumber();
	    		fanxiuNumberSum = fanxiuNumberSum + data.getFanxiuNumber();
	    		weekDataSum.setDipianNumber(pipianNumberSum);
	    		weekDataSum.setFanxiuNumber(fanxiuNumberSum);
	    		if(pipianNumberSum != 0){
	    			weekDataSum
							.setQulifiedRate((pipianNumberSum - fanxiuNumberSum)
									/ (double) pipianNumberSum);	
	    		}else{
	    			weekDataSum.setQulifiedRate(1);	
	    		}
	    		if(data.getQulifiedRate()*100 < Double.valueOf(setting.getValue())){
	    			latestAlertWeek = weekSeq;
	    		}
	    	}else{
	    		if(currentWeek == 0){
	    			currentWeek = weekSeq - 1;
	    		}
	    		data = new WeekData();
	    	}
	    	data.setStartDate(weekStartDateString);
	    	data.setEndDate(weekEndDateString);
	    	data.setWeekSeq(weekSeq);
	    	int monthSeq = weekEndDate.getMonth() + 1;
	    	data.setMonth(monthSeq);
	    	list.add(data);
	    	weekSumDataList.add(weekDataSum);
	    	nextweekDate = (Calendar) cal.clone();
	    	nextweekDate.add(Calendar.DAY_OF_MONTH, 6);
	    	weekSeq ++;
	    }while(nextweekDate.get(Calendar.YEAR) == year);
		model.addAttribute("weekDataList", list);//���ڵ���������
		model.addAttribute("weekSumDataList", weekSumDataList);//���ڵ��ۼ�����
    	model.addAttribute("weekAlertLimit", setting.getValue());//�趨�ľ���ֵ
    	model.addAttribute("year",year);//�����������
    	model.addAttribute("currentWeek", currentWeek == 0 ? weekSeq : currentWeek);
    	//���㵱ǰʱ���Ƿ���Ҫ����,��alertWeekNumber�����ڳ��ֵ��ھ���ֵ�����ݣ����𾯱�
    	model.addAttribute("alert",
				currentWeek - latestAlertWeek <= alertWeekNumber
						&& latestAlertWeek != 0 ? true : false);
	}
	
	/**
	 * �����·�һ�κϸ�������ͼ����
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/monthAnalystic.do")
    public String monthAnalystic(HttpServletRequest request, ModelMap model) throws Exception {
		String yearString = request.getParameter("year");
		HankouSequence seq = HankouSequence.FIRST;
		getMonthAnalysticData(model, yearString, seq);
        return "/monthAnalystic/monthAnalystic";
    }
	
	
	/**
	 * �����·�����ͼ�Ķ��κϸ�������
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/monthAnalysticSecondRT.do")
    public String monthAnalysticSecondRT(HttpServletRequest request, ModelMap model) throws Exception {
		String yearString = request.getParameter("year");
		HankouSequence seq = HankouSequence.SECOND;
		getMonthAnalysticData(model, yearString, seq);
        return "/monthAnalystic/monthAnalysticSecondRT";
    }

	private void getMonthAnalysticData(ModelMap model, String yearString,
			HankouSequence seq) {
		int year = 0;
    	Calendar cal = Calendar.getInstance();
    	Date now = new Date();
    	if(yearString == null){
    		year =  cal.get(Calendar.YEAR);
    		cal.set(Calendar.MONTH, 0);
    		cal.set(Calendar.DAY_OF_YEAR, 1);
    	}else{
    		year = Integer.valueOf(yearString);
    		cal.set(year, 0, 1);	
    	}
	    DateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
	    String nowString = dateformat.format(now);
	    Calendar nextweekDate = null;
	    int weekSeq = 1;
	    int latestAlertWeek = 0;
	    int currentWeek=0;
	    List<WeekData> list = new ArrayList<WeekData>();
	    List<WeekCumulativeData> weekSumDataList = new ArrayList<WeekCumulativeData>();
	    Setting setting = settingManager.getUserByUsername(SettingType.WeekAlertLimit);
	    int alertWeekNumber =  Integer.valueOf(settingManager.getUserByUsername(SettingType.AlertWeekNumber).getValue());
		
	    do{
	    	Date weekStartDate = CalendarUtils.getFirstDayOfWeek(cal.getTime());//���ܵ�һ��
	    	cal.setTime(weekStartDate);//calendar�����ɱ��ܵ�һ��
	    	String weekStartDateString = dateformat.format(weekStartDate);
	    	cal.add(Calendar.DAY_OF_MONTH, 6);//�������һ��
	    	Date weekEndDate = cal.getTime();
	    	String weekEndDateString = dateformat.format(weekEndDate);
	    	cal.add(Calendar.DAY_OF_MONTH, 1);//���ܵ�һ��
	    	WeekData data;
	    	WeekCumulativeData weekDataSum = new WeekCumulativeData();
	    	if(nowString.compareTo(weekStartDateString) >= 0){
	    		data = recordManager.getWeekData(weekStartDateString, weekEndDateString, seq);
	    		if(data.getQulifiedRate()*100 < Double.valueOf(setting.getValue())){
	    			latestAlertWeek = weekSeq;
	    		}
	    	}else{
	    		if(currentWeek == 0){
	    			currentWeek = weekSeq - 1;
	    		}
	    		data = new WeekData();
	    	}
	    	data.setStartDate(weekStartDateString);
	    	data.setEndDate(weekEndDateString);
	    	data.setWeekSeq(weekSeq);
	    	int monthSeq = weekEndDate.getMonth() + 1;
	    	data.setMonth(monthSeq);
	    	list.add(data);
	    	weekSumDataList.add(weekDataSum);
	    	nextweekDate = (Calendar) cal.clone();
	    	nextweekDate.add(Calendar.DAY_OF_MONTH, 6);
	    	weekSeq ++;
	    }while(nextweekDate.get(Calendar.YEAR) == year);
	    
	    //����12���·ݵ�������
	    int monthSeq = 1;
	    List<MonthData> monthDataList = new ArrayList<MonthData>();
	    MonthData loopData = new MonthData();
	    for(WeekData weekData : list){
	    	if(weekData.getMonth() == monthSeq){
	    		if(loopData.getMonth() == 0){
	    			loopData.setMonth(monthSeq);
	    			loopData.setStartDate(weekData.getStartDate());
	    		}
	    		loopData.setDipianNumber(loopData.getDipianNumber() + weekData.getDipianNumber());
	    		loopData.setFanxiuNumber(loopData.getFanxiuNumber() + weekData.getFanxiuNumber());
	    		loopData.setHangkouNumber(loopData.getHangkouNumber() + weekData.getHangkouNumber());
	    		loopData.setEndDate(weekData.getEndDate());
	    	}else{
	    		monthSeq ++;
	    		monthDataList.add(loopData);
	    		loopData = new MonthData();
	    		loopData.setDipianNumber(loopData.getDipianNumber() + weekData.getDipianNumber());
	    		loopData.setFanxiuNumber(loopData.getFanxiuNumber() + weekData.getFanxiuNumber());
	    		loopData.setHangkouNumber(loopData.getHangkouNumber() + weekData.getHangkouNumber());
	    		loopData.setMonth(monthSeq);
	    		loopData.setStartDate(weekData.getStartDate());
	    	}
	    }
		monthDataList.add(loopData);
		
		//����12���·ݵ��ۻ�����
		List<MonthCumulativeData> monthSumDataList = new ArrayList<MonthCumulativeData>();
	    long pipianNumberSum = 0;//Ƭ������
		long fanxiuNumberSum = 0;//��������
		for(MonthData monthData : monthDataList){
			//��֤������Ϊ0������������������ֵ
			if(monthData.getDipianNumber() != 0){
				monthData.setQulifiedRate((monthData.getDipianNumber() - monthData
						.getFanxiuNumber())
						/ (double) monthData.getDipianNumber());
			}else{
				monthData.setQulifiedRate(1);
			}
			pipianNumberSum = pipianNumberSum + monthData.getDipianNumber();
			fanxiuNumberSum = fanxiuNumberSum + monthData.getFanxiuNumber();
			MonthCumulativeData dataSum = new MonthCumulativeData();
			dataSum.setDipianNumber(pipianNumberSum);
			dataSum.setFanxiuNumber(fanxiuNumberSum);
			//��֤������Ϊ0������������������ֵ
			if(dataSum.getDipianNumber()!=0){
				dataSum.setQulifiedRate((dataSum.getDipianNumber() - dataSum
						.getFanxiuNumber())
						/ (double) dataSum.getDipianNumber());
			}else{
				dataSum.setQulifiedRate(1);
			}
			monthSumDataList.add(dataSum);
		}
		model.addAttribute("monthDataList", monthDataList);
		model.addAttribute("monthSumDataList", monthSumDataList);
    	model.addAttribute("weekAlertLimit", setting.getValue());
    	model.addAttribute("year",year);
    	model.addAttribute("alert",currentWeek - latestAlertWeek <= alertWeekNumber && latestAlertWeek !=0 ? true : false);
	}
}
