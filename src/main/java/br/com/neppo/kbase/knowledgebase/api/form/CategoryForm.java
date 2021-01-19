package br.com.neppo.kbase.knowledgebase.api.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryForm {

    @NotBlank
    private String name;
    @NotNull
    private Long userId;
    private String description;
    private String slug;

}
