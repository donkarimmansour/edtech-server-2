package com.example.pi_backend2.Controller;

import com.example.pi_backend2.Entity.Attempt;
import com.example.pi_backend2.Repository.AttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("attempts")
public class AttemptController {
    @Autowired
    private AttemptRepository attemptRepository;


    @PostMapping("/post")
    public ResponseEntity<Attempt> enregistrerTentative(@RequestBody Attempt request) {
        Attempt tentative = this.attemptRepository.save(request);
        return new ResponseEntity<>(tentative, HttpStatus.CREATED);
    }
    @GetMapping("/{userName}")
    public ResponseEntity<List<Attempt>> getAttemptsByUserName(@PathVariable String userName) {
        List<Attempt> attempts = attemptRepository.findByUserName(userName);
        return new ResponseEntity<>(attempts, HttpStatus.OK);
    }


}
