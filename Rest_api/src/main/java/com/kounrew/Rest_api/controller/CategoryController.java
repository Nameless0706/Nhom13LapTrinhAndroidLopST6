package com.kounrew.Rest_api.controller;

import java.util.Optional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.kounrew.Rest_api.entity.CategoryEntity;
import com.kounrew.Rest_api.service.CategoryService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("")
	public ResponseEntity<?> listAllCategory() {
		return ResponseEntity.ok().body(categoryService.findAllCategory());
	}

	@GetMapping("/get")
	public ResponseEntity<CategoryEntity> getCategoryByName(@RequestParam("name") String categoryName,
			HttpServletRequest request) {
		// System.out.println("Encoding: " + request.getCharacterEncoding()); // Kiểm
		// tra encoding
		Optional<CategoryEntity> category = categoryService.findCategoryByName(categoryName);
		if (category == null)
			// Tạm thời là vậy, thực tế người ta dùng AOP để bắt exception
			return ResponseEntity.notFound().build();
		// Nếu tìm thấy return 200 OK
		return ResponseEntity.ok(category.get());
	}

	@PostMapping(value = "/save")
	public CategoryEntity saveCategory(@Valid @RequestBody CategoryEntity cate) {
		return categoryService.saveCategory(cate);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<CategoryEntity> updateCategory(@PathVariable(value = "id") Integer categoryid,
			@Valid @RequestBody CategoryEntity category) {
		Optional<CategoryEntity> cate = categoryService.findCategoryById(categoryid);
		if (cate == null) {
			return ResponseEntity.notFound().build();
		}
		cate.get().setName(category.getName());
		CategoryEntity updatedContact = categoryService.saveCategory(cate.get());
		return ResponseEntity.ok(updatedContact);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<CategoryEntity> deleteCategory(@PathVariable(value = "id") Integer categoryid) {
		Optional<CategoryEntity> cate = categoryService.findCategoryById(categoryid);
		if (cate == null) {
			return ResponseEntity.notFound().build();
		}
		categoryService.deleteCategory(cate.get());
		return ResponseEntity.ok().build();
	}

}
