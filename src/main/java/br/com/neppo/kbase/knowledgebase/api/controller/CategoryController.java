package br.com.neppo.kbase.knowledgebase.api.controller;

import br.com.neppo.kbase.knowledgebase.api.form.CategoryForm;
import br.com.neppo.kbase.knowledgebase.domain.model.Category;
import br.com.neppo.kbase.knowledgebase.domain.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.Valid;

@RestControllerAdvice
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> newCategorie(@Valid @RequestBody CategoryForm categoryForm){
        Category category = categoryService.saveCategory(categoryForm);
        if(category == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }
}
