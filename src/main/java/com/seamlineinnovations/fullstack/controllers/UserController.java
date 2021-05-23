package com.seamlineinnovations.fullstack.controllers;

import com.seamlineinnovations.fullstack.shared.dto.UserDto;
import com.seamlineinnovations.fullstack.exceptions.ResourceNotFoundException;
import com.seamlineinnovations.fullstack.entities.UserEntity;
import com.seamlineinnovations.fullstack.repositories.UserRepository;
import com.seamlineinnovations.fullstack.request.UserDetailsRequestModel;
import com.seamlineinnovations.fullstack.response.UserRest;
import com.seamlineinnovations.fullstack.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users") // http://localhost:8080/users
public class UserController {
    private UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
        UserRest returnValue = new UserRest();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails,userDto);

        UserDto createdUser = userService.createUser(userDto);

        BeanUtils.copyProperties(createdUser,returnValue);
        return returnValue;

    }

    @GetMapping("/user/all")
    public ResponseEntity<List<UserEntity>> getUsers() {
        return ResponseEntity.ok(this.userRepository.findAll());

    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable(value = "id") Long id) {
        UserEntity userEntity = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found")

        );
        return ResponseEntity.ok().body(userEntity);
    }

    @PutMapping("/user/{id}")
    public UserEntity updateUser(@RequestBody UserEntity newUserEntity, @PathVariable(value = "id") Long id) {
        return this.userRepository.findById(id).map(userModel -> {
            userModel.setFirstName(newUserEntity.getFirstName());
            userModel.setLastName(newUserEntity.getLastName());
            userModel.setEmail(newUserEntity.getEmail());
            userModel.setEncryptedPassword(newUserEntity.getEncryptedPassword());
//            userModel.setPassword(newUserModel.getPassword());
            return this.userRepository.save(userModel);
        }).orElseGet(() -> {
            newUserEntity.setId(id);
            return this.userRepository.save(newUserEntity);
        });

    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> removeUser(@PathVariable(value = "id") Long id) {
        UserEntity userEntity = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        this.userRepository.delete(userEntity);
        return ResponseEntity.ok().build();
    }
}
