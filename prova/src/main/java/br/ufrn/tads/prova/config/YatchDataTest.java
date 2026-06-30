package br.ufrn.tads.prova.config;

import br.ufrn.tads.prova.domain.model.Yatch;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class YatchDataTest {

    public static List<Yatch> CreateTestYatchs(){
        Date date = new Date();
        Yatch y1 = new Yatch();
        y1.setName("Veleiro Ocean");
        y1.setPrice(BigDecimal.valueOf(250000.0));
        y1.setColor("white");
        y1.setActive(true);
        y1.setCodProduct("PROD-2222");
        y1.setImagem("product01.png");

        Yatch y2 = new Yatch();
        y2.setName("Lancha Speed");
        y2.setPrice(BigDecimal.valueOf(150000.0));
        y2.setColor("white");
        y2.setActive(true);
        y2.setCodProduct("PROD-3345");
        y2.setImagem("product02.png");


        Yatch y3 = new Yatch();
        y3.setName("Barco a Vela Breeze");
        y3.setPrice(BigDecimal.valueOf(89000.0));
        y3.setColor("white");
        y3.setActive(false);
        y3.setCodProduct("PROD-4489");
        y3.setImagem("product03.png");


        return List.of(y1,y2,y3);
    }

    public static Yatch createTestYatch(){
        Date date = new Date();
        Yatch y3 = new Yatch();
        y3.setName("Barco a Vela Breeze");
        y3.setPrice(BigDecimal.valueOf(89000.0));
        y3.setColor("white");
        y3.setActive(false);
        y3.setCodProduct("PROD-4489");
        y3.setImagem("product03.png");

        return y3;
    }
}
