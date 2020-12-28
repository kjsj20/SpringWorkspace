package com.koreait.mylegacy.model.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.koreait.mylegacy.domain.Dept;
import com.koreait.mylegacy.domain.Emp;
@Repository
public class MybatisDeptDAO {
	private SqlSession sqlSession = null;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public int insert(Dept dept) {
		int result = 0;
		result = sqlSession.insert("Dept.insert", dept);
		return result;
	}
	
	
}
