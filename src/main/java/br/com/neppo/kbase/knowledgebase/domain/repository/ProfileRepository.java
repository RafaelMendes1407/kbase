package br.com.neppo.kbase.knowledgebase.domain.repository;

import br.com.neppo.kbase.knowledgebase.domain.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
