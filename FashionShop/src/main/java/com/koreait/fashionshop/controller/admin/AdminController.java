package com.koreait.fashionshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {
//관리자 모드 메인 요청
 @RequestMapping("/admin")
 public String adminMain() {
	 return "admin/main";
 }
}
