package br.com.neppo.kbase.knowledgebase.domain.service;

import br.com.neppo.kbase.knowledgebase.domain.model.User;
import br.com.neppo.kbase.knowledgebase.domain.repository.UserRepository;
import br.com.neppo.kbase.knowledgebase.domain.service.serviceException.EmailAlreadyRegisteredException;
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

    public User updateUser(User user){
        return userRepository.save(user);
    }

    public Optional<User> selectUser(Long id){
        return userRepository.findById(id);
    }

}
