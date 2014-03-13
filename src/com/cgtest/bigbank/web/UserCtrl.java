package com.cgtest.bigbank.web;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cgtest.registration.model.User;
import com.cgtest.registration.service.UserManager;
import com.cgtest.user.authentication.SystemUserDetailService;

@Controller
public class UserCtrl {
	
	private static Logger logger = LoggerFactory.getLogger(SystemUserDetailService.class);
	
	@Autowired
	UserManager userManager;
	
	@RequestMapping(value = "/register.html", method = RequestMethod.POST)
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
			userManager.add(user);
		} catch (Exception e) {
			logger.error("ÓÃ»§×¢²áÊ§°Ü", e);
		}
		return "redirect:/login.jsp";
	}
	
	@RequestMapping(value = "/listUsers.html")
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