package com.example.pi_backend2.Service;

import com.example.pi_backend2.Entity.Cours;
import com.example.pi_backend2.Entity.matiere;
import com.example.pi_backend2.Repository.CoursRepository;
import com.example.pi_backend2.Repository.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoursService {
    @Autowired
    private CoursRepository coursRepository;
    @Autowired
    private MatiereRepository matiereRepository;

    public void saveDocumentPDF(byte[] data, String title,String description,matiere ma) {
        Cours documentPDF = new Cours();
        documentPDF.setData(data);
        documentPDF.setTitle(title); // Définir le titre du document
        documentPDF.setDescription(description);
        documentPDF.setMatiere(ma);
        coursRepository.save(documentPDF);
    }

    public byte[] getDocumentPDFById(String title) {
        Optional<Cours> optionalDocumentPDF = coursRepository.findByTitle(title);

        return optionalDocumentPDF.map(Cours::getData).orElse(null);
    }
    public List<Cours> getAllCours() {
        return coursRepository.findAll();
    }

    public List<Cours> searchCoursByKeywords(String keywords) {
        return coursRepository.findByTitleContainingIgnoreCase(keywords);
    }

    public List<Cours> getCoursByMatiere(String name) {
       matiere matiere= this.matiereRepository.findByName(name);
        return this.coursRepository.findByMatiere(matiere);
    }
}
