package fi.haagahelia.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import fi.haagahelia.bookstore.domain.User;
import fi.haagahelia.bookstore.repository.UserRepository;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        User user = new User("testuser", "password", "USER", "test@email.com");

        userRepository.save(user);

        assertThat(user.getId()).isNotNull();
    }

    @Test
    public void testFindByUsername() {
        User user = new User("searchuser", "password", "USER", "email@test.com");
        userRepository.save(user);

        User found = userRepository.findByUsername("searchuser");

        assertThat(found).isNotNull();
        assertThat(found.getEmail()).isEqualTo("email@test.com");
    }

    @Test
    public void testDeleteUser() {
        User user = new User("deleteuser", "password", "USER", "delete@test.com");
        userRepository.save(user);

        Long id = user.getId();
        userRepository.deleteById(id);

        assertThat(userRepository.findById(id)).isEmpty();
    }

}
