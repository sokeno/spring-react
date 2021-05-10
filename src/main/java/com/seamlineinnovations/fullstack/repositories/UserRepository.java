package com.seamlineinnovations.fullstack.repositories;

import com.seamlineinnovations.fullstack.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
