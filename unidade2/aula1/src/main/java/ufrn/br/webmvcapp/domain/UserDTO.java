package ufrn.br.webmvcapp.domain;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {

    @NotNull (message = "First Name, Cant be empty.")
    private String firstName;
    @NotNull (message = "Last Name, Cant be empty.")
    private String lastName;
}
