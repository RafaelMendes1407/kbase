package br.com.neppo.kbase.knowledgebase.api.dto;

import br.com.neppo.kbase.knowledgebase.domain.model.Category;
import br.com.neppo.kbase.knowledgebase.domain.model.Tag;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TagDTO {
    private Long id;

    @NotBlank
    private String title;
    private String slug;
    private OffsetDateTime createdAt;
    private UserDTO createdBy;
    private UserDTO updateBy;
    private OffsetDateTime updatedAt;
    private CategoryDTO category;

    public TagDTO(Tag tag){
        this.id = tag.getId();
        this.title = tag.getTitle();
        this.slug = tag.getSlug();
        this.createdAt = tag.getCreatedAt();
        this.createdBy = new UserDTO().converterToUserDTO(tag.getCreatedBy());
        this.updateBy = new UserDTO().converterToUserDTO(tag.getUpdateBy());
        this.updatedAt = tag.getCreatedAt();
        this.category = new CategoryDTO(tag.getCategory());
    }

    public TagDTO optionalTagDTO(Tag tag){
        this.id = tag.getId();
        this.title = tag.getTitle();
        this.slug = tag.getSlug();
        this.createdAt = tag.getCreatedAt();
        this.updatedAt = tag.getCreatedAt();
        return this;
    }

    public static List<TagDTO> convertListTagToListDTO(List<Tag> tags){
        return tags.stream().map(TagDTO::new).collect(Collectors.toList());
    }

    public List<TagDTO> convertToListOptional(List<Tag> tags) {
        return tags.stream().map(e -> new TagDTO().optionalTagDTO(e)).collect(Collectors.toList());
    }
}
