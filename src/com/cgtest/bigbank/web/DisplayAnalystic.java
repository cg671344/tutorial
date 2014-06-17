package com.cgtest.bigbank.web;

import java.net.URLDecoder;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cgtest.biz.pojo.MsColumnData;
import com.cgtest.biz.pojo.PageModel;
import com.cgtest.biz.pojo.PieDataBean;
import com.cgtest.registration.model.Record.HankouSequence;
import com.cgtest.registration.service.RecordManager;

/**
 * 获取图表显示用的数据
 * @author chairy
 *
 */
@Controller
public class DisplayAnalystic {

	public static final int PAGE_SIZE = 16; 
	
	@Autowired
	RecordManager recordManager;
	
    @RequestMapping("/zoneClassAnalystic.do")
    public String zoneClass(HttpServletRequest request, ModelMap model) throws Exception {
    	String startDate = request.getParameter("startDate");
    	String endDate = request.getParameter("endDate");
		List<MsColumnData> list = recordManager.getResponsibilityZoneData(startDate, endDate, HankouSequence.FIRST);
		model.addAttribute("displayDataList", list);
        return "/zoneClass/zoneClassAnalystic";
    }
    
    @RequestMapping("/zoneClassAnalysticTable.do")
    public String zoneClassAnalysticTable(HttpServletRequest request, ModelMap model) throws Exception {
    	String startDate = request.getParameter("startDate");
    	if("".equals(startDate)){
    		startDate = null;
    	}
    	String endDate = request.getParameter("endDate");
    	if("".equals(endDate)){
    		endDate = null;
    	}
		List<MsColumnData> list = recordManager.getResponsibilityZoneData(StringUtils.remove(
				startDate, '-'), StringUtils.remove(endDate, '-'), HankouSequence.FIRST);
		model.addAttribute("displayDataList", list);
        return "/zoneClass/zoneClassAnalysticTable";
    }
    
	@ResponseBody
	@RequestMapping(value = "/getZoneClassAnalysticJsonData.do")
	public String getMsColumnData(HttpServletRequest request) {
    	String startDate = request.getParameter("startDate");
    	if("".equals(startDate)){
    		startDate = null;
    	}
    	String endDate = request.getParameter("endDate");
    	if("".equals(endDate)){
    		endDate = null;
    	}
		List<MsColumnData> list = recordManager.getResponsibilityZoneData(StringUtils.remove(
				startDate, '-'), StringUtils.remove(endDate, '-'), HankouSequence.FIRST);
		JSONArray jsonData = JSONArray.fromObject(list);  
		return jsonData.toString();
	}
    
    @RequestMapping("/zoneClassAnalysticSecondRT.do")
    public String zoneClassSecondRT(HttpServletRequest request, ModelMap model) throws Exception {
    	String startDate = request.getParameter("startDate");
    	if("".equals(startDate)){
    		startDate = null;
    	}
    	String endDate = request.getParameter("endDate");
    	if("".equals(endDate)){
    		endDate = null;
    	}
		List<MsColumnData> list = recordManager.getResponsibilityZoneData(startDate, endDate, HankouSequence.SECOND);
		model.addAttribute("displayDataList", list);
        return "/zoneClass/zoneClassAnalysticSecondRT";
    }
    
    @RequestMapping("/zoneClassAnalysticTableSecondRT.do")
    public String zoneClassAnalysticTableSecondRT(HttpServletRequest request, ModelMap model) throws Exception {
    	String startDate = request.getParameter("startDate");
    	if("".equals(startDate)){
    		startDate = null;
    	}
    	String endDate = request.getParameter("endDate");
    	if("".equals(endDate)){
    		endDate = null;
    	}
    	
		List<MsColumnData> list = recordManager.getResponsibilityZoneData(StringUtils.remove(
				startDate, '-'), StringUtils.remove(endDate, '-'), HankouSequence.SECOND);
		model.addAttribute("displayDataList", list);
        return "/zoneClass/zoneClassAnalysticTableSecondRT";
    }
    
	@ResponseBody
	@RequestMapping(value = "/getZoneClassAnalysticJsonDataSecondRT.do")
	public String getMsColumnDataSecondRT(HttpServletRequest request) {
    	String startDate = request.getParameter("startDate");
    	if("".equals(startDate)){
    		startDate = null;
    	}
    	String endDate = request.getParameter("endDate");
    	if("".equals(endDate)){
    		endDate = null;
    	}
		List<MsColumnData> list = recordManager.getResponsibilityZoneData(StringUtils.remove(
				startDate, '-'), StringUtils.remove(endDate, '-'), HankouSequence.SECOND);
		JSONArray jsonData = JSONArray.fromObject(list);  
		return jsonData.toString();
	}
	
    @RequestMapping("/zoneAnalystic.do")
    public String zoneAnalystic(ModelMap model) throws Exception {
		List<MsColumnData> list = recordManager.getZoneAnalysticData(null,null, HankouSequence.FIRST);
		model.addAttribute("displayDataList", list);
        return "/zone/zoneAnalystic";
    }
    
