package br.com.neppo.kbase.knowledgebase.api.dto;

import br.com.neppo.kbase.knowledgebase.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotBlank
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;

    public UserDTO converterToUserDTO(User user){
        if(user == null){
            return null;
        }
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        return this;
    }

}
