package br.com.neppo.kbase.knowledgebase.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "tag")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String slug; // ??
    private OffsetDateTime createdAt;
    private User createdBy;
    private User updateBy;
    private OffsetDateTime updatedAt;
    private Section section;


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

