package libraryservise.am.libraryservice.rest;

import io.swagger.annotations.ApiOperation;
import libraryservise.am.libraryservice.jwt.JwtTokenUtil;
import libraryservise.am.libraryservice.mail.EmailServiceImpl;
import libraryservise.am.libraryservice.model.JwtAuthenticationRequest;
import libraryservise.am.libraryservice.model.User;
import libraryservise.am.libraryservice.repository.UserRepository;
import libraryservise.am.libraryservice.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rest/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailServiceImpl emailService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    private UserDetailsService userDetailsService;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        // Return the token
        return ResponseEntity.ok(token);
    }

    @GetMapping
    @ApiOperation(value = "Get all users", response = User.class, responseContainer = "list")

    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping()
    public ResponseEntity saveUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setVerify(false);
        userRepository.save(user);
        String token = jwtTokenUtil.generateToken(new CurrentUser(user));
        String message = String.format("Hi %s, You are successfully registered to our cool portal. Please visit by <a href=\"http://localhost:8080/rest/users/verify?token=%s\">this</a> link to verify your account", user.getName(), token);
        emailService.sendSimpleMessage(user.getEmail(), "Welcome", message);
        return ResponseEntity.ok("created");
    }
        @GetMapping("/verify")
    public ResponseEntity verifyUser(@RequestParam("token") String token) {
        String email = jwtTokenUtil.getUsernameFromToken(token);
        User oneByEmail = userRepository.findOneByEmail(email);
        oneByEmail.setVerify(true);
        userRepository.save(oneByEmail);
        return ResponseEntity.ok("verified");
    }

}
