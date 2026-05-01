package ufrn.br.webmvcapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    private boolean admin;

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    public User(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
