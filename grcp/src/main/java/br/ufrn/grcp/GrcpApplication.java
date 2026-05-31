package br.ufrn.grcp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class GrcpApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrcpApplication.class, args);
    }

}
