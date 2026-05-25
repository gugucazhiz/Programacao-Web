package br.ufrn.tads.prova.config;


import br.ufrn.tads.prova.domain.model.Person;
import br.ufrn.tads.prova.domain.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.context.annotation.RequestScope;

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
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/admin/").hasRole("ADMIN")
                        // Regra Geral (Catch-All): Todas as demais exigem autenticação
                        .anyRequest().authenticated()
                )
                // 2. DEFINIÇÃO DE AUTENTICAÇÃO
                // Pede ao Spring para usar proteção via Sessão Web e gerar a página de Login Padrão
                .formLogin(form -> form
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // Rota que aciona o logout
                        .invalidateHttpSession(true) // Invalida a sessão HTTP
                        .clearAuthentication(true) // Limpa o contexto de autenticação
                        .deleteCookies("JSESSIONID") // Deleta o cookie da sessão
                        .logoutSuccessUrl("/login?logout") // Para onde redirecionar após
                );
        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//        // Constrói um usuário padrão do Spring
//        UserDetails administrador = User.builder()
//                .username("admin")
//                // Criptografa a senha em tempo real usando a injeção do encoder
//                .password(encoder.encode(adminPass))
//                .roles("ADMIN")
//                .build();
//        UserDetails visitante = User.builder()
//                .username("user")
//                // Criptografa a senha em tempo real usando a injeção do encoder
//                .password(encoder.encode("senha123"))
//                .roles("USER")
//                .build();
//        // Entrega o usuário construído para o contêiner gerenciar na memória RAM
//        return new InMemoryUserDetailsManager(administrador,visitante);
//    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        // Fator de força (Work Factor) configurável. Por padrão usa-se 10 ou 12.
        return new BCryptPasswordEncoder();
    }

    @Bean
    @RequestScope
    public Person loggedPerson(){
        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();
            if(authentication == null || !authentication.isAuthenticated()){
                return null;
            }
            User user = (User) authentication.getPrincipal();

            return user.getPerson();
    }
}



