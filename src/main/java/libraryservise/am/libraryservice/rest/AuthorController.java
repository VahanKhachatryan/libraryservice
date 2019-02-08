package libraryservise.am.libraryservice.rest;


import libraryservise.am.libraryservice.model.Author;
import libraryservise.am.libraryservice.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rest/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    public ResponseEntity getAllAuthor() {
        return ResponseEntity.ok(authorRepository.findAll());
    }

    @PostMapping()
    public ResponseEntity saveAuthor(@RequestBody Author author) {
        authorRepository.save(author);
        return ResponseEntity.ok("created");
    }

    @GetMapping("/{authorId}")
    public ResponseEntity getAuthorById(@PathVariable(name = "authorId") int authorId){
        Author one = authorRepository.findOneById(authorId);
        if (one==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Author id "+authorId+" id not found");
        }else {
            return ResponseEntity.ok(one);
        }

    }

}
