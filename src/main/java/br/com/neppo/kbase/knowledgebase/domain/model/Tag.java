package br.com.neppo.kbase.knowledgebase.domain.model;

import br.com.neppo.kbase.knowledgebase.api.form.TagForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;

@Entity
@Table(name = "tag")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;
    private String slug;
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @OneToOne
    @JoinColumn(referencedColumnName="id")
    private User createdBy;

    @OneToOne
    @JoinColumn(referencedColumnName="id")
    private User updateBy;
    private OffsetDateTime updatedAt;

    @ManyToOne
    private Category category;

    public Tag(TagForm tagForm){
        this.title = tagForm.getTitle();
        this.slug = tagForm.getSlug();
    }

}

//    O sistema deve permitir o cadastro de tags que serão utilizadas para classificação de artigos
//        e na indexação do conteúdo.
//        As tags devem seguir um padrão: palavras unicas (ex: tracasenha) e sem caracteres especiais
// O usuário pode editar uma tag seguindo as mesmas regras de criação:
//
//As tags devem seguir um padrão: palavras unicas (ex: tracasenha) e sem caracteres especiais.
//
//Os usuários deverão ser notificados do impacto dessa edição apresentando a quantidade de artigos
// impactados com o uso das tags.

//    O usuário pode remover uma tag.
//
//        Se essa tag estiver sendo utilizada em algum lugar o usuário deve
//        ser notificado da quantidade de artigos que utilizam a tag, pois os artigos
//        perderão a referência as essas tags.

