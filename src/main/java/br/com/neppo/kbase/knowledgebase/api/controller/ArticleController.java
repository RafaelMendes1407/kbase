package br.com.neppo.kbase.knowledgebase.api.controller;

import br.com.neppo.kbase.knowledgebase.api.dto.ArticleDTO;
import br.com.neppo.kbase.knowledgebase.api.form.ArticleForm;
import br.com.neppo.kbase.knowledgebase.domain.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.Valid;

@RestControllerAdvice
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @PostMapping
    public ResponseEntity<ArticleDTO> createArticle(@Valid @RequestBody ArticleForm articleForm){
        ArticleDTO article = articleService.createNewArticle(articleForm);
        return new ResponseEntity<>(article, HttpStatus.CREATED);
    }
}
