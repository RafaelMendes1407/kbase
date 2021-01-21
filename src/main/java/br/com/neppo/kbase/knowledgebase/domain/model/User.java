package br.com.neppo.kbase.knowledgebase.domain.model;

import br.com.neppo.kbase.knowledgebase.api.form.UserForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
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
    private List<Profile> profile = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.profile;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User (UserForm userForm){
        this.name = userForm.getName();
        this.email = userForm.getEmail();
        this.password = userForm.getPassword();
        this.domain = userForm.getDomain();
    }
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
