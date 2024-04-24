package com.example.pi_backend2.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "option_entity")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "question_id" ,referencedColumnName = "id")
    @JsonIgnore
    private Question question;

    private String name;

    @Column(name = "is_answer")
    private int isAnswer;

    public Option( Question question, String name, int isAnswer) {
        this.question = question;
        this.name = name;
        this.isAnswer = isAnswer;
    }


}