	@ResponseBody
	@SuppressWarnings("unchecked")
    @RequestMapping("/getZoneAnalysticJsonData.do")
    public String getZoneAnalysticJsonData(HttpServletRequest request, ModelMap model) throws Exception {
    	String startDate = request.getParameter("startDate");
    	if("".equals(startDate)){
    		startDate = null;
    	}
    	String endDate = request.getParameter("endDate");
    	if("".equals(endDate)){
    		endDate = null;
    	}
    	
    	List<MsColumnData> list = null;
		if(startDate != null && endDate != null){
			list = recordManager.getZoneAnalysticData(StringUtils.remove(
					startDate, '-'), StringUtils.remove(endDate, '-'), HankouSequence.FIRST);
    	}else{
    		list = recordManager.getZoneAnalysticData(null, null, HankouSequence.FIRST);
    	}
    	JSONArray jsonData = JSONArray.fromObject(list);
    	String jsonStringData = jsonData.toString();
        return jsonStringData;
    }
    
    @RequestMapping("/zoneAnalysticTable.do")
    public String zoneAnalysticTable(HttpServletRequest request, ModelMap model) throws Exception {
    	String startDate = request.getParameter("startDate");
    	if("".equals(startDate)){
    		startDate = null;
    	}
    	String endDate = request.getParameter("endDate");
    	if("".equals(endDate)){
    		endDate = null;
    	}
    	List<MsColumnData> list = null;
		if(startDate != null && endDate != null){
			list = recordManager.getZoneAnalysticData(StringUtils.remove(
					startDate, '-'), StringUtils.remove(endDate, '-'), HankouSequence.FIRST);
    	}else{
    		list = recordManager.getZoneAnalysticData(null, null,HankouSequence.FIRST);
    	}
		model.addAttribute("displayDataList", list);
        return "/zone/zoneAnalysticTable";
    }
    
    
    @RequestMapping("/zoneAnalysticSecondRT.do")
    public String zoneAnalysticSecondRT(ModelMap model) throws Exception {
		List<MsColumnData> list = recordManager.getZoneAnalysticData(null,null, HankouSequence.SECOND);
		model.addAttribute("displayDataList", list);
        return "/zone/zoneAnalysticSecondRT";
    }
    
	@ResponseBody
	@SuppressWarnings("unchecked")
    @RequestMapping("/getZoneAnalysticJsonDataSecondRT.do")
    public String getZoneAnalysticJsonDataSecondRT(HttpServletRequest request, ModelMap model) throws Exception {
    	String startDate = request.getParameter("startDate");
    	if("".equals(startDate)){
    		startDate = null;
    	}
    	String endDate = request.getParameter("endDate");
    	if("".equals(endDate)){
    		endDate = null;
    	}
    	List<MsColumnData> list = null;
		if(startDate != null && endDate != null){
			list = recordManager.getZoneAnalysticData(StringUtils.remove(
					startDate, '-'), StringUtils.remove(endDate, '-'), HankouSequence.SECOND);
    	}else{
    		list = recordManager.getZoneAnalysticData(null, null, HankouSequence.SECOND);
    	}
    	JSONArray jsonData = JSONArray.fromObject(list);
    	String jsonStringData = jsonData.toString();
        return jsonStringData;
    }
    
    @RequestMapping("/zoneAnalysticTableSecondRT.do")
    public String zoneAnalysticTableSecondRT(HttpServletRequest request, ModelMap model) throws Exception {
    	String startDate = request.getParameter("startDate");
    	if("".equals(startDate)){
    		startDate = null;
    	}
    	String endDate = request.getParameter("endDate");
    	if("".equals(endDate)){
    		endDate = null;
    	}
    	List<MsColumnData> list = null;
		if(startDate != null && endDate != null){
			list = recordManager.getZoneAnalysticData(StringUtils.remove(
					startDate, '-'), StringUtils.remove(endDate, '-'), HankouSequence.SECOND);
    	}else{
    		list = recordManager.getZoneAnalysticData(null, null,HankouSequence.SECOND);
    	}
		model.addAttribute("displayDataList", list);
        return "/zone/zoneAnalysticTableSecondRT";
    }
    
    @RequestMapping("/hangongAnalystic.do")
    public String hangongAnalystic(HttpServletRequest request, ModelMap model) throws Exception {
    	String startDate = request.getParameter("startDate");
    	String endDate = request.getParameter("endDate");
    	if("".equals(startDate)){
    		startDate = null;
    	}
    	if("".equals(endDate)){
    		endDate = null;
    	}
    	String hangongNo = request.getParameter("hangongNo");
    	String hangongName = request.getParameter("hangongName");
    	if(hangongName!=null)
    		hangongName = URLDecoder.decode(hangongName,"UTF-8");
    	List<MsColumnData> list = recordManager.getHangongAnalysticData(
				StringUtils.remove(startDate, '-'), StringUtils.remove(endDate,
						'-'), hangongNo, hangongName, HankouSequence.FIRST);
		Map<String,Object>rs=new HashMap<String,Object>();
		rs.put("results",list);
		rs.put("total",list.size());
		rs.put("pagesize",list.size());
		request.setAttribute("rs", rs);
        return "/hangong/hangongAnalystic";
    }
    
