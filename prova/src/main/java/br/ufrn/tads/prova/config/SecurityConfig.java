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


    //Creditos totais ao codigo do filterchain para o professor taniro
    //pois o mesmo foi totalmente feito com base nos ensinamentos de suas aulas
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/admin/").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/login?logout")
                );
        return http.build();
    }

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



