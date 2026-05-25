package br.ufrn.tads.prova.domain.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity
public class Yatch {

    public Yatch(){

    }
    public Yatch(UUID id,
                 String name,
                 String color,
                 String imagem,
                 String codProduct,
                 BigDecimal price){
        this.id=id;
        this.name=name;
        this.color=color;
        this.imagem=imagem;
        this.codProduct=codProduct;
        this.price = price;
    }

    @Id
    @GeneratedValue (strategy =GenerationType.AUTO)
    private UUID id;

    private BigDecimal price;

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
