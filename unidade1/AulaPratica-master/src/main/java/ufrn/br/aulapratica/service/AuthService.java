package ufrn.br.aulapratica.service;

import org.springframework.stereotype.Service;
import ufrn.br.aulapratica.repository.LoginRepository;

@Service
public class AuthService {
    LoginRepository loginRepository;

    public AuthService(LoginRepository loginRepository){
        this.loginRepository = loginRepository;
    }

    public boolean loginRequest(String email, String senha){
        // Simulação do Banco de Dados
            return "admin@ufrn.br".equals(email) && "senha123".equals(senha);
    }
}
