package br.com.neppo.kbase.knowledgebase.domain.service.util;

import br.com.neppo.kbase.knowledgebase.api.form.ArticleForm;
import br.com.neppo.kbase.knowledgebase.domain.model.Article;
import br.com.neppo.kbase.knowledgebase.domain.model.Category;

import java.util.ArrayList;
import java.util.List;

public class ArticleUtil {

    public static Article generateArticle(){
        List<Category> categories = new ArrayList<>();
        categories.add(CategoryUtil.createCategory());
        Article article = new Article();
        article.setTitle("Example");
        article.setSubtitle("Example");
        article.setContent("Example");
        article.setCategory(categories);
        article.setSection(SectionUtil.createSection());
        return article;
    }

    public static ArticleForm gerenateArticleForm(){
        List<Long> categories = new ArrayList<>();
        categories.add(1L);
        ArticleForm articleForm = new ArticleForm();
        articleForm.setTitle("Example");
        articleForm.setSubtitle("Example");
        articleForm.setContent("Example");
        articleForm.setCategoryId(categories);
        articleForm.setSectionId(1L);
        return articleForm;
    }
}
