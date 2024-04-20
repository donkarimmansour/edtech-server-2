package com.example.pi_backend2.Repository;

import com.example.pi_backend2.Entity.Cours;
import com.example.pi_backend2.Entity.matiere;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CoursRepository extends JpaRepository<Cours,Long> {
    Optional<Cours> findByTitle(String title);

    List<Cours> findByTitleContainingIgnoreCase(String keywords);

    List<Cours> findByMatiere(matiere matiere);
}
