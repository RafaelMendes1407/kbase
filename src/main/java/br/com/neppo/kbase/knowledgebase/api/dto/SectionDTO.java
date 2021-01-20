package br.com.neppo.kbase.knowledgebase.api.dto;

import br.com.neppo.kbase.knowledgebase.domain.model.Section;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SectionDTO {

    private Long id;
    private String title;
    private String slug;
    private OffsetDateTime createdAt;
    private UserDTO createdBy;
    private UserDTO updateBy;
    private OffsetDateTime updatedAt;
    private CategoryDTO category;

    public SectionDTO(Section section) {
        this.id = section.getId();
        this.title = section.getTitle();
        this.slug = section.getSlug();
        this.createdBy = new UserDTO().converterToUserDTO(section.getCreatedBy());
        this.updateBy = new UserDTO().converterToUserDTO(section.getUpdateBy());
        this.createdAt = section.getCreatedAt();
        this.updatedAt = section.getUpdatedAt();
        this.category = new CategoryDTO(section.getCategory());
    }

    public SectionDTO optionalSectionDTO(Section section){
        this.id = section.getId();
        this.title = section.getTitle();
        this.slug = section.getSlug();
        this.createdBy = new UserDTO().converterToUserDTO(section.getCreatedBy());
        this.updateBy = new UserDTO().converterToUserDTO(section.getUpdateBy());
        this.createdAt = section.getCreatedAt();
        this.updatedAt = section.getUpdatedAt();
        return this;
    }

    public List<SectionDTO> convertTOSectionDTO(List<Section> section) {
        return section.stream().map(e -> new SectionDTO().optionalSectionDTO(e)).collect(Collectors.toList());
    }
}
