package br.ufrn.tads.prova.domain.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
public class YatchDTO {


    private UUID id;

    @NotBlank
    private String name;

    private BigDecimal price;

    @NotBlank
    private String color;

    @NotBlank
    private String imagem;

    @Pattern(
            regexp = "^PROD-\\d{4}$",
            message = "Código deve estar no formato PROD-0000"
    )
    @NotBlank
    private String codProduct;
}
