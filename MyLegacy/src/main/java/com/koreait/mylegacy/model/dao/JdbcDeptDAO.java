package com.koreait.mylegacy.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.mylegacy.domain.Dept;
import com.koreait.mylegacy.model.pool.PoolManager;
@Repository
public class JdbcDeptDAO {
	@Autowired
	private PoolManager manager;
	
	public List selectAll() {
		List list = null;
		return list;
	}
	
	public Dept select(int deptno) {
		Dept dept = null;
		return dept;
	}
	
	public int regist(Dept dept) {
		int result = 0;
		System.out.println("풀매니저"+manager);
		return result;
	}
	
	public int update(Dept dept) {
		int result = 0;
		return result;
	}
	
	public int delete(int deptno) {
		int result = 0;
		return result;
	}
}
