package br.ufrn.tads.prova.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Product {

    public Product(){

    }

    public Product(Yatch yatchsToBuy){
        this.setYatchsToBuy(yatchsToBuy);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany
    private List<Yatch> yatchsToBuy;

    @OneToOne
    private Person person;

    public void setYatchsToBuy(Yatch yatchsToBuy){
        this.yatchsToBuy.add(yatchsToBuy);
    }
}
