package br.ufrn.tads.prova.domain.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity
public class Yatch {

    @Id
    @GeneratedValue (strategy =GenerationType.AUTO)
    private UUID id;

    private Double price;

    @NotBlank
    private String name;

    @NotBlank
    private String color;

    private boolean status;

    @NotBlank
    private String imagem;

    @Pattern(
            regexp = "^PROD-\\d{4}$",
            message = "Código deve estar no formato PROD-0000"
    )
    @NotBlank
    private String codProduct;

    private LocalDateTime isDeleted;
}
