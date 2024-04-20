package com.example.pi_backend2.Repository;

import com.example.pi_backend2.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User,Long> {
    Optional<User> findByfirstName(String userName);

    Optional<User> findByUserName(String userName);

    User findByUserNameAndPassword(String userName, String password);
}
