package ufrn.br.webmvcapp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. DEFINIÇÃO DE AUTORIZAÇÃO DE ROTAS
                .authorizeHttpRequests(authz -> authz
                        // Regra Específica: A pasta de CSS é pública (Allowlist)
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/users/create").hasRole("ADMIN")
                        // Regra Geral (Catch-All): Todas as demais exigem autenticação
                        .anyRequest().authenticated()
                )
                // 2. DEFINIÇÃO DE AUTENTICAÇÃO
                // Pede ao Spring para usar proteção via Sessão Web e gerar a página de Login Padrão
                .formLogin(form -> form
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                );
        return http.build();
    }

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
