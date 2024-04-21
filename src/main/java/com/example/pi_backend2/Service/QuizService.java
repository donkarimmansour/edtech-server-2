package com.example.pi_backend2.Service;

import com.example.pi_backend2.Entity.*;
import com.example.pi_backend2.Repository.CoursRepository;
import com.example.pi_backend2.Repository.OptionRepository;
import com.example.pi_backend2.Repository.QuestionRepository;
import com.example.pi_backend2.Repository.QuizRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private APIService apiService;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private CoursRepository coursRepository;

    @Autowired
    private EntityManager entityManager;



    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public List<Quiz> getAllQuizzesByCours(String name) {
        Optional<Cours>   cours = this.coursRepository.findByTitle(name);
        return this.quizRepository.findByCours(cours);
    }


    public Quiz getQuizByName(String name) {
        List<Quiz> quizzes = quizRepository.findByName(name);
        if (!quizzes.isEmpty()) {
            return quizzes.get(0);
        } else {
            throw new EntityNotFoundException("Quiz not found with name: " + name);
        }
    }
//    public void deleteQuiz(Long id){
//        quizRepository.deleteById(id);
//    }
    public List<Quiz> searchCoursByKeywords(String keywords) {
        return quizRepository.findByNameContainingIgnoreCase(keywords);
    }
//    public Quiz createQuiz(Quiz quiz) {
//        return quizRepository.save(quiz);
//    }

    /*public void generateAndSaveQuiz2(String cours) {
        System.out.println("Generating quiz for course: " + cours.split("\n")[0]);

        apiService.generateQuizQuestions(cours).subscribe(quizQuestions -> {
            System.out.println("Generated quiz questions: " + quizQuestions);

            Quiz quiz = new Quiz();
            quiz.setName(cours.split("\n")[0]);
            quiz.setDescription("Quiz for " + cours.split("\n")[0]);
            quiz = quizRepository.save(quiz);

            List<Question> questions = new ArrayList<>();
            for (Map<String, Object> quizQuestion : quizQuestions) {
                Question question = new Question();
                question.setQuestion((String) quizQuestion.get("question"));
                question.setQuiz(quiz);
                question = questionRepository.save(question);
                System.out.println("Saved question: " + question);

                List<String> options = (List<String>) quizQuestion.get("options");
                for (String optionText : options) {
                    // Assuming that the optionText is not the answer, set isAnswer to false
                    Option option = new Option(question, optionText, false);
                    optionRepository.save(option);
                    System.out.println("Saved option: " + option);
                }
                questions.add(question);
            }
            // hna fin at ajouta


        });
    }*/





    @Transactional
    public List<Question> generateAndSaveQuiz(String cours, String cour_id) {
        System.out.println("Generating quiz for course: " + cours.split("\n")[0]);

        List<Question> questions = new ArrayList<>();

        apiService.generateQuizQuestions(cours).subscribe(quizQuestions -> {
            System.out.println("Generated quiz questions: " + quizQuestions);

            // Retrieve the managed Quiz entity from the database based on its name
            Optional<Cours> courses = coursRepository.findById(Long.valueOf(cour_id));
            Cours selectedCours = courses.orElseGet(() -> coursRepository.findAll().get(0));

            System.out.println("selectedCours: " + selectedCours.getId());

            Quiz quiz = new Quiz();
            quiz.setName(cours.split("\n")[0]);
            quiz.setDescription("Quiz for " + cours.split("\n")[0]);
            quiz.setCours(selectedCours);
            quiz = quizRepository.save(quiz);


            for (Map<String, Object> quizQuestion : quizQuestions) {
                Question question = new Question();
                question.setName((String) quizQuestion.get("question"));
                question.setQuiz(quiz);

                String answerText = (String) quizQuestion.get("answer");
                question.setCorrect(answerText);

                question = questionRepository.save(question);
                System.out.println("Saved question: " + question);

                List<String> options = (List<String>) quizQuestion.get("options");

                for (String optionText : options) {
                    // Assuming that the optionText is not the answer, set isAnswer to false
                    Option option = new Option(question, optionText, optionText.equals(answerText) ? 1 : 0);
                    optionRepository.save(option);
                    System.out.println("Saved option: " + option);
                }

                questions.add(question);
            }
        });

        return questions;
    }





    public Quiz getOptionalQuizByName(String name) {
        String shortName = name.length() > 255 ? name.substring(0, 255) : name;
        List<Quiz> quizzes = quizRepository.findByName(shortName);
        if (quizzes.isEmpty()) {
            Quiz newQuiz = new Quiz();
            newQuiz.setName(shortName);
            newQuiz.setDescription(shortName);
            // Set other properties of newQuiz...
            System.out.println(newQuiz);
            return quizRepository.saveAndFlush(newQuiz);
        } else {
            // Handle multiple quizzes with the same name
            // For example, return the first one
            return quizzes.get(0);
        }
    }

}

