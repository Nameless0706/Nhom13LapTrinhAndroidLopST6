package com.kounrew.Rest_api.service;

import java.util.List;
import java.util.Optional;

import com.kounrew.Rest_api.entity.CategoryEntity;

public interface CategoryService {
	List<CategoryEntity> findAllCategory();
	
	Optional<CategoryEntity> findCategoryByName(String name);
	
	Optional<CategoryEntity> findCategoryById(Integer id);
	
	CategoryEntity saveCategory(CategoryEntity categoryEntity);
	
	void deleteCategory(CategoryEntity categoryEntity);
}
