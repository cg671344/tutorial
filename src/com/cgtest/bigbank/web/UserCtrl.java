package com.cgtest.bigbank.web;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cgtest.registration.model.User;
import com.cgtest.registration.service.UserManager;
import com.cgtest.user.authentication.SystemUserDetailService;

@Controller
public class UserCtrl {
	
	private static Logger logger = LoggerFactory.getLogger(SystemUserDetailService.class);
	
	@Autowired
	UserManager userManager;
	
	private static final String SUCCESS = "SUCCESS";
	private static final String FAILED = "FAILED";
	
	@RequestMapping(value = "/userInforMaintain.do")
	public String maintainPage(HttpServletRequest request, Model model) {
		return "/user/userInforMaintain";
	}
	
	@ResponseBody
	@RequestMapping(value = "/changePassword.do")
	public String passwordChange(HttpServletRequest request, Model model) {
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword"); 
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String, String > map = new HashMap<String,String>();
		User user = userManager.getUserByUsername(userDetails.getUsername());
		Md5PasswordEncoder md5=new Md5PasswordEncoder();
		String encodeOldPassword= md5.encodePassword(oldPassword,null);
		if(user.getPassword().equals(encodeOldPassword)){
			String md5Password = md5.encodePassword(newPassword,null);
			user.setPassword(md5Password);
			userManager.save(user);
			map.put("status", SUCCESS);
	        JSONObject jsonObject = JSONObject.fromObject(map);  
			return jsonObject.toString();
		}else{
			map.put("status", FAILED);
	        JSONObject jsonObject = JSONObject.fromObject(map);  
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value = "/register.do", method = RequestMethod.POST)
	public String submit(HttpServletRequest request, Model model) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user=  new User();
		user.setUsername(username);
		//TODO:adding some salt can secure the password
		Md5PasswordEncoder md5=new Md5PasswordEncoder();
		String md5Password=md5.encodePassword(password,null);
		user.setPassword(md5Password);
		try {
			userManager.save(user);
		} catch (Exception e) {
			logger.error("ÓÃ»§×¢²áÊ§°Ü", e);
		}
		return "redirect:/login.jsp";
	}
	
	@RequestMapping(value = "/listUsers.do")
	public String listUsers(HttpServletRequest request, Model model) {
		List<User> users = userManager.getUsers();
		model.addAttribute("users", users);
		return "listUsers";
	}
	
	@RequestMapping(value = "/loginIndex.html")
	public String loginIndex(HttpServletRequest request, Model model) {
		return "redirect:/login/index.jsp";
	}
}