package com.seamlineinnovations.fullstack.services;

import com.seamlineinnovations.fullstack.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto user);

}
