package libraryservise.am.libraryservice.repository;

import libraryservise.am.libraryservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findOneByEmail(String email);
}
