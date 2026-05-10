package ufrn.br.webmvcapp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        // Constrói um usuário padrão do Spring
        UserDetails administrador = User.builder()
                .username("admin")
                // Criptografa a senha em tempo real usando a injeção do encoder
                .password(encoder.encode("senha123"))
                .roles("ADMIN")
                .build();
        UserDetails visitante = User.builder()
                .username("user")
                // Criptografa a senha em tempo real usando a injeção do encoder
                .password(encoder.encode("senha123"))
                .roles("USER")
                .build();
        // Entrega o usuário construído para o contêiner gerenciar na memória RAM
        return new InMemoryUserDetailsManager(administrador,visitante);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        // Fator de força (Work Factor) configurável. Por padrão usa-se 10 ou 12.
        return new BCryptPasswordEncoder();
    }
}
