package br.ufrn.tads.prova.domain.model;


import br.ufrn.tads.prova.domain.interfaces.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity
public class Yatch extends AbstractEntity {

    public Yatch(){

    }
    public Yatch(UUID id,
                 String name,
                 String color,
                 String imagem,
                 String codProduct,
                 BigDecimal price){
        this.setId(id);
        this.name=name;
        this.color=color;
        this.imagem=imagem;
        this.codProduct=codProduct;
        this.price = price;
    }


    private BigDecimal price;

    @NotBlank
    @Column
    private String name;

    @NotBlank
    @Column
    private String color;


    @NotBlank
    @Column
    private String imagem;

    @Pattern(
            regexp = "^PROD-\\d{4}$",
            message = "Código deve estar no formato PROD-0000"
    )
    @NotBlank
    @Column
    private String codProduct;

    private LocalDateTime isDeleted;
}
