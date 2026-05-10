package ufrn.br.webmvcapp.domain;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    @NotBlank(message = "First Name, Cant be empty.")
    private String firstName;
    @NotBlank (message = "Last Name, Cant be empty.")
    private String lastName;
}
