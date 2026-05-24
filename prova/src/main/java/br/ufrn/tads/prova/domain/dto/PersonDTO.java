package br.ufrn.tads.prova.domain.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String email;


    @NotBlank
    private String phone;
}
