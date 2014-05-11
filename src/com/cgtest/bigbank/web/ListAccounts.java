package com.cgtest.bigbank.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cgtest.bigbank.BankService;

@Controller
public class ListAccounts {

	@Autowired
    private BankService bankService;
    
    @RequestMapping("/listAccounts.do")
    public String handleRequest(ModelMap modelMap) throws Exception {
//        if (request.getUserPrincipal() == null) {
//            throw new AuthenticationCredentialsNotFoundException("You must login to view the account list (Spring Security message)"); // only for Spring Security managed authentication
//        }
        modelMap.addAttribute("accounts",  bankService.findAccounts());
        return "listAccounts";
    }
}
