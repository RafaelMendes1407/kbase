package br.com.neppo.kbase.knowledgebase.domain.repository;

import br.com.neppo.kbase.knowledgebase.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
