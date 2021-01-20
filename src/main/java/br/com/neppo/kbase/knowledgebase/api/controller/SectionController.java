package br.com.neppo.kbase.knowledgebase.api.controller;

import br.com.neppo.kbase.knowledgebase.api.dto.CategoryDTO;
import br.com.neppo.kbase.knowledgebase.api.dto.SectionDTO;
import br.com.neppo.kbase.knowledgebase.api.form.SectionForm;
import br.com.neppo.kbase.knowledgebase.domain.model.Category;
import br.com.neppo.kbase.knowledgebase.domain.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestControllerAdvice
@RequestMapping("/sections")
public class SectionController {

    @Autowired
    SectionService sectionService;

    @PostMapping
    public ResponseEntity<SectionDTO> createSection(@Valid @RequestBody SectionForm sectionForm){
        SectionDTO section = sectionService.createSection(sectionForm);
        return new ResponseEntity<>(section, HttpStatus.CREATED);
    }

    @GetMapping("/{categoryID}")
    public ResponseEntity<CategoryDTO> getSectionsFromCategory(@PageableDefault(sort="id", direction = Sort.Direction.DESC, page =0, size=10)
                                                                    Pageable page, @PathVariable Long categoryID){
        CategoryDTO category = sectionService.getCategorySections(categoryID);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

}
