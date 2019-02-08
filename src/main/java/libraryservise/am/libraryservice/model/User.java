package libraryservise.am.libraryservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    @Column
    private int id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private boolean verify;
}
