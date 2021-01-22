package br.com.neppo.kbase.knowledgebase.domain.service;

import br.com.neppo.kbase.knowledgebase.api.dto.ArticleDTO;
import br.com.neppo.kbase.knowledgebase.api.form.ArticleForm;
import br.com.neppo.kbase.knowledgebase.domain.constants.ArticleStatus;
import br.com.neppo.kbase.knowledgebase.domain.model.*;
import br.com.neppo.kbase.knowledgebase.domain.repository.ArticleRepository;
import br.com.neppo.kbase.knowledgebase.domain.service.serviceException.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private ArticleRepository articleRepository;
    private UserService userService;
    private CategoryService categoryService;
    private TagService tagService;
    private SectionService sectionService;


    @Autowired
    public ArticleService(ArticleRepository articleRepository, UserService userService,
                          CategoryService categoryService, TagService tagService,
                          SectionService sectionService) {
        this.articleRepository = articleRepository;
        this.userService = userService;
        this.categoryService = categoryService;
        this.tagService = tagService;
        this.sectionService = sectionService;
    }


    public ArticleDTO createNewArticle(ArticleForm articleForm, Long id) {
        List<Category> category = categoryService.getListCategories(articleForm.getCategoryId());
        User user = userService.selectUser(id);
        Section section = sectionService.getSection(articleForm.getSectionId());
        List<Tag> tags = tagService.getListTags(articleForm.getTags());
        Article article = new Article(articleForm);
        article.setCategory(category);
        article.setCreatedBy(user);
        article.setTags(tags);
        article.setSection(section);
        return new ArticleDTO(articleRepository.save(article));
    }

    public Article getArticleById(Long idArticle) {
        Optional<Article> article = articleRepository.findById(idArticle);
        if(article.isEmpty()){
            throw new ResourceNotFoundException("Article not found");
        }
        Long views = article.get().getViewers();
        if(views == null){
            views = 0L;
        }
        article.get().setViewers(views+=1);
        articleRepository.save(article.get());
        return article.get();
    }

    public void publishArticle(Long idArticle) {
        Article article = this.getArticleById(idArticle);
        article.setArticleStatus(ArticleStatus.PUBLISH);
        articleRepository.save(article);
    }

    public void deleteArticle(Long idArticle, Long userId) {
        User user = userService.selectUser(userId);
        Article article = this.getArticleById(idArticle);
        if(user.getId().equals(article.getCreatedBy())){
            articleRepository.deleteById(idArticle);
        }
        throw new ResourceNotFoundException("Only the author can exclude this article");
    }

    public void likeArticle(Long idArticle) {
        Article article = this.getArticleById(idArticle);
        Long likes = article.getLiked();
        if(likes == null){
            likes = 0L;
        }
        article.setLiked(likes += 1);
        articleRepository.save(article);
    }

    public Page<ArticleDTO> getPublishedArticles(Pageable page) {
        Page<Article> articles = articleRepository.findByArticleStatus(ArticleStatus.PUBLISH, page);
        return ArticleDTO.convertArticlesToPage(articles);
    }

    public Page<ArticleDTO> getDraftArticles(Pageable page, Long id) {
        User user = userService.selectUser(id);
        Page<Article> articles = articleRepository.getArticlesByStatusAndUser(ArticleStatus.DRAFT, user, page);
        return ArticleDTO.convertArticlesToPage(articles);
    }

    public Page<ArticleDTO> getUserArticles(Pageable page, Long id) {
        User user = userService.selectUser(id);
        Page<Article> articles = articleRepository.findByCreatedBy(user, page);
        return ArticleDTO.convertArticlesToPage(articles);
    }
}
