package br.com.neppo.kbase.knowledgebase.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Length(min = 5)
    private String name;

    @Email
    private String email;

    @NotBlank
    private String domain;

    @NotBlank
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Profile> profile;

}

//    O usuário deve ser capaz de fazer o seu cadastro e criar o seu próprio domínio.
//
//        Dados para cadastro:
//
//        Nome Completo
//        Email
////        Domínio
////        Senha
//Incluir:
//
//        Termo de politica de privacidade
//O administrador poderá visualizar a base de conhecimento.
//
//        Nome do artigo
//        Status (publicado, rascunho, etc)
//        Data da ultima alteração
//        Autor
//        Quantidade de visualizações
//        Quantidade e percentual de avlaiações positivas
//        Quantidade e percentual de avaliações neutras
//        Quantidade e percentual de avaliações negativas
