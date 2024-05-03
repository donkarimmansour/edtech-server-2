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


}

