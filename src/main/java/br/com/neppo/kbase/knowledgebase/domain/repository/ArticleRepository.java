package br.com.neppo.kbase.knowledgebase.domain.repository;

import br.com.neppo.kbase.knowledgebase.domain.constants.ArticleStatus;
import br.com.neppo.kbase.knowledgebase.domain.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query("SELECT a FROM Article a WHERE articleStatus = :status AND createdBy=:id")
    Page<Article> findByArticleStatusCreatedBy(@Param("status") ArticleStatus status, @Param("id") Long id, Pageable page);
    Page<Article> findByArticleStatus(ArticleStatus status, Pageable page);
    Page<Article> findByCreatedBy(Long userId, Pageable page);
}
