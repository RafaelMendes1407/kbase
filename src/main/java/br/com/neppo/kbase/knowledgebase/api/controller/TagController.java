package br.com.neppo.kbase.knowledgebase.api.controller;

import br.com.neppo.kbase.knowledgebase.api.dto.TagDTO;
import br.com.neppo.kbase.knowledgebase.api.form.TagForm;
import br.com.neppo.kbase.knowledgebase.domain.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.Valid;

@RestControllerAdvice
@RequestMapping("/tags")
public class TagController {

    @Autowired
    TagService tagService;

    @PostMapping
    public ResponseEntity<TagDTO> createNewTag(@Valid @RequestBody TagForm tagForm){
        TagDTO tagDTO = tagService.createNewTag(tagForm);
        return new ResponseEntity<>(tagDTO, HttpStatus.CREATED);
    }

}
