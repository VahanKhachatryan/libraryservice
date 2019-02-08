package libraryservise.am.libraryservice.rest;

import libraryservise.am.libraryservice.jwt.JwtTokenUtil;
import libraryservise.am.libraryservice.model.Author;
import libraryservise.am.libraryservice.model.Book;
import libraryservise.am.libraryservice.repository.AuthorRepository;
import libraryservise.am.libraryservice.repository.BookRepository;
import libraryservise.am.libraryservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rest/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;
    @GetMapping
    public ResponseEntity getAllBook() {
        return ResponseEntity.ok(bookRepository.findAll());
    }

    @PostMapping
    public ResponseEntity saveBook(@RequestBody Book book) {
        bookRepository.save(book);
        return ResponseEntity.ok("created");
    }

    @GetMapping("/{authorId}")
    public ResponseEntity getBookByAuthorId(@PathVariable(name = "authorId") int authorId) {
        Book one = bookRepository.findOneByAuthor_Id(authorId);
        if (one == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Book with " + authorId + " authorId not found");
        } else {
            return ResponseEntity.ok(one);
        }
    }
}
