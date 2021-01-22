package br.com.neppo.kbase.knowledgebase.api.controller;

import br.com.neppo.kbase.knowledgebase.api.dto.TagDTO;
import br.com.neppo.kbase.knowledgebase.api.form.TagForm;
import br.com.neppo.kbase.knowledgebase.api.security.TokenService;
import br.com.neppo.kbase.knowledgebase.domain.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestControllerAdvice
@RequestMapping("/tags")
public class TagController {

    @Autowired
    TagService tagService;

    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity<TagDTO> createNewTag(@Valid @RequestBody TagForm tagForm, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        Long id = tokenService.getUserId(token.substring(7, token.length()));
        TagDTO tagDTO = tagService.createNewTag(tagForm, id);
        return new ResponseEntity<>(tagDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TagDTO>> getTags(){
        List<TagDTO> tags = tagService.getTags();
        return ResponseEntity.ok(tags);
    }
}
