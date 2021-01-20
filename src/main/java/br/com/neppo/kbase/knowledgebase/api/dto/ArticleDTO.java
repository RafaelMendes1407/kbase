package br.com.neppo.kbase.knowledgebase.api.dto;

import br.com.neppo.kbase.knowledgebase.domain.constants.ArticleStatus;
import br.com.neppo.kbase.knowledgebase.domain.model.Article;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleDTO {
    private Long id;
    private String title;
    private String subtitle;
    private String content;
    private ArticleStatus articleStatus;
    private List<CategoryDTO> category;
    private OffsetDateTime createdAt;
    private UserDTO createdBy;
    private UserDTO updateBy;
    private OffsetDateTime updatedAt;
    private Long viewers;
    private Long liked;
    private List<TagDTO> tags;

    public ArticleDTO(Article article){
        this.id = article.getId();
        this.title = article.getTitle();
        this.subtitle = article.getSubtitle();
        this.content = article.getContent();
        this.articleStatus = article.getArticleStatus();
        this.category = new CategoryDTO().convertToListOptional(article.getCategory());
        this.createdAt = article.getCreatedAt();
        this.createdBy = new UserDTO().converterToUserDTO(article.getCreatedBy());
        this.updateBy = new UserDTO().converterToUserDTO(article.getCreatedBy());;
        this.updatedAt = article.getUpdatedAt();
        this.viewers = article.getViewers();
        this.liked = article.getLiked();
        this.tags = new TagDTO().convertToListOptional(article.getTags());
    }
}
