package br.com.neppo.kbase.knowledgebase.domain.model;

import br.com.neppo.kbase.knowledgebase.api.form.ArticleForm;
import br.com.neppo.kbase.knowledgebase.domain.constants.ArticleStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "article")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;
    private String subtitle;

    @NotBlank
    @Column(length = 8000)
    private String content;

    @Enumerated(EnumType.STRING)
    private ArticleStatus articleStatus = ArticleStatus.DRAFT;

    @ManyToMany
    private List<Category> category;

    private OffsetDateTime createdAt = OffsetDateTime.now();

    @OneToOne
    @JoinColumn(referencedColumnName="id", nullable=false)
    private Section section;

    @OneToOne
    @JoinColumn(referencedColumnName="id", nullable=false)
    private User createdBy;

    @OneToOne
    @JoinColumn(referencedColumnName="id")
    private User updateBy;

    private OffsetDateTime updatedAt;
    private Long viewers;
    private Long liked;

    @ManyToMany
    private List<Tag> tags;

    public Article(ArticleForm articleForm){
        this.subtitle = articleForm.getSubtitle();
        this.title = articleForm.getTitle();
        this.content = articleForm.getContent();
    }
}

//    O usuário pode deletar um artigo tanto publicado quanto em rascunho.
//        Como a remoção é permanente, deve ser notificado ao usuário que todos os links
//        e referências ao artigo deletados não serão mais válidos e que essa ação não pode ser desfeita.
//O usuário pode criar artigos dentro de seções ou categorias.
//
//        Todo artigo é criado com status rascunho.
//        Todo artigo deve pertencer a pelo menos uma seção/categoria.
//        Não é permitido criar um artigo fora de uma categoria.
//Possibilidade de editar o conteúdo do artigo.
//
//        Título
//        Descrição (Resumo)
//        Textos
//        Imagens
//        Subtítulos (subseções do artigo)
//        Automaticamente no final de cada artigo deve ser incluido o sistema de avaliação do conteúdo.
//Todo artigo que esteja com status Rascunho pode ser publicado.
//        Publicar signifca disponibilizar o artigo para pesquisa.
//        Todo artigo é criado com status rascunho.