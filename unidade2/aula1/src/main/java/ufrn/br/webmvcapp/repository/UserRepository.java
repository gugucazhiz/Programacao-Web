package ufrn.br.webmvcapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ufrn.br.webmvcapp.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
