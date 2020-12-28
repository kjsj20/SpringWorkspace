package com.koreait.mylegacy.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.mylegacy.domain.Dept;
import com.koreait.mylegacy.domain.Emp;
import com.koreait.mylegacy.model.dao.JdbcDeptDAO;
import com.koreait.mylegacy.model.dao.JdbcEmpDAO;
import com.koreait.mylegacy.model.pool.PoolManager;

@Service
public class JdbcEmpService {
	@Autowired
	private PoolManager manager;
	@Autowired
	private JdbcDeptDAO jdbcDeptDAO;
	@Autowired
	private JdbcEmpDAO jdbcEmpDAO;

	public List selectAll() {
		List list = null;
		Connection con = manager.getConnection();
		jdbcEmpDAO.setCon(con);
		list = jdbcEmpDAO.selectAll();
		manager.freeConnection(con);
		return list;
	}

	public Dept selectDept(int deptno) {
		Dept dept = null;
		return dept;
	}

	public Emp selectEmp(int empno) {
		Emp emp = null;
		return emp;
	}

	public int insertDept(Dept dept) {
		int result = 0;
		return result;
	}

	public int updateDept(Dept dept) {
		int result = 0;
		return result;
	}

	public int deleteDept(int deptno) {
		int result = 0;
		return result;
	}

	public int insertEmp(Emp emp) {
		int result = 0;
		return result;
	}

	public int updateEmp(Emp emp) {
		int result = 0;
		return result;
	}

	public int deleteEmp(int empno) {
		int result = 0;
		return result;
	}
	
	public int regist(Emp emp) {
		int result = 0;
		Connection con = manager.getConnection();
		//DAO들에게 동일한 Connection을 배분!!
		jdbcDeptDAO.setCon(con);
		jdbcEmpDAO.setCon(con);
		try {
			con.setAutoCommit(false);
			jdbcDeptDAO.regist(emp.getDept());
			jdbcEmpDAO.regist(emp);
			con.commit(); //위 두개의 insert 업무수행중 에러가 없다면 트랜잭션 커밋
			result = 1;
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		manager.freeConnection(con);
		return result;
	}
}
