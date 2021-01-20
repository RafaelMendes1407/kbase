package br.com.neppo.kbase.knowledgebase.domain.model;

import br.com.neppo.kbase.knowledgebase.api.form.SectionForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;

@Entity
@Table(name = "section")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;
    private String slug;
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @ManyToOne
    @JoinColumn(referencedColumnName="id", nullable=false)
    private User createdBy;

    @ManyToOne
    @JoinColumn(referencedColumnName="id")
    private User updateBy;
    private OffsetDateTime updatedAt;

    @ManyToOne
    @JoinColumn(referencedColumnName="id", nullable=false)
    private Category category;

    public Section(SectionForm sectionForm){
        this.title = sectionForm.getTitle();
        this.slug = sectionForm.getSlug();
    }

}


//    Como usuário gostaria de cadastrar sesções
//
//    Para o cadastro:
//
//        Nome da seção
//        Cotegorias sob a seção (selecionar a categoria que essa seção vai pertencer)
//        Toda seção deve pertencer a uma categoria

//    É possível editar o nome e qual a categoria pertencente a essa seção.

//    O usuário pode remover uma seção.
//
//        Deve ser notificado que essa ação não pode ser desfeita.
//        Todos os artigos dessa seção serão excluídos.
//        Apresentar a quantidade de artigos que serão excluídos definitivamente.