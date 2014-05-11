package com.cgtest.bigbank.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cgtest.bigbank.Account;
import com.cgtest.bigbank.BankService;


@Controller
public class PostAccounts{
	
	@Autowired
    private BankService bankService;

    @RequestMapping("/post.do")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Security check (this is unnecessary if Spring Security is performing the authorization)
//        if (!request.isUserInRole("ROLE_TELLER")) {
//            throw new AccessDeniedException("You must be a teller to post transactions (Spring Security message)");
//        }

        // Actual business logic
        Long id = ServletRequestUtils.getRequiredLongParameter(request, "id");
        Double amount = ServletRequestUtils.getRequiredDoubleParameter(request, "amount");
        Account a = bankService.readAccount(id);
        bankService.post(a, amount);
        return new ModelAndView("redirect:listAccounts.html");
    }

}
