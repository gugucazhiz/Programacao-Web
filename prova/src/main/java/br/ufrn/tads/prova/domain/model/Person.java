package br.ufrn.tads.prova.domain.model;


import br.ufrn.tads.prova.domain.interfaces.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Setter
@Getter
@Entity
public class Person extends AbstractEntity {


    @NotBlank
    @Column
    private String name;

    @NotBlank
    @Column
    private String email;

    @Pattern(
            regexp = "^\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}$",
            message = "Telefone deve estar no formato (99) 99999-9999"
    )
    @NotBlank
    @Column
    private String phone;


    @Pattern(
            regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$",
            message = "CPF deve estar no formato 000.000.000-00"
    )
    @NotBlank
    @Column
    private String cpf;

    @OneToOne
    private Product product;

    @OneToOne(mappedBy = "person")
    private User user;
}
