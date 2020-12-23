package test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class TestController implements Controller{
	private Student st;
	private Dog dog;
	public void setStudent(Student st) {
		this.st = st;
	}
	public void setDog(Dog dog) {
		this.dog = dog;
	}
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		st.study1();
//		st.study2();
//		st.study3();
//		st.study4();
		dog.haveFood1();
		dog.haveFood2();
		dog.haveFood3();
		return null;
	}
}
