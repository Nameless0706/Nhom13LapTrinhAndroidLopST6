package com.kounrew.Rest_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kounrew.Rest_api.entity.CategoryEntity;
import com.kounrew.Rest_api.repository.CategoryRepository;
import com.kounrew.Rest_api.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public List<CategoryEntity> findAllCategory() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}
	
	@Override
	public Optional<CategoryEntity> findCategoryById(Integer id) {
		// TODO Auto-generated method stub
		return categoryRepository.findById(id);
	}


	@Override
	public Optional<CategoryEntity> findCategoryByName(String name) {
		// TODO Auto-generated method stub
		return categoryRepository.findByName(name);
	}

	@Override
	public CategoryEntity saveCategory(CategoryEntity categoryEntity) {
		// TODO Auto-generated method stub
		return categoryRepository.save(categoryEntity);
	}

	
	@Override
	public void deleteCategory(CategoryEntity categoryEntity) {
		// TODO Auto-generated method stub
		categoryRepository.delete(categoryEntity);
	}
	
	

}
