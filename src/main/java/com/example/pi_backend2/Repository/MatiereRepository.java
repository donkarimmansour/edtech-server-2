package com.example.pi_backend2.Repository;

import com.example.pi_backend2.Entity.matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatiereRepository extends JpaRepository<matiere,Long> {
    matiere findByName(String name);
}
