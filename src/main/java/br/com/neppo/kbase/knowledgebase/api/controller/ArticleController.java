package br.com.neppo.kbase.knowledgebase.api.controller;

import br.com.neppo.kbase.knowledgebase.api.dto.ArticleDTO;
import br.com.neppo.kbase.knowledgebase.api.form.ArticleForm;
import br.com.neppo.kbase.knowledgebase.api.security.TokenService;
import br.com.neppo.kbase.knowledgebase.domain.model.Article;
import br.com.neppo.kbase.knowledgebase.domain.service.ArticleService;
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
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity<ArticleDTO> createArticle(@Valid @RequestBody ArticleForm articleForm, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        Long id = tokenService.getUserId(token.substring(7, token.length()));
        ArticleDTO article = articleService.createNewArticle(articleForm, id);
        return new ResponseEntity<>(article, HttpStatus.CREATED);
    }

    @GetMapping("/{idArticle}")
    public ResponseEntity<ArticleDTO> readArticle(@PathVariable Long idArticle){
        Article article = articleService.getArticleById(idArticle);
        return new ResponseEntity<>(new ArticleDTO(article), HttpStatus.OK);
    }

    @PatchMapping("/{idArticle}")
    public ResponseEntity<Void> publishArticle(@PathVariable Long idArticle){
        articleService.publishArticle(idArticle);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<ArticleDTO>> getArticleByFilters(){
        // TODO
        return null;
    }

    @DeleteMapping("/{idArticle}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long idArticle, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        Long id = tokenService.getUserId(token.substring(7, token.length()));
        articleService.deleteArticle(idArticle, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/like/{idArticle}")
    public ResponseEntity<ArticleDTO> likeArticle(@PathVariable Long idArticle){
        articleService.likeArticle(idArticle);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<ArticleDTO>> getAllPublishedArticles(@PageableDefault(sort="id", direction = Sort.Direction.DESC, page =0, size=20) Pageable page){
        Page<ArticleDTO> article = articleService.getPublishedArticles(page);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @GetMapping("/draft")
    public ResponseEntity<Page<ArticleDTO>> getAllDraftArticles(@PageableDefault(sort="id", direction = Sort.Direction.DESC, page =0, size=20) Pageable page,
        HttpServletRequest request){
        String token = request.getHeader("Authorization");
        Long id = new TokenService().getUserId(token);
        Page<ArticleDTO> article = articleService.getDraftArticles(page, id);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<Page<ArticleDTO>> getUserArticles(@PageableDefault(sort="id", direction = Sort.Direction.DESC, page =0, size=20) Pageable page
                , HttpServletRequest request){
        String token = request.getHeader("Authorization");
        Long id = tokenService.getUserId(token.substring(7, token.length()));
        Page<ArticleDTO> article = articleService.getUserArticles(page, id);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }
}
