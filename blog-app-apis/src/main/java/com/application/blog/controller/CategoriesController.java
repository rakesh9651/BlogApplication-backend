package com.application.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.blog.payloads.ApiResponse;
import com.application.blog.payloads.CategoryDto;
import com.application.blog.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

	@Autowired
	private CategoryService catService;

	// create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategories(@Valid @RequestBody CategoryDto catDto) {
		CategoryDto createdCat = this.catService.createCategory(catDto);
		return new ResponseEntity<CategoryDto>(createdCat, HttpStatus.CREATED);
	}

	// update
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @PathVariable("catId") Integer catId,
			@RequestBody CategoryDto catDto) {
		CategoryDto updatedDto = this.catService.updateCategory(catDto, catId);
		return new ResponseEntity<CategoryDto>(updatedDto, HttpStatus.OK);
	}

	// delete
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("catId") Integer catId) {
		this.catService.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category deleted successfully !!", true),
				HttpStatus.OK);
	}

	// get
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("catId") Integer catId) {
		CategoryDto catDto = this.catService.getCategory(catId);
		return new ResponseEntity<CategoryDto>(catDto, HttpStatus.OK);
	}

	// get all
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategories() {
		List<CategoryDto> catDtos = this.catService.getCategories();
		return ResponseEntity.ok(catDtos);
	}
}
