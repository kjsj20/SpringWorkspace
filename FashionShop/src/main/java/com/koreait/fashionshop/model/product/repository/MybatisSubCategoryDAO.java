package com.koreait.fashionshop.model.product.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.fashionshop.model.domain.SubCategory;
import com.koreait.fashionshop.model.domain.TopCategory;

@Repository  //componet-scan의 대상이 될 수 있으려면..
public class MybatisSubCategoryDAO implements SubCategoryDAO{
	@Autowired
	private SqlSessionTemplate sessionTemplate;

	public List selectAll() {
		return null;
	}

	public List selectAllById(int topcategory_id) {
		return sessionTemplate.selectList("SubCategory.selectAllById", topcategory_id);
	}

	public TopCategory select(int topcategory_id) {
		return null;
	}

	public void insert(SubCategory subCategory) {
		
	}

	public void update(SubCategory subCategory) {
		
	}

	public void delete(int subCategory_id) {
		
	}

	
	
}