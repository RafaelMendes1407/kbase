package br.com.neppo.kbase.knowledgebase.domain.service;

import br.com.neppo.kbase.knowledgebase.api.dto.ArticleDTO;
import br.com.neppo.kbase.knowledgebase.api.form.ArticleForm;
import br.com.neppo.kbase.knowledgebase.domain.model.*;
import br.com.neppo.kbase.knowledgebase.domain.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public ArticleDTO createNewArticle(ArticleForm articleForm) {
        List<Category> category = categoryService.getListCategories(articleForm.getCategoryId());
        User user = userService.selectUser(articleForm.getUserId());
        Section section = sectionService.getSection(articleForm.getSectionId());
        List<Tag> tags = tagService.getListTags(articleForm.getTags());
        Article article = new Article(articleForm);
        article.setCategory(category);
        article.setCreatedBy(user);
        article.setTags(tags);
        article.setSection(section);
        return new ArticleDTO(articleRepository.save(article));
    }
}
