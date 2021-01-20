package br.com.neppo.kbase.knowledgebase.api.dto;

import br.com.neppo.kbase.knowledgebase.domain.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
