import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.user.domain.User;
import com.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByEmailOrCpf_EmailFound() {
        // Mocking existing user
        User existingUser = new User();
        existingUser.setEmail("john@example.com");
        existingUser.setCpf("123.456.789-10");
        entityManager.persist(existingUser);
        entityManager.flush();

        // Call the method being tested
        Optional<User> foundUser = userRepository.findByEmailOrCpf("john@example.com", null);

        // Verify the result
        assertTrue(foundUser.isPresent());
        assertEquals("john@example.com", foundUser.get().getEmail());
    }

    @Test
    public void testFindByEmailOrCpf_CpfFound() {
        // Mocking existing user
        User existingUser = new User();
        existingUser.setEmail("john@example.com");
        existingUser.setCpf("123.456.789-10");
        entityManager.persist(existingUser);
        entityManager.flush();

        // Call the method being tested
        Optional<User> foundUser = userRepository.findByEmailOrCpf(null, "123.456.789-10");

        // Verify the result
        assertTrue(foundUser.isPresent());
        assertEquals("123.456.789-10", foundUser.get().getCpf());
    }

    @Test
    public void testFindByEmailOrCpf_NotFound() {
        // Call the method being tested
        Optional<User> foundUser = userRepository.findByEmailOrCpf("nonexistent@example.com", null);

        // Verify the result
        assertFalse(foundUser.isPresent());
    }

    @Test
    public void testFindByIdOrCpfOrEmail() {
        // Mocking existing users
        User user1 = new User();
        user1.setId(1L);
        user1.setEmail("john@example.com");
        entityManager.persist(user1);

        User user2 = new User();
        user2.setId(2L);
        user2.setCpf("123.456.789-10");
        entityManager.persist(user2);

        entityManager.flush();

        // Call the method being tested
        Page<User> foundUsers = userRepository.findByIdOrCpfOrEmail(1L, null, null, PageRequest.of(0, 10));

        // Verify the result
        assertEquals(1, foundUsers.getTotalElements());
        assertEquals("john@example.com", foundUsers.getContent().get(0).getEmail());
    }

    // Add more test methods for other scenarios
}
