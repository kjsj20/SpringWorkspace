package com.koreait.mylegacy.controller.emp;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.mylegacy.domain.Dept;
import com.koreait.mylegacy.domain.Emp;
import com.koreait.mylegacy.model.dao.MybatisDeptDAO;
import com.koreait.mylegacy.model.service.JdbcEmpService;
import com.koreait.mylegacy.model.service.MybatisEmpService;

@Controller
public class EmpController {
	private static final Logger logger =  LoggerFactory.getLogger(EmpController.class);
	@Autowired
	private JdbcEmpService empService;
	@Autowired
	private MybatisEmpService mybatisEmpService;

	//사원등록 폼요청
	@RequestMapping(value = "/emp/registform", method = RequestMethod.GET)
	public String registForm() {
		//저장할 것이 없고, 그냥 뷰 만 반환하고 싶을때는 String도 가능
		return "emp/regist_form";
	}
	
	@RequestMapping(value = "/emp/regist", method = RequestMethod.POST)
	public String regist(Dept dept, Emp emp) {
		//log4j라는 라이브러리는 우리가 개발시 디버그 목적으로 사용하는 콘솔 출력보다도 훨씬 다양하며
		//복잡한 기능을 지원하는 로그 라이브러리이다.
		//특히 출력 로그 레벨이라는 기능을둬서, 개발자가 원하는 레벨을 선택하여 출력을 제어할 수 있도록 지원.
		//ALL(모든로깅) < DEBUG(확인) < INFO(강조) < WARN(경고) < ERROR(오류) < FATAL < OFF
	logger.debug(""+dept.getDeptno());
	logger.debug(dept.getDname());
	logger.debug(dept.getLoc());
	
	logger.debug(""+emp.getEmpno());
	logger.debug(emp.getEname());
	logger.debug(""+emp.getSal());
//	System.out.println(emp.getDeptno());
	
	emp.setDept(dept);
	int result = empService.regist(emp);
	System.out.println("등록결과"+result);
	return "redirect:/emp/list";
	}
	
	//사원 목록
	@RequestMapping(value = "/emp/list", method = RequestMethod.GET)
	public ModelAndView selectAll() {
		ModelAndView mav = new ModelAndView();
		List empList = mybatisEmpService.selectAll();
		
		mav.addObject("empList", empList);
		mav.setViewName("emp/list");
		return mav;
	}
}
