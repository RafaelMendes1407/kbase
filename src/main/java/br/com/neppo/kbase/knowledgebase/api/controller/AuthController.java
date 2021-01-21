package br.com.neppo.kbase.knowledgebase.api.controller;

import br.com.neppo.kbase.knowledgebase.api.dto.TokenDTO;
import br.com.neppo.kbase.knowledgebase.api.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import br.com.neppo.kbase.knowledgebase.api.core.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDTO> auth(@Valid @RequestBody LoginForm loginForm){
        UsernamePasswordAuthenticationToken login = loginForm.convertToLogin();

        try{
            Authentication auth = authManager.authenticate(login);
            String token = tokenService.generateToken(auth);
            return ResponseEntity.ok(new TokenDTO(token, "Bearer "));
        }catch (AuthenticationException ex){
            return ResponseEntity.badRequest().build();
        }
    }
}
