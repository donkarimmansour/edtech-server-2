package com.example.pi_backend2.Controller;

import com.example.pi_backend2.Entity.Cours;
import com.example.pi_backend2.Entity.Question;
import com.example.pi_backend2.Entity.Quiz;
import com.example.pi_backend2.Repository.QuestionRepository;
import com.example.pi_backend2.Service.QuizService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private EntityManager entityManager;
    private final QuizService quizService;
    private QuestionRepository questionRepository;

    @Autowired
    public QuizController(QuizService quizService,QuestionRepository questionRepository) {

        this.quizService = quizService;
        this.questionRepository=questionRepository;
    }

    @GetMapping("/get")
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        List<Quiz> quizzes = quizService.getAllQuizzes();
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    @GetMapping("/{quizName}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable("quizName") String name) {
        Quiz quiz = quizService.getQuizByName(name);

        return new ResponseEntity<>(quiz, HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Quiz>> searchCours(@RequestParam String keywords) {
        List<Quiz> quiz = quizService.searchCoursByKeywords(keywords);
        return ResponseEntity.ok().body(quiz);
    }

//
//    @PostMapping("/post")
//    @Transactional
//    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
//
//        Quiz createdQuiz = new Quiz();
//        createdQuiz.setName(quiz.getName());
//        createdQuiz.setDescription(quiz.getDescription());
//
//
//        for (Question question : quiz.getQuestions()) {
//            Question managedQuestion = entityManager.merge(question);
//
//            createdQuiz.getQuestions().add(managedQuestion);
//        }
//        Quiz savedQuiz = this.quizService.createQuiz(createdQuiz);
//
//        return new ResponseEntity<>(savedQuiz, HttpStatus.CREATED);
//    }

    @GetMapping("/createQuiz")
    @Transactional
    public ResponseEntity<Quiz> generateQuiz2(@RequestParam String cours, String cour_id) {
        System.out.println();
        System.out.println("in controler");
        System.out.println();

        // Generate and save the quiz
        this.quizService.generateAndSaveQuiz(cours, cour_id);
        System.out.println();

        System.out.println();

        // Retrieve the saved quiz
        Quiz savedQuiz = quizService.getOptionalQuizByName(cours);

        // Return the saved quiz
        return new ResponseEntity<>(savedQuiz, HttpStatus.CREATED);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Quiz> updateQuiz(@PathVariable("id") Long id, @RequestBody Quiz quiz) {
//        Quiz updatedQuiz = quizService.updateQuiz(id, quiz);
//        return new ResponseEntity<>(updatedQuiz, HttpStatus.OK);
//    }
//@DeleteMapping("/{id}")
//   public ResponseEntity<?> deleteQuiz(@PathVariable("id") Long id) {
//       quizService.deleteQuiz(id);
//       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}