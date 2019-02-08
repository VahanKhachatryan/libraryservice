package libraryservise.am.libraryservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue
    @Column
    private int id;
    @Column
    private String title;
    @Column
    private int price;
    @ManyToOne
    private Author author;

}
