package com.example.pi_backend2.Controller;

import com.example.pi_backend2.Entity.Question;
import com.example.pi_backend2.Repository.QuestionRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.*;

@RestController
@RequestMapping("/questions")
public class QuestionController {
//
//    private QuestionRepository questionRepository;
//
//    public QuestionController(QuestionRepository questionRepository) {
//        this.questionRepository = questionRepository;
//    }
//
//
//    @GetMapping("/get")
//    public List<Question> getAllQuestions() {
//        return questionRepository.findAll();
//    }
////
////    // Récupérer une question par son ID
////    @GetMapping("/{id}")
////    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
////        Optional<Question> questionOptional = questionRepository.findById(id);
////        return questionOptional.map(question -> new ResponseEntity<>(question, HttpStatus.OK))
////                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
////    }
//
//    // Ajouter une nouvelle question
//    @PostMapping("/post")
//    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
//        Question qst = new Question();
//        qst.setName(question.getName());
////
////        List<String> optionTexts = question.getOptions() instanceof List
////                ? question.getOptions()
////                : new ArrayList<>();
////        qst.setOptions(optionTexts);
//
//
//        Question savedQuestion = questionRepository.save(qst);
//
//        return new ResponseEntity<>(savedQuestion, HttpStatus.CREATED);
//    }

    // Mettre à jour une question existante
//    @PutMapping("/{id}")
//    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question questionDetails) {
//        Optional<Question> optionalQuestion = questionRepository.findById(id);
//        if (optionalQuestion.isPresent()) {
//            Question question = optionalQuestion.get();
//            question.setName(questionDetails.getName());
//            question.setTypeId(questionDetails.getTypeId());
//            question.setOptions(questionDetails.getOptions());
//            question.setQuestionType(questionDetails.getQuestionType());
//            question.setQuiz(questionDetails.getQuiz());
//            Question updatedQuestion = questionRepository.save(question);
//            return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    // Supprimer une question
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
//        Optional<Question> optionalQuestion = questionRepository.findById(id);
//        if (optionalQuestion.isPresent()) {
//            questionRepository.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
}
