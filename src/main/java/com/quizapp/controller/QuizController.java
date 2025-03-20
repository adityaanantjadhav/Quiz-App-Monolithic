package com.quizapp.controller;


import com.quizapp.model.Question;
import com.quizapp.model.QuestionWrapper;
import com.quizapp.model.Quiz;
import com.quizapp.model.Response;
import com.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {


    @Autowired
    private QuizService quizService;



    //We can use @RequestBody Map<String,String> instead of all these request params as well. For that we need to send data in JSON format
    @PostMapping("/create")
    public ResponseEntity<?> createQuiz(@RequestParam("category") String category,@RequestParam("difficultyLevel") String difficultyLevel,@RequestParam(value="noOfQuestions", defaultValue = "10") int noOfQuestions,@RequestParam("title") String title){
        return new ResponseEntity<Quiz>(quizService.createQuiz(category,difficultyLevel,noOfQuestions,title), HttpStatus.CREATED);
    }

    @GetMapping("/getQuiz/{id}")
    public ResponseEntity<?> getQuiz(@PathVariable("id") Integer id){
        return new ResponseEntity<List<QuestionWrapper>>(quizService.getQuiz(id),HttpStatus.OK);
    }


    @PostMapping("/submit/{id}")
    public ResponseEntity<?> submitQuiz(@PathVariable("id") int id, @RequestBody List<Response> responses){
        return new ResponseEntity<Integer>(quizService.calculateResult(id,responses),HttpStatus.OK);
    }
}
