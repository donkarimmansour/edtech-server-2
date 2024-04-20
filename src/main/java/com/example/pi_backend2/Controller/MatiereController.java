package com.example.pi_backend2.Controller;

import com.example.pi_backend2.Entity.Cours;
import com.example.pi_backend2.Entity.User;
import com.example.pi_backend2.Entity.matiere;
import com.example.pi_backend2.Repository.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matieres")
public class MatiereController {
    @Autowired
    private MatiereRepository matiereRepository;

    public MatiereController(MatiereRepository matiereRepository) {
        this.matiereRepository = matiereRepository;
    }
    @PostMapping("/add")
    public ResponseEntity<matiere> addMatiere(@RequestBody matiere matiere) {
        matiere newUser = matiereRepository.save(matiere);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public List<matiere> getAllMatires() {
        return this.matiereRepository.findAll();
    }


}
