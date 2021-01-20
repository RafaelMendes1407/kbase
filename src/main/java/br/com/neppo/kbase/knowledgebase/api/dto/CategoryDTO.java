package br.com.neppo.kbase.knowledgebase.api.dto;

import br.com.neppo.kbase.knowledgebase.domain.model.Category;
import br.com.neppo.kbase.knowledgebase.domain.model.Section;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO {

    private Long id;
    private String name;
    private String description;
    private String slug;
    private UserDTO createdBy;
    private UserDTO updatedBy;
    private OffsetDateTime createAt = OffsetDateTime.now();
    private OffsetDateTime updatedAt;
    private List<SectionDTO> section;



    public CategoryDTO (Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
        this.slug = category.getSlug();
        this.createdBy = new UserDTO().converterToUserDTO(category.getCreatedBy());
        this.createAt = category.getCreateAt();
        this.updatedBy = new UserDTO().converterToUserDTO(category.getUpdatedBy());;
        this.updatedAt = category.getUpdatedAt();
        this.section = new SectionDTO().convertTOSectionDTO(category.getSection());
    }

    public CategoryDTO optionalCategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
        this.slug = category.getSlug();
        return this;
    }

    public static Page<CategoryDTO> convertToPage(Page<Category> categories) {
        return categories.map(CategoryDTO::new);
    }

    public static List<CategoryDTO> convertToList(List<Category> categories) {
        return categories.stream().map(CategoryDTO::new).collect(Collectors.toList());
    }

    public List<CategoryDTO> convertToListOptional(List<Category> categories) {
        return categories.stream().map(e -> new CategoryDTO().optionalCategoryDTO(e)).collect(Collectors.toList());
    }

}
