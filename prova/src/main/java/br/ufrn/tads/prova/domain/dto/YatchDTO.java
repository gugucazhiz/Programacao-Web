package br.ufrn.tads.prova.domain.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class YatchDTO {

    public UUID id;
}
