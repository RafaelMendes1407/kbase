package br.com.neppo.kbase.knowledgebase.api.form;

import br.com.neppo.kbase.knowledgebase.domain.model.Section;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryForm {

    private String name;
    private String description;
    private String slug;
    private Section section;
}
