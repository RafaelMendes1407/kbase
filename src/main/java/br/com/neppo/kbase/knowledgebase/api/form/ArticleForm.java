package br.com.neppo.kbase.knowledgebase.api.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleForm {

    @NotBlank
    private String title;
    private String subtitle;

    @NotBlank
    private String content;

    @NotNull
    private List<Long> categoryId;

    @NotNull
    private Long userId;

    @NotNull
    private Long sectionId;

    private List<Long> tags;
}
