package com.example.pi_backend2.Repository;

import com.example.pi_backend2.Entity.Cours;
import com.example.pi_backend2.Entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository  extends JpaRepository<Quiz,Long> {
    List<Quiz> findByName(String name);


    List<Quiz> findByNameContainingIgnoreCase(String keywords);

    List<Quiz> findByCours(Optional<Cours> cours);
}
