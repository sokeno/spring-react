package com.seamlineinnovations.fullstack;

import com.seamlineinnovations.fullstack.entities.UserEntity;
import com.seamlineinnovations.fullstack.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserEntityTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test_save_user(){
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName("Steve");
        userEntity.setLastName("Steve");
        userEntity.setEmail("steve@steve.com");
//        userModel.setUsername("Steve");
//        userModel.setPassword("Steve");
        entityManager.persist(userEntity);

        assertThat(userEntity).hasFieldOrPropertyWithValue("name","Steve");
        assertThat(userEntity).hasFieldOrPropertyWithValue("email","steve@steve.com");
        assertThat(userEntity).hasFieldOrPropertyWithValue("username","Steve");

    }
    @Test
    public void test_get_users(){
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName("Steve");
        userEntity.setLastName("Steve");
        userEntity.setEmail("steve@steve.com");
//        userModel.setUsername("Steve");
//        userModel.setPassword("Steve");
        entityManager.persist(userEntity);

        UserEntity newUserEntity = new UserEntity();
        newUserEntity.setFirstName("kati");
        newUserEntity.setLastName("kati");
        newUserEntity.setEmail("kati@steve.com");
//        newUserModel.setUsername("kati");
//        newUserModel.setPassword("kati");
        entityManager.persist(newUserEntity);

        List<UserEntity> userEntities = new ArrayList<>(userRepository.findAll());
        assertThat(userEntities).hasSize(2).contains(userEntity, newUserEntity);
    }
    @Test
    public void test_get_user(){
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName("Steve");
        userEntity.setLastName("Steve");
        userEntity.setEmail("steve@steve.com");
//        userModel.setUsername("Steve");
//        userModel.setPassword("Steve");
        entityManager.persist(userEntity);

        UserEntity newUserEntity = new UserEntity();
        newUserEntity.setFirstName("kati");
        newUserEntity.setLastName("kati");
        newUserEntity.setEmail("kati@steve.com");
//        newUserModel.setUsername("kati");
//        newUserModel.setPassword("kati");
        entityManager.persist(newUserEntity);

        UserEntity userEntity3 = new UserEntity();
        userEntity3.setFirstName("stevo");
        userEntity3.setLastName("stevo");
        userEntity3.setEmail("kati@stevo.com");
//        userModel3.setUsername("stevo");
//        userModel3.setPassword("stevo");
        entityManager.persist(userEntity3);

        UserEntity person = userRepository.findById(userEntity3.getId()).get();
        assertThat(person.getFirstName()).isEqualTo(userEntity3.getFirstName());
        assertThat(person.getLastName()).isEqualTo("stevo");

    }

    @Test
    public void test_update_user(){
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName("Steve");
        userEntity.setLastName("Steve");
        userEntity.setEmail("steve@steve.com");
//        userModel.setUsername("Steve");
//        userModel.setPassword("Steve");
        entityManager.persist(userEntity);

        UserEntity newUserEntity = new UserEntity();
        newUserEntity.setFirstName("kati");
        newUserEntity.setLastName("kati");
        newUserEntity.setEmail("kati@steve.com");
//        newUserModel.setUsername("kati");
//        newUserModel.setPassword("kati");

        UserEntity oldUserEntity = userRepository.findById(userEntity.getId()).get();
        oldUserEntity.setFirstName(newUserEntity.getFirstName());
        oldUserEntity.setLastName(newUserEntity.getLastName());
        oldUserEntity.setEmail(newUserEntity.getEmail());
//        oldUserModel.setUsername(newUserModel.getUsername());
//        oldUserModel.setPassword(newUserModel.getPassword());

        entityManager.persist(oldUserEntity);

        UserEntity updatedUserEntity = userRepository.findById(userEntity.getId()).get();

        assertThat(updatedUserEntity.getFirstName()).isEqualTo(newUserEntity.getFirstName());
        assertThat(updatedUserEntity.getLastName()).isEqualTo("kati");



    }

    @Test
    public void test_delete_users(){
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName("Steve");
        userEntity.setLastName("Steve");
        userEntity.setEmail("steve@steve.com");
//        userModel.setUsername("Steve");
//        userModel.setPassword("Steve");
        entityManager.persist(userEntity);

        UserEntity newUserEntity = new UserEntity();
        newUserEntity.setFirstName("kati");
        newUserEntity.setLastName("kati");
        newUserEntity.setEmail("kati@steve.com");
//        newUserModel.setUsername("kati");
//        newUserModel.setPassword("kati");
        entityManager.persist(newUserEntity);

        List<UserEntity> userEntities = userRepository.findAll();

        assertThat(userEntities).hasSize(2).contains(userEntity, newUserEntity);
        userRepository.deleteAll();

        assertThat(userRepository.findAll()).isEmpty();
    }
}
