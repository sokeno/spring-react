package com.seamlineinnovations.fullstack;

import com.seamlineinnovations.fullstack.models.User;
import com.seamlineinnovations.fullstack.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test_save_user(){
        User user = new User();
        user.setName("Steve");
        user.setSurname("Steve");
        user.setEmail("steve@steve.com");
        user.setUsername("Steve");
        user.setPassword("Steve");
        entityManager.persist(user);

        assertThat(user).hasFieldOrPropertyWithValue("name","Steve");
        assertThat(user).hasFieldOrPropertyWithValue("email","steve@steve.com");
        assertThat(user).hasFieldOrPropertyWithValue("username","Steve");

    }
    @Test
    public void test_get_users(){
        User user = new User();
        user.setName("Steve");
        user.setSurname("Steve");
        user.setEmail("steve@steve.com");
        user.setUsername("Steve");
        user.setPassword("Steve");
        entityManager.persist(user);

        User newUser = new User();
        newUser.setName("kati");
        newUser.setSurname("kati");
        newUser.setEmail("kati@steve.com");
        newUser.setUsername("kati");
        newUser.setPassword("kati");
        entityManager.persist(newUser);

        List<User> users = new ArrayList<>(userRepository.findAll());
        assertThat(users).hasSize(2).contains(user,newUser);
    }
    @Test
    public void test_get_user(){
        User user = new User();
        user.setName("Steve");
        user.setSurname("Steve");
        user.setEmail("steve@steve.com");
        user.setUsername("Steve");
        user.setPassword("Steve");
        entityManager.persist(user);

        User newUser = new User();
        newUser.setName("kati");
        newUser.setSurname("kati");
        newUser.setEmail("kati@steve.com");
        newUser.setUsername("kati");
        newUser.setPassword("kati");
        entityManager.persist(newUser);

        User user3 = new User();
        user3.setName("stevo");
        user3.setSurname("stevo");
        user3.setEmail("kati@stevo.com");
        user3.setUsername("stevo");
        user3.setPassword("stevo");
        entityManager.persist(user3);

        User person = userRepository.findById(user3.getId()).get();
        assertThat(person.getName()).isEqualTo(user3.getName());
        assertThat(person.getSurname()).isEqualTo("stevo");

    }

    @Test
    public void test_update_user(){
        User user = new User();
        user.setName("Steve");
        user.setSurname("Steve");
        user.setEmail("steve@steve.com");
        user.setUsername("Steve");
        user.setPassword("Steve");
        entityManager.persist(user);

        User newUser = new User();
        newUser.setName("kati");
        newUser.setSurname("kati");
        newUser.setEmail("kati@steve.com");
        newUser.setUsername("kati");
        newUser.setPassword("kati");

        User oldUser = userRepository.findById(user.getId()).get();
        oldUser.setName(newUser.getName());
        oldUser.setSurname(newUser.getSurname());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setUsername(newUser.getUsername());
        oldUser.setPassword(newUser.getPassword());

        entityManager.persist(oldUser);

        User updatedUser = userRepository.findById(user.getId()).get();

        assertThat(updatedUser.getName()).isEqualTo(newUser.getName());
        assertThat(updatedUser.getSurname()).isEqualTo("kati");



    }

    @Test
    public void test_delete_users(){
        User user = new User();
        user.setName("Steve");
        user.setSurname("Steve");
        user.setEmail("steve@steve.com");
        user.setUsername("Steve");
        user.setPassword("Steve");
        entityManager.persist(user);

        User newUser = new User();
        newUser.setName("kati");
        newUser.setSurname("kati");
        newUser.setEmail("kati@steve.com");
        newUser.setUsername("kati");
        newUser.setPassword("kati");
        entityManager.persist(newUser);

        List<User> users = userRepository.findAll();

        assertThat(users).hasSize(2).contains(user,newUser);
        userRepository.deleteAll();

        assertThat(userRepository.findAll()).isEmpty();
    }
}
