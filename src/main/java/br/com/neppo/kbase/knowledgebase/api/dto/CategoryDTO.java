package br.com.neppo.kbase.knowledgebase.api.dto;

import br.com.neppo.kbase.knowledgebase.domain.model.Category;
import br.com.neppo.kbase.knowledgebase.domain.model.Section;
import br.com.neppo.kbase.knowledgebase.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CategoryDTO {

    @Autowired
    ModelMapper modelMapper;

    private Long id;
    private String name;
    private String description;
    private String slug;
    private UserDTO createdBy;
    private UserDTO updatedBy;
    private OffsetDateTime createAt = OffsetDateTime.now();
    private OffsetDateTime updatedAt;
    private List<Section> section;

    public CategoryDTO convertTo(Category category){
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
        this.slug = category.getSlug();
        this.createdBy = modelMapper.map(category.getCreatedBy(), UserDTO.class);
        this.createAt = category.getCreateAt();
        this.updatedBy = modelMapper.map(category.getUpdatedBy(), UserDTO.class);

        this.updatedAt = category.getUpdatedAt();
        this.section = category.getSection();
        return this;
    }
}
