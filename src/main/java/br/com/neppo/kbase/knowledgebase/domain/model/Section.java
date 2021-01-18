package br.com.neppo.kbase.knowledgebase.domain.model;

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
    @GeneratedValue
    private Long id;

    @NotBlank
    private String title;
    private String subtitle;
    private String slug;
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @ManyToOne
    private User createdBy;

    @ManyToOne
    private User updateBy;
    private OffsetDateTime updatedAt;

    @ManyToOne
    private Category category;

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