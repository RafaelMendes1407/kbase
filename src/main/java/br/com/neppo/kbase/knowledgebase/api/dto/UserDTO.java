package br.com.neppo.kbase.knowledgebase.api.dto;

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

}