    @RequestMapping("/hankouTable.do")
    public String hangkouTable(HttpServletRequest request, ModelMap model) throws Exception {
    	String startDate = request.getParameter("startDate");
    	if("".equals(startDate)){
    		startDate = null;
    	}
    	String endDate = request.getParameter("endDate");
    	if("".equals(endDate)){
    		endDate = null;
    	}
    	String hangongNo = request.getParameter("hangongNo");
    	int pageIndex;
		String PARAMETER_PAGE = (new ParamEncoder("element").
				 encodeParameterName(TableTagParameters.PARAMETER_PAGE));
		String pageIndexString = request.getParameter(PARAMETER_PAGE);
		if(pageIndexString==null||pageIndexString==""){
			pageIndex=1;
		}
		else{
			pageIndex = Integer.parseInt(pageIndexString);
		}
		PageModel pageModel = null;
		String startDateString  = null;
		String endDateString = null;
		if (startDate != null && endDate != null) {
			startDateString = StringUtils.remove(startDate, '-');
			endDateString = StringUtils.remove(endDate, '-');
			pageModel = recordManager.getPagedHangongRecord(pageIndex,
					PAGE_SIZE, startDateString, endDateString, hangongNo,HankouSequence.FIRST);
		} else {
			pageModel = recordManager.getPagedHangongRecord(pageIndex,
					PAGE_SIZE, null, null, hangongNo,HankouSequence.FIRST);
		}
		Map<String,Object>rs=new HashMap<String,Object>();
		rs.put("results", pageModel.getList());
		rs.put("total",pageModel.getTotalRecords());
		rs.put("pagesize",PAGE_SIZE);
		request.setAttribute("rs", rs);
        return "/hangong/hankouTable";
    }
    
    @RequestMapping("/hangongAnalysticSecondRT.do")
    public String hangongAnalysticSecondRT(HttpServletRequest request, ModelMap model) throws Exception {
    	String startDate = request.getParameter("startDate");
    	if("".equals(startDate)){
    		startDate = null;
    	}
    	String endDate = request.getParameter("endDate");
    	if("".equals(endDate)){
    		endDate = null;
    	}
    	
    	String hangongNo = request.getParameter("hangongNo");
    	String hangongName = request.getParameter("hangongName");
    	if(hangongName!=null)
    		hangongName = URLDecoder.decode(hangongName,"UTF-8");
    	List<MsColumnData> list = recordManager.getHangongAnalysticData(
				StringUtils.remove(startDate, '-'), StringUtils.remove(endDate,
						'-'),hangongNo, hangongName, HankouSequence.SECOND);
		Map<String,Object>rs=new HashMap<String,Object>();
		rs.put("results",list);
		rs.put("total",list.size());
		rs.put("pagesize",list.size());
		request.setAttribute("rs", rs);
        return "/hangong/hangongAnalysticSecondRT";
    }
    
    @RequestMapping("/hankouTableSecondRT.do")
    public String hangkouTableSecondRT(HttpServletRequest request, ModelMap model) throws Exception {
    	String startDate = request.getParameter("startDate");
    	String endDate = request.getParameter("endDate");
    	if("".equals(startDate)){
    		startDate = null;
    	}
    	if("".equals(endDate)){
    		endDate = null;
    	}
    	String hangongNo = request.getParameter("hangongNo");
    	int pageIndex;
		String PARAMETER_PAGE = (new ParamEncoder("element").
				 encodeParameterName(TableTagParameters.PARAMETER_PAGE));
		String pageIndexString = request.getParameter(PARAMETER_PAGE);
		if(pageIndexString==null||pageIndexString==""){
			pageIndex=1;
		}
		else{
			pageIndex = Integer.parseInt(pageIndexString);
		}
		PageModel pageModel = null;
		String startDateString  = null;
		String endDateString = null;
		if (startDate != null && endDate != null) {
			startDateString = StringUtils.remove(startDate, '-');
			endDateString = StringUtils.remove(endDate, '-');
			pageModel = recordManager.getPagedHangongRecord(pageIndex,
					PAGE_SIZE, startDateString, endDateString, hangongNo,HankouSequence.SECOND);
		} else {
			pageModel = recordManager.getPagedHangongRecord(pageIndex,
					PAGE_SIZE, null, null, hangongNo,HankouSequence.SECOND);
		}
		Map<String,Object>rs=new HashMap<String,Object>();
		rs.put("results", pageModel.getList());
		rs.put("total",pageModel.getTotalRecords());
		rs.put("pagesize",PAGE_SIZE);
		request.setAttribute("rs", rs);
        return "/hangong/hankouTableSecondRT";
    }
	
	@ResponseBody
	@RequestMapping(value = "/getPieData.do")
	public String getPieData(HttpServletRequest request) {
		List<PieDataBean>  list = recordManager.getZoneClass();
		JSONArray jsonData = JSONArray.fromObject(list);  
		return jsonData.toString();
	}
}
