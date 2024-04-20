package com.example.pi_backend2.Controller;
import com.example.pi_backend2.Entity.matiere;
import com.example.pi_backend2.Entity.Cours;
import com.example.pi_backend2.Repository.MatiereRepository;
import com.example.pi_backend2.Service.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/cours")
public class CoursController {
    @Autowired
    private CoursService documentPDFService;
    @Autowired
    private MatiereRepository matiereRepository;

    @PostMapping("/upload")
    public ResponseEntity<Void> uploadDocumentPDF(

            @RequestParam("title") String title, @RequestParam("description") String description
            ,@RequestParam("matiere") String matiere,@RequestParam("file") MultipartFile file) {
        try {
            byte[] data = file.getBytes();
            matiere ma=this.matiereRepository.findByName(matiere);

            documentPDFService.saveDocumentPDF(data, title,description,ma); // Passer le titre au service
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{title}")
    public ResponseEntity<byte[]> getDocumentPDF(@PathVariable String title) {
        byte[] data = documentPDFService.getDocumentPDFById(title);
        if (data != null) {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(data);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/all/{nomMatiere}")
    public List<Cours> getAllCours(@PathVariable String nomMatiere) {
        System.out.println(this.documentPDFService.getCoursByMatiere(nomMatiere));
        return this.documentPDFService.getCoursByMatiere(nomMatiere);

    }
    @GetMapping("/search")
    public ResponseEntity<List<Cours>> searchCours(@RequestParam String keywords) {
        List<Cours> cours = documentPDFService.searchCoursByKeywords(keywords);
        return ResponseEntity.ok().body(cours);
    }

}
