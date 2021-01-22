package br.com.neppo.kbase.knowledgebase.api.controller;

import br.com.neppo.kbase.knowledgebase.api.dto.CategoryDTO;
import br.com.neppo.kbase.knowledgebase.api.form.CategoryForm;
import br.com.neppo.kbase.knowledgebase.api.security.TokenService;
import br.com.neppo.kbase.knowledgebase.domain.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestControllerAdvice
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity<CategoryDTO> newCategory(@Valid @RequestBody CategoryForm categoryForm, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        Long id = tokenService.getUserId(token.substring(7, token.length()));
        CategoryDTO category = categoryService.saveCategory(categoryForm, id);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @GetMapping
    public Page<CategoryDTO> getCategories(@PageableDefault(sort="id", direction = Sort.Direction.DESC, page =0, size=10) Pageable page){
        return categoryService.getCategories(page);
    }
}
