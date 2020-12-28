package com.koreait.mylegacy.controller.emp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.koreait.mylegacy.domain.Dept;
import com.koreait.mylegacy.domain.Emp;
import com.koreait.mylegacy.model.dao.JdbcDeptDAO;

@Controller
public class EmpController {
	@Autowired
	private JdbcDeptDAO jdbcDeptDAO;
	
	//사원등록 폼요청
	@RequestMapping(value = "/emp/registform", method = RequestMethod.GET)
	public String registForm() {
		//저장할 것이 없고, 그냥 뷰 만 반환하고 싶을때는 String도 가능
		return "emp/regist_form";
	}
	
	@RequestMapping(value = "/emp/regist", method = RequestMethod.POST)
	public String regist(Dept dept, Emp emp) {
	System.out.println(dept.getDeptno());
	System.out.println(dept.getDname());
	System.out.println(dept.getLoc());
	
	System.out.println(emp.getEmpno());
	System.out.println(emp.getEname());
	System.out.println(emp.getSal());
	System.out.println(emp.getDeptno());
	
	System.out.println("jdbcDeptDAO는" + jdbcDeptDAO);
	jdbcDeptDAO.regist(dept);
	System.out.println("등록 원해?");
	
	return null;
	}
}
