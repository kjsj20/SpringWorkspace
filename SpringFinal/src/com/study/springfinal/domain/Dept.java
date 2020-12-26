package com.study.springfinal.domain;

import java.util.List;


/*
 * 부서와 사원간의 관계가 1:多 이므로, 다수의 자식을 보유한 관계를 
 * Mybatis에서는 Collection 이라고 한
 * */
public class Dept {
	private int deptno;
	private String dname;
	private String loc;
	//사원들을 거느린다..
	private List<Emp> empList;
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public List<Emp> getEmpList() {
		return empList;
	}
	public void setEmpList(List<Emp> empList) {
		this.empList = empList;
	}
}
