package com.example.pi_backend2.Repository;

import com.example.pi_backend2.Entity.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttemptRepository extends JpaRepository<Attempt,Long> {

    List<Attempt> findByUserName(String userName);
}
