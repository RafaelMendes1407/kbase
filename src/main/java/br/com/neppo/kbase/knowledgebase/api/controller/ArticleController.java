package br.com.neppo.kbase.knowledgebase.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequestMapping("/articles")
public class ArticleController {

    @PostMapping
    public ResponseEntity<ArticleDTO> createArticle(@Valid @RequestMapping ArtigoForm artigoForm){

    }
}
