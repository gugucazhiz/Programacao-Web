package br.ufrn.tads.prova.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Product {
    public Product() {

    }
    public Product(Yatch yatchsIncomingToBuy){
        this.setYatchsToBuy(yatchsIncomingToBuy);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private BigDecimal subTotal = BigDecimal.valueOf(0.0);

    @ManyToMany
    private List<Yatch> yatchsToBuy = new ArrayList<>();

    @OneToOne(cascade = CascadeType.PERSIST)
    private Person person;

    public void setYatchsToBuy(Yatch yatch){
        this.yatchsToBuy.add(yatch);
    }
}
