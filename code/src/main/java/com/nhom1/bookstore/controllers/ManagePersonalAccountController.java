package com.nhom1.bookstore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nhom1.bookstore.entity.Account;
import com.nhom1.bookstore.services.AccountService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ManagePersonalAccountController {
    private final AccountService accountService;
    
    public ManagePersonalAccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/taikhoan/thongtin")
    public String viewPersonalAccount(HttpSession session, Model model) {
        Object loggedInUser = session.getAttribute("loggedInUser");
        if(loggedInUser != null) {
            Account account = accountService.getAccountNonPassword(loggedInUser.toString());
            model.addAttribute("account", account);
            return "user_taikhoan";
        }
        return "redirect:/dangnhap";
    }

    @PostMapping("/taikhoan/thongtin")
    public String changeInformation(HttpSession session,
    @RequestParam("hoten") String hoten, 
    @RequestParam("sdt") String sdt, 
    @RequestParam("diachi") String diachi) {
        Account account = new Account();
        account.setHoTen(hoten);
        account.setSoDienThoai(sdt);
        account.setDiaChi(diachi);

        Object loggedInUser = session.getAttribute("loggedInUser");
        accountService.editAccount(loggedInUser.toString(), account);
        return "redirect:/taikhoan/thongtin";
    }
}
