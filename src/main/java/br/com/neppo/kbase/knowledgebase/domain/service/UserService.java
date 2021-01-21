package br.com.neppo.kbase.knowledgebase.domain.service;

import br.com.neppo.kbase.knowledgebase.api.form.UserForm;
import br.com.neppo.kbase.knowledgebase.domain.model.Profile;
import br.com.neppo.kbase.knowledgebase.domain.model.User;
import br.com.neppo.kbase.knowledgebase.domain.repository.UserRepository;
import br.com.neppo.kbase.knowledgebase.domain.service.serviceException.EmailAlreadyRegisteredException;
import br.com.neppo.kbase.knowledgebase.domain.service.serviceException.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private ProfileService profileService;
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, ProfileService profileService){
        this.userRepository = userRepository;
        this.profileService = profileService;
    }

    public User saveUser(UserForm userForm){
        Optional<User> userEmail = userRepository.findByEmail(userForm.getEmail());
        if(userEmail.isPresent()){
            if(userEmail.get().getEmail().equals(userForm.getEmail())){
                throw new EmailAlreadyRegisteredException("Email Already Registered");
            }
        }
        User newUser = new User(userForm);

        if(userForm.getProfile() != null){
            Profile profile = profileService.getProfileById(userForm.getProfile());
            List<Profile> profiles = new ArrayList<>();
            profiles.add(profile);
            newUser.setProfile(profiles);
        }
        newUser.setPassword(new BCryptPasswordEncoder().encode(newUser.getPassword()));
        return userRepository.save(newUser);
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
