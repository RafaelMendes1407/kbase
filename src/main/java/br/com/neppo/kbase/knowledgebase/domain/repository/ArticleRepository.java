package br.com.neppo.kbase.knowledgebase.domain.repository;

import br.com.neppo.kbase.knowledgebase.domain.constants.ArticleStatus;
import br.com.neppo.kbase.knowledgebase.domain.model.Article;
import br.com.neppo.kbase.knowledgebase.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    //Page<Article> findByArticleStatusCreatedBy(ArticleStatus status, User id, Pageable page);
    @Query("SELECT a FROM Article a WHERE a.articleStatus= :status AND a.createdBy =:userId")
    Page<Article> getArticlesByStatusAndUser(ArticleStatus status, User userId, Pageable page);
    Page<Article> findByArticleStatus(ArticleStatus status, Pageable page);
    Page<Article> findByCreatedBy(User userId, Pageable page);
}
