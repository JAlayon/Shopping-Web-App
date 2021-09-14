package com.alayon.shopme.site.admin.user;

import com.alayon.shopme.common.entity.Role;
import com.alayon.shopme.common.entity.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @Order(1)
    @Rollback(false)
    public void createUser_HappyPath(){
        Role admin = testEntityManager.persist(new Role("Admin", ""));
        User user = User.builder()
                    .firstName("Jair")
                .lastName("alayon")
                .email("alayonjair@gmail.com")
                .password("12345")
                .enabled(true)
                .build();
        user.addRole(admin);

        User savedUser = userRepository.save(user);

        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    @Rollback(false)
    public void createUserWithMultipleRoles_HappyPath(){
        Role editor = testEntityManager.persist(new Role("Editor", ""));
        Role assistant = testEntityManager.persist(new Role("Assistant", ""));

        User user = User.builder()
                .firstName("Jair")
                .lastName("alayon")
                .email("email@gmail.com")
                .password("12345")
                .enabled(true)
                .build();

        user.addRole(editor);
        user.addRole(assistant);

        User savedUser = userRepository.save(user);

        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    @Order(3)
    public void listAllUsers_HappyPath(){
        Iterable<User> users = userRepository.findAll();
        users.forEach(System.out::println);

        assertThat(users).asList().hasSize(2);
    }

    @Test
    @Order(4)
    public void getUserById_HappyPath(){
        User user = userRepository.findById(1).get();
        assertThat(user.getFirstName()).isNotNull();
    }
}
