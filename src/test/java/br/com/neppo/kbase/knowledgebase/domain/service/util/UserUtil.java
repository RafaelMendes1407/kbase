package br.com.neppo.kbase.knowledgebase.domain.service.util;

import br.com.neppo.kbase.knowledgebase.api.form.UserForm;
import br.com.neppo.kbase.knowledgebase.domain.model.User;

public class UserUtil {
    public static User createUser(){
        User user = new User();
        user.setId(1L);
        user.setName("User 1");
        user.setEmail("email@email.com");
        user.setPassword("123456");
        user.setDomain("Domain");
        return user;
    }

    public static UserForm userForm(){
        UserForm userForm = new UserForm();
        userForm.setName("User 1");
        userForm.setEmail("email@email.com");
        userForm.setPassword("123456");
        userForm.setDomain("Domain");
        userForm.setProfile(1L);
        return userForm;
    }
}
