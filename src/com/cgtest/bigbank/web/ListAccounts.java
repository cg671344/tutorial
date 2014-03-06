package com.cgtest.bigbank.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.Assert;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.cgtest.bigbank.BankService;



@Controller
public class ListAccounts {

	@Autowired
    private BankService bankService;
    
    @RequestMapping("/listAccounts.html")
    public String handleRequest(ModelMap modelMap) throws Exception {
        // Security check (this is unnecessary if Spring Security is performing the authorization)
//        if (request.getUserPrincipal() == null) {
//            throw new AuthenticationCredentialsNotFoundException("You must login to view the account list (Spring Security message)"); // only for Spring Security managed authentication
//        }
        ModelAndView mav = new ModelAndView("listAccounts");
        modelMap.addAttribute("accounts",  bankService.findAccounts());
        return "listAccounts";
    }
}
