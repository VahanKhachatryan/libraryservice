package libraryservise.am.libraryservice.security;

import libraryservise.am.libraryservice.model.User;
import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user) {
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList("USER"));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public int getId() {
        return user.getId();
    }
}
