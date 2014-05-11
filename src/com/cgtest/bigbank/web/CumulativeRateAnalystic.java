package com.cgtest.bigbank.web;

import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cgtest.biz.pojo.MsColumnData;
import com.cgtest.biz.pojo.PageModel;
import com.cgtest.biz.pojo.PieDataBean;
import com.cgtest.registration.model.Setting;
import com.cgtest.registration.model.Record.HankouSequence;
import com.cgtest.registration.model.Setting.SettingType;
import com.cgtest.registration.service.RecordManager;
import com.cgtest.registration.service.SettingManager;

/**
 * 
 * @author chairy
 *
 */
@Controller
public class CumulativeRateAnalystic {

	public static final int PAGE_SIZE = 16; 
	
	@Autowired
	RecordManager recordManager;
	
	@Autowired
    private SettingManager settingManager;
	
	/**
	 * 获取累计合格率，需要判断是否包含13年的基准数据
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("/getCumulativedRate.do")
    public String getCumulativedRate(HttpServletRequest request, Model model) throws Exception {
    	String startDate = settingManager.getUserByUsername(SettingType.CumulativeRateStartDate).getValue();
    	String startDateString = StringUtils.remove(startDate, '-');
    	HankouSequence seq = HankouSequence.FIRST;
    	SettingType pianziNumberType = SettingType.BasePianziNumber;
    	SettingType fanxiuNumberType = SettingType.BaseFanxiuNumber;
    	ModelMap rateData = getCumulativeRateData(startDateString, seq, pianziNumberType,
				fanxiuNumberType);
    	model.addAllAttributes(rateData);
    	return "/cumulativeRate/cumulativeRate";
    }
    
    /**
     * 获取二次RT累计合格率情况
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/getCumulativedRateSecondRT.do")
    public String getCumulativedRateSecondRT(HttpServletRequest request, Model model) throws Exception {
    	String startDate = settingManager.getUserByUsername(SettingType.CumulativeRateStartDate).getValue();
    	String startDateString = StringUtils.remove(startDate, '-');
    	HankouSequence seq = HankouSequence.SECOND;
    	SettingType pianziNumberType = SettingType.BasePianziNumberSecondRT;
    	SettingType fanxiuNumberType = SettingType.BaseFanxiuNumberSecondRT;
    	ModelMap rateData = getCumulativeRateData(startDateString, seq, pianziNumberType,
				fanxiuNumberType);
    	model.addAllAttributes(rateData);
    	return "/cumulativeRate/cumulativeRateSecondRT";
    }
    
	private ModelMap getCumulativeRateData(String startDateString,
			HankouSequence seq, SettingType pianziNumberType,
			SettingType fanxiuNumberType) {
		ModelMap model = new ModelMap();
		long[] pair = recordManager.getDipianTotalFanxiuTotalPair(startDateString, seq);
    	
    	Setting baseDate = settingManager.getUserByUsername(SettingType.BaseRecordDate);
    	String baseDateString = StringUtils.remove(baseDate.getValue(), '-');
    	//如果累计时间早于基准数据时间，累计时需要加入基准数据
    	if(startDateString.compareTo(baseDateString) <= 0){
    		Setting basePianziNumber = settingManager.getUserByUsername(pianziNumberType);
    		Setting baseFanxiuNumber = settingManager.getUserByUsername(fanxiuNumberType);
    		long pianziNumberTotal = pair[0] + Long.valueOf(basePianziNumber.getValue());
    		long fanxiuNumberTotal = pair[1] + Long.valueOf(baseFanxiuNumber.getValue());
    		model.addAttribute("pianziNumberTotal", pianziNumberTotal);
    		model.addAttribute("fanxiuNumberTotal", fanxiuNumberTotal);
    		if(pianziNumberTotal == 0){
    			model.addAttribute("rate", 0);
    		}else{
    			double rate = (pianziNumberTotal- fanxiuNumberTotal) / (double)pianziNumberTotal;
    			double displayPecentValue = rate * 100;
    			NumberFormat fmt = new DecimalFormat("0.00");
    			model.addAttribute("rate",fmt.format(displayPecentValue));
    		}
    	}else{
    		model.addAttribute("pianziNumberTotal", pair[0]);
    		model.addAttribute("fanxiuNumberTotal", pair[1]);
    		if(pair[0] == 0){
    			model.addAttribute("rate", 0);
    		}else{
    			double rate =  (pair[0]- pair[1]) / (double)pair[0];
    			double displayPecentValue = rate * 100;
    			NumberFormat fmt = new DecimalFormat("0.00");
    			model.addAttribute("rate", fmt.format(displayPecentValue));
    		}
    	}
    	return model;
	}
}
