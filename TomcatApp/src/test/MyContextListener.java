package test;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
 * 톰켓과 같은 요청이 들어오기도 이전 시점인, 즉 서버를 가동하는 
 * 감지할 수 있는 ServletContextListener를 이용하여 ,ServletContext 객체에
 * 무언가를 작업해보자!!
 * */
public class MyContextListener implements ServletContextListener{
	//어플리케이션이 초기화 될때.. 호출되는 메서드
	public void contextInitialized(ServletContextEvent sc) {
		System.out.println("어플리케이션이 톰켓 시작과 함께 가동되었어!!");
		//어플리케이션의 전역적 정보를 가진, 세션보다도 훨씬 오래 살아가는 ServletContext에
		//비즈니스객체들(Service, DAO..)을 올리자!!
		ServletContext context = sc.getServletContext();
		context.setAttribute("msg", "난 오래살아~");
		
		String obj = context.getInitParameter("contextConfigLocation");
		System.out.println("어플리케이션가동될때 메모리에 올려질 빈들은" + obj);
	}
	
	//어플리케이션이 종료 될때 호출되는 메서드
	public void contextDestroyed(ServletContextEvent sc) {
		System.out.println("어플리케이션 톰켓 종료와 함께 종료되었어!!");
	}

	
}
