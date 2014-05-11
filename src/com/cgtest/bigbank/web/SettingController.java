package com.cgtest.bigbank.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cgtest.registration.model.Setting;
import com.cgtest.registration.model.Setting.SettingType;
import com.cgtest.registration.service.SettingManager;
@Controller
@RequestMapping("/setting")
public class SettingController {

	public static final String SUCCESS = "SUCCESS";
	public static final String FAILED = "FAILED";
	
	@Autowired
    private SettingManager settingManager;
	
    @RequestMapping("/alertLimitSetting.do")
    public String alertLimitSetting(HttpServletRequest request, Model model) throws Exception {
    	Setting weekAlertLimitSetting = settingManager.getUserByUsername(SettingType.WeekAlertLimit);
    	Setting weekAlertLimitSettingSecondRT = settingManager.getUserByUsername(SettingType.WeekAlertLimitSecondRT);
    	Setting cumulativeRateStartDateSetting = settingManager.getUserByUsername(SettingType.CumulativeRateStartDate);
    	String weekAlertLimit = weekAlertLimitSetting.getValue();
    	String weekAlertLimitSecondRT = weekAlertLimitSettingSecondRT.getValue();
    	String cumulativeRateStartDate = cumulativeRateStartDateSetting.getValue();
		model.addAttribute("alertLimit", weekAlertLimit);
		model.addAttribute("alertLimitSecondRT", weekAlertLimitSecondRT);
		model.addAttribute("cumulativeStartDate", cumulativeRateStartDate);
		return "/setting/alertLimit";
    }
	
	@ResponseBody
    @RequestMapping("/getAlertLimitSetting.do")
    public String handleRequest(HttpServletRequest request, Model model) throws Exception {
    	Setting setting = settingManager.getUserByUsername(SettingType.WeekAlertLimit);
    	String value = setting.getValue();
    	Map<String,String>map = new HashMap<String,String>();
		map.put("value", value);
        JSONObject jsonObject = JSONObject.fromObject(map);  
		return jsonObject.toString();
    }
	
	@ResponseBody
    @RequestMapping("/setAlertLimitSetting.do")
    public String setAlertLimitSetting(HttpServletRequest request, Model model) throws Exception {
		String value = request.getParameter("alertLimit");
    	Setting setting = settingManager.getUserByUsername(SettingType.WeekAlertLimit);
    	setting.setValue(value);
    	settingManager.update(setting);
        Map<String,String> map = new HashMap<String,String>();  
        map.put("status", SUCCESS); 
        JSONObject jsonObject = JSONObject.fromObject(map);  
		return jsonObject.toString();
    }
	
	
	@ResponseBody
    @RequestMapping("/getAlertLimitSettingSecondRT.do")
    public String handleRequestSecondRT(HttpServletRequest request, Model model) throws Exception {
    	Setting setting = settingManager.getUserByUsername(SettingType.WeekAlertLimitSecondRT);
    	String value = setting.getValue();
    	Map<String,String>map = new HashMap<String,String>();
		map.put("value", value);
        JSONObject jsonObject = JSONObject.fromObject(map);  
		return jsonObject.toString();
    }
	
	@ResponseBody
    @RequestMapping("/setAlertLimitSettingSecondRT.do")
    public String setAlertLimitSettingSecondRT(HttpServletRequest request, Model model) throws Exception {
		String value = request.getParameter("alertLimit");
    	Setting setting = settingManager.getUserByUsername(SettingType.WeekAlertLimitSecondRT);
    	setting.setValue(value);
    	settingManager.update(setting);
        Map<String,String> map = new HashMap<String,String>();  
        map.put("status", SUCCESS); 
        JSONObject jsonObject = JSONObject.fromObject(map);  
		return jsonObject.toString();
    }
	
    @RequestMapping("/cumulativeStartDate.do")
    public String cumulativeStartDate(HttpServletRequest request, Model model) throws Exception {
    	Setting setting = settingManager.getUserByUsername(SettingType.CumulativeRateStartDate);
    	String value = setting.getValue();
    	Map<String,String>map = new HashMap<String,String>();
		map.put("value", value);
		model.addAttribute("cumulativeStartDate", value);
		return "/setting/cumulativeStartDate";
    }
	
	@ResponseBody
    @RequestMapping("/setCumulativeStartDate.do")
    public String setCumulativeStartDate(HttpServletRequest request, Model model) throws Exception {
		String value = request.getParameter("cumulativeStartDate");
    	Setting setting = settingManager.getUserByUsername(SettingType.CumulativeRateStartDate);
    	setting.setValue(value);
    	settingManager.update(setting);
        Map<String,String> map = new HashMap<String,String>();  
        map.put("status", SUCCESS); 
        JSONObject jsonObject = JSONObject.fromObject(map);  
		return jsonObject.toString();
    }
	
}
