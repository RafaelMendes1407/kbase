package br.com.neppo.kbase.knowledgebase.domain.model;


import br.com.neppo.kbase.knowledgebase.api.form.CategoryForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String slug;

    @OneToOne
    @JoinColumn(referencedColumnName="id")
    private User createdBy;

    @OneToOne
    @JoinColumn(referencedColumnName="id")
    private User updatedBy;
    private OffsetDateTime createAt = OffsetDateTime.now();
    private OffsetDateTime updatedAt;

    @OneToMany(mappedBy = "category")
    private List<Section> section;

    public Category convertFromForm(CategoryForm categoryForm) {
        this.name = categoryForm.getName();
        this.description = categoryForm.getDescription();
        this.slug = categoryForm.getSlug();
        return this;
    }
}
