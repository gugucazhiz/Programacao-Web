//package ufrn.br.webmvcapp.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration {
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        return http
//                .authorizeHttpRequests( auth -> {
//                    auth.requestMatchers("/users/create").hasRole("ADMIN");
//                    auth.anyRequest().authenticated();
//                })
//                .formLogin(Customizer.withDefaults())
//                .logout(Customizer.withDefaults())
//                .build();
//    }
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(){
//        UserDetails user = User.withUsername("user")
//                .password("{noop}password")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.withUsername("admin")
//                .password("{noop}password")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user,admin);
//    }
//}




//EXEMPLO ANTIGO DE ABSTRACAO DO FILTER CHAIN PARA ENTENDER COMO FUNCIONA, NAO É FEITO ASSIM EM PRODUCAO
