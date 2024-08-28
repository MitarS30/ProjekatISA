package com.example.final_project;

import com.example.final_project.model.User;
import com.example.final_project.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateAndFindUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");

        userRepository.save(user);

        Optional<User> foundUser = userRepository.findById(user.getId());
        Assertions.assertTrue(foundUser.isPresent());
        Assertions.assertEquals(user.getUsername(), foundUser.get().getUsername());
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");

        userRepository.save(user);
        userRepository.delete(user);

        Optional<User> foundUser = userRepository.findById(user.getId());
        Assertions.assertFalse(foundUser.isPresent());
    }
}
