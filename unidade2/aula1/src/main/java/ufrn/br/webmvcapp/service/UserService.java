package ufrn.br.webmvcapp.service;

import org.springframework.stereotype.Service;
import ufrn.br.webmvcapp.domain.User;
import ufrn.br.webmvcapp.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    UserRepository repository;

    public UserService(UserRepository userRepository){
        this.repository = userRepository;
    }

    public User salvar(User user){
        return repository.save(user);
    }
    public List<User> listUser(){
        return repository.findAll();
    }
}
