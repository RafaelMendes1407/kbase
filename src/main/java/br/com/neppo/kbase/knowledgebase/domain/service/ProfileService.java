package br.com.neppo.kbase.knowledgebase.domain.service;

import br.com.neppo.kbase.knowledgebase.domain.model.Profile;
import br.com.neppo.kbase.knowledgebase.domain.repository.ProfileRepository;
import br.com.neppo.kbase.knowledgebase.domain.service.serviceException.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {
    private ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository){
        this.profileRepository = profileRepository;
    }

    public Profile getProfileById(Long id){
        Optional<Profile> profile = profileRepository.findById(id);
        if(profile.isPresent()){
            return profile.get();
        }
        else throw new ResourceNotFoundException("Profile does not exist");
    }
}
