package libraryservise.am.libraryservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue
    @Column
    private int id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private int age;
}
