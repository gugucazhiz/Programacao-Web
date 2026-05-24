package br.ufrn.tads.prova.domain.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
public class UserDTO {

    @Id
    @GeneratedValue
    private UUID id;

    private String username;

    // Senha:
    // mínimo 8 caracteres,
    // 1 letra maiúscula,
    // 1 minúscula,
    // 1 número,
    // 1 caractere especial
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,50}$",
            message = "Senha deve ter ao menos 8 caracteres e no maximo 50, incluindo maiúscula, minúscula, número e caractere especial"
    )
    private String password;

}
