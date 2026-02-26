package fi.haagahelia.bookstore.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;

import fi.haagahelia.bookstore.domain.User;
import fi.haagahelia.bookstore.repository.UserRepository;

public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository repository;

	public UserDetailServiceImpl(UserRepository repository) {
		this.repository = repository;
	}


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        User user = repository.findByUsername(username);
        
        UserBuilder builder = null;

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        } else {
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(user.getPasswordHash());
            builder.roles(user.getRole());
        }
        
        return builder.build();
    }
    

}
