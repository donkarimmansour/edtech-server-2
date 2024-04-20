package com.example.pi_backend2.Entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prof{
    @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdProf;
    private String nom;
    private  String prenom;
    private  Long numeroTel;
    private String Adresse;
    private String specialite;
    @Column(unique = true)
    private String codeProf;
    private String password;
    private String userName;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getIdProf() {
        return IdProf;
    }

    public void setIdProf(Long idProf) {
        IdProf = idProf;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Long getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(Long numeroTel) {
        this.numeroTel = numeroTel;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getCodeProf() {
        return codeProf;
    }

    public void setCodeProf(String codeProf) {
        this.codeProf = codeProf;
    }
}

