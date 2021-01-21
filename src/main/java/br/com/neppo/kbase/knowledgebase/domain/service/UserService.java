package br.com.neppo.kbase.knowledgebase.domain.service;

import br.com.neppo.kbase.knowledgebase.api.form.UserForm;
import br.com.neppo.kbase.knowledgebase.domain.model.User;
import br.com.neppo.kbase.knowledgebase.domain.repository.UserRepository;
import br.com.neppo.kbase.knowledgebase.domain.service.serviceException.EmailAlreadyRegisteredException;
import br.com.neppo.kbase.knowledgebase.domain.service.serviceException.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User saveUser(User user){
        Optional<User> userEmail = userRepository.findByEmail(user.getEmail());
        if(userEmail.isPresent()){
            if(userEmail.get().getEmail().equals(user.getEmail())){
                throw new EmailAlreadyRegisteredException("Email Already Registered");
            }
        }
        return userRepository.save(user);
    }

    public User updateUser(UserForm userForm, Long id){
        User user = this.selectUser(id);
        if(!user.getName().equals(userForm.getName())){
            user.setName(userForm.getName());
        }
        if(!user.getPassword().equals(userForm.getPassword())){
            user.setPassword(userForm.getPassword());
        }
        if(!user.getDomain().equals(userForm.getDomain())){
            user.setDomain(userForm.getDomain());
        }

        return userRepository.save(user);
    }

    public User selectUser(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        return user.get();
    }

}
