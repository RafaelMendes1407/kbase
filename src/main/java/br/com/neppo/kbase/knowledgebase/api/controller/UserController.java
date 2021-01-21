package br.com.neppo.kbase.knowledgebase.api.controller;

import br.com.neppo.kbase.knowledgebase.api.dto.UserDTO;
import br.com.neppo.kbase.knowledgebase.api.form.UserForm;
import br.com.neppo.kbase.knowledgebase.api.security.TokenService;
import br.com.neppo.kbase.knowledgebase.domain.model.User;
import br.com.neppo.kbase.knowledgebase.domain.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    TokenService tokenService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserForm userForm) {
        User userSaved = userService.saveUser(userForm);
        return new ResponseEntity<UserDTO>(modelMapper.map(userSaved, UserDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserForm userForm, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Long id = new TokenService().getUserId(token);
        User userUpdated = userService.updateUser(userForm, id);
        return new ResponseEntity<UserDTO>(modelMapper.map(userUpdated, UserDTO.class), HttpStatus.OK);
    }
}
