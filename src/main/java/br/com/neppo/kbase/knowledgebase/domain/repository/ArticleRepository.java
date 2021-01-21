package br.com.neppo.kbase.knowledgebase.domain.repository;

import br.com.neppo.kbase.knowledgebase.domain.constants.ArticleStatus;
import br.com.neppo.kbase.knowledgebase.domain.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Page<Article> findByArticleStatus(ArticleStatus status, Pageable page);
    Page<Article> findByCreatedBy(Long userId, Pageable page);
}
