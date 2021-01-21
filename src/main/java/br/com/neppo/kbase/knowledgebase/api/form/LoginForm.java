package br.com.neppo.kbase.knowledgebase.api.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    public UsernamePasswordAuthenticationToken convertToLogin() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
