package br.ufrn.tads.prova.config;

import br.ufrn.tads.prova.domain.model.Person;
import br.ufrn.tads.prova.domain.model.User;
import br.ufrn.tads.prova.domain.model.Yatch;
import br.ufrn.tads.prova.repository.PersonRepository;
import br.ufrn.tads.prova.repository.UserRepository;
import br.ufrn.tads.prova.repository.YatchRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DataLoader {
    //para nao ter que usar uma api e dificultar
    //na hora de subir essa aplicação em outro local
    //
    //vou usar o dataloader com o create-drop


    @Value("${server.custom.admin.password}")
    private String adminPass;

    public DataLoader(SecurityConfig securityConfig){
    }



    @Bean
    CommandLineRunner initUsersDatabase(UserRepository userRepository,
                                        PersonRepository personRepository,
                                        PasswordEncoder passwordEncoder){
        return args -> {
            User user = new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode(adminPass));
            user.setRole("ADMIN");

            Person person = new Person();
            person.setName("Administrador");
            person.setCpf("123.456.789-00");
            person.setPhone("(84) 99999-9999");
            person.setEmail("gustavo_msn_@hotmail.com");

            user.setPerson(person);
            person.setUser(user);

            //////////////////////////
            User user2 = new User();
            user2.setUsername("user");
            user2.setPassword(passwordEncoder.encode(adminPass));
            user2.setRole("USER");

            Person person2 = new Person();
            person2.setName("User");
            person2.setCpf("123.456.000-00");
            person2.setPhone("(99) 99999-9999");
            person2.setEmail("gustavo_msn_@hotmail.com");

            user2.setPerson(person2);
            person2.setUser(user2);

            userRepository.saveAll(List.of(
                    user,user2
            ));
            personRepository.saveAll(List.of(
                    person,person2
            ));
            System.out.println(adminPass);
        };
    }

    //dados mockados
    @Bean
    CommandLineRunner initDatabase(YatchRepository repository){
        return args -> {
            // Produto 1
            Yatch y1 = new Yatch();
            y1.setName("Veleiro Ocean");
            y1.setPrice(BigDecimal.valueOf(250000.0));
            y1.setColor("white");
            y1.setStatus(true);
            y1.setCodProduct("PROD-2222");
            y1.setImagem("product01.png");

            // Produto 2
            Yatch y2 = new Yatch();
            y2.setName("Lancha Speed");
            y2.setPrice(BigDecimal.valueOf(150000.0));
            y2.setColor("white");
            y2.setStatus(true);
            y2.setCodProduct("PROD-3345");
            y2.setImagem("product02.png");

// Produto 3
            Yatch y3 = new Yatch();
            y3.setName("Barco a Vela Breeze");
            y3.setPrice(BigDecimal.valueOf(89000.0));
            y3.setColor("white");
            y3.setStatus(false);
            y3.setCodProduct("PROD-4489");
            y3.setImagem("product03.png");

// Produto 4
            Yatch y4 = new Yatch();
            y4.setName("Iate Luxo");
            y4.setPrice(BigDecimal.valueOf(750000.0));
            y4.setColor("white");
            y4.setStatus(true);
            y4.setCodProduct("PROD-5512");
            y4.setImagem("product04.png");

// Produto 5
            Yatch y5 = new Yatch();
            y5.setName("Jet Ski Aqua");
            y5.setPrice(BigDecimal.valueOf(32000.0));
            y5.setColor("white");
            y5.setStatus(true);
            y5.setCodProduct("PROD-6678");
            y5.setImagem("product05.png");

// Produto 6
            Yatch y6 = new Yatch();
            y6.setName("Canoa Rápida");
            y6.setPrice(BigDecimal.valueOf(12500.0));
            y6.setColor("white");
            y6.setStatus(false);
            y6.setCodProduct("PROD-7734");
            y6.setImagem("product06.png");

// Produto 7
            Yatch y7 = new Yatch();
            y7.setName("Barco Pescador");
            y7.setPrice(BigDecimal.valueOf(45000.0));
            y7.setColor("white");
            y7.setStatus(true);
            y7.setCodProduct("PROD-8891");
            y7.setImagem("product07.png");

// Produto 8
            Yatch y8 = new Yatch();
            y8.setName("Inflável Explorer");
            y8.setPrice(BigDecimal.valueOf(8900.0));
            y8.setColor("white");
            y8.setStatus(true);
            y8.setCodProduct("PROD-9903");
            y8.setImagem("product08.png");

// Produto 9
            Yatch y9 = new Yatch();
            y9.setName("Catamarã Duo");
            y9.setPrice(BigDecimal.valueOf(320000.0));
            y9.setColor("white");
            y9.setStatus(false);
            y9.setCodProduct("PROD-1015");
            y9.setImagem("product09.png");

// Produto 10
            Yatch y10 = new Yatch();
            y10.setName("Hovercraft Turbo");
            y10.setPrice(BigDecimal.valueOf(420000.0));
            y10.setColor("white");
            y10.setStatus(true);
            y10.setCodProduct("PROD-1127");
            y10.setImagem("product10.png");

            repository.saveAll(List.of(
                    y1,y2,y3,y4,y5,
                    y6,y7,y8,y9,y10
            ));
        };
    }
}
