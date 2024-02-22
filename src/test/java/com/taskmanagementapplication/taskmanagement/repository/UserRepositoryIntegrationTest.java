package com.taskmanagementapplication.taskmanagement.repository;

import com.taskmanagementapplication.taskmanagement.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;



import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testExistsByUserName() {

        // Given
        User user = new User();
        user.setUserName("testUser");
        entityManager.persistAndFlush(user);

        // When
        boolean exists = userRepository.existsByUserName("testUser");

        // Then
        assertThat(exists).isTrue();
    }

    @Test
    public void testFindByUserName() {

        // Given
        User user = new User();
        user.setUserName("testUser");
        entityManager.persistAndFlush(user);

        // When
        User foundUser = userRepository.findByUserName("testUser");

        // Then
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUserName()).isEqualTo("testUser");
    }
}
