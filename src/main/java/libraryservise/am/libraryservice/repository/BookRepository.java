package libraryservise.am.libraryservice.repository;

import libraryservise.am.libraryservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findOneByAuthor_Id(int authorId);
}
