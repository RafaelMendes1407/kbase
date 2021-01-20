package br.com.neppo.kbase.knowledgebase.api.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagForm {

    @NotBlank
    private String title;
    private String slug;
    @NotNull
    private Long userId;
    @NotNull
    private Long categoryId;
}
