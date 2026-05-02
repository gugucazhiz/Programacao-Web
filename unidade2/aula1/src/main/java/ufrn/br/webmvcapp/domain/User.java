package ufrn.br.webmvcapp.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Data
@Getter
@Setter
@ToString
@Entity(name = "users")
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    @ColumnDefault("false")
    private boolean admin;

    @NotBlank
    private String firstName;
    @NotBlank //not blank é melhor pois cobre os casos de notNull e os casos de " "
    private String lastName;

    public User(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
