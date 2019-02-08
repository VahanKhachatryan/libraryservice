package libraryservise.am.libraryservice.repository;

import libraryservise.am.libraryservice.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
Author findOneById(int id);
}
