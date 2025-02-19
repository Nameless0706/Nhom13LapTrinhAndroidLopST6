package com.kounrew.Rest_api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kounrew.Rest_api.entity.CategoryEntity;
import com.kounrew.Rest_api.model.ResponseModel;
import com.kounrew.Rest_api.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/category")
public class Response {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/list")
	public ResponseEntity<?> getAllCategory(){
		return new ResponseEntity<ResponseModel>(new ResponseModel(true, "Thành công", categoryService.findAllCategory()), HttpStatus.OK);
	}
	
	@GetMapping("/get3")
	public ResponseEntity<?> getCategory(@Validated @RequestParam("id") Integer categoryid){
		Optional<CategoryEntity> category = categoryService.findCategoryById(categoryid);
		
		if(category.isPresent()) {
			return new ResponseEntity<ResponseModel>(new ResponseModel(true, "Thành công", category.get()), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<ResponseModel>(new ResponseModel(false, "Thất bại", null), HttpStatus.NOT_FOUND);

		}
	}
	
	
}
