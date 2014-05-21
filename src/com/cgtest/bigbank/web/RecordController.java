package com.cgtest.bigbank.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cgtest.biz.pojo.PageModel;
import com.cgtest.registration.model.Record;
import com.cgtest.registration.service.RecordManager;

@Controller
public class RecordController {

	@Autowired
	RecordManager recordManager;
	
	public static final int PAGE_SIZE = 16; 
	
    @RequestMapping("/listRecords.do")
    public String handleRequest(HttpServletRequest request, ModelMap modelMap) throws Exception {
    	String startDate = request.getParameter("startDate");
    	String endDate = request.getParameter("endDate");
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
		if(startDate != null && endDate != null){
	 		startDateString = StringUtils.remove(startDate, '-');
    		endDateString = StringUtils.remove(endDate, '-');
    		pageModel =  recordManager.getPagedRecord(pageIndex, PAGE_SIZE, startDateString, endDateString);
    	}else{
    		pageModel =  recordManager.getPagedRecord(pageIndex, PAGE_SIZE, null, null);
    	}
		Map<String,Object>rs=new HashMap<String,Object>();
		rs.put("results", pageModel.getList());
		rs.put("total",pageModel.getTotalRecords());
		rs.put("pagesize",PAGE_SIZE);
		rs.put("pageIndex",pageIndex);
		request.setAttribute("rs", rs);
        return "listRecords";
    }
    
    @RequestMapping("/hangongChartNoQuery.do")
    public String hangongChartNoQuery(HttpServletRequest request, ModelMap modelMap) throws Exception {
    	String chartNo = request.getParameter("chartNo");
    	String hankouNo = request.getParameter("hankouNo");
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
		PageModel pageModel = recordManager.getPagedHangongChartNoQueryRecord(chartNo, hankouNo, pageIndex, PAGE_SIZE);;
		Map<String,Object>rs=new HashMap<String,Object>();
		rs.put("results", pageModel.getList());
		rs.put("total",pageModel.getTotalRecords());
		rs.put("pagesize",PAGE_SIZE);
		request.setAttribute("rs", rs);
        return "/hangong/hangongChartNoQuery";
    }
    
    
    @RequestMapping("/hangongChartNoQueryForAdmin.do")
    public String hangongChartNoQueryForAdmin(HttpServletRequest request, ModelMap modelMap) throws Exception {
    	String chartNo = request.getParameter("chartNo");
    	String hankouNo = request.getParameter("hankouNo");
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
		PageModel pageModel = recordManager.getPagedHangongChartNoQueryRecord(chartNo, hankouNo, pageIndex, PAGE_SIZE);;
		Map<String,Object>rs=new HashMap<String,Object>();
		rs.put("results", pageModel.getList());
		rs.put("total",pageModel.getTotalRecords());
		rs.put("pagesize",PAGE_SIZE);
		request.setAttribute("rs", rs);
        return "/hangong/hangongChartNoQueryForAdmin";
    }
    
    @ResponseBody
    @RequestMapping("/deleteRecords.do")
    public String deleteRecords(HttpServletRequest request, ModelMap modelMap) throws Exception {
    	String ids = request.getParameter("ids");
    	String [] idsArray = ids.split(",");
    	List<String> idList = new ArrayList<String>();
    	for(String id : idsArray){
    		idList.add(id);
    	}
    	recordManager.deleteRecordByIds(idList);
        Map<String,String> map = new HashMap<String,String>();  
        map.put( "status", "SUCCESS"); // 通知页面数据提交成功 
        JSONObject jsonObject = JSONObject.fromObject(map);  
		return jsonObject.toString();
    }
    
    @ResponseBody
    @RequestMapping("/editRecord.do")
    public String editRecord(HttpServletRequest request, ModelMap modelMap) throws Exception {
    	String recordId = request.getParameter("recordId");
    	String zone = request.getParameter("zone");
		String zoneClass = request.getParameter("zoneClass");
		Record record = recordManager.getRecordById(recordId);
		record.setZone(zone);
		record.setZoneClass(zoneClass);
		recordManager.saveRecord(record);
        Map<String,String> map = new HashMap<String,String>();  
        map.put( "status", "SUCCESS"); // 通知页面数据提交成功 
        JSONObject jsonObject = JSONObject.fromObject(map);  
		return jsonObject.toString();
    }
    
}
