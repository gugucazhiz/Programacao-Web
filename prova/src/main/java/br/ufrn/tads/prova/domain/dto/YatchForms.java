package br.ufrn.tads.prova.domain.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class YatchForms {

    private UUID id;
}
