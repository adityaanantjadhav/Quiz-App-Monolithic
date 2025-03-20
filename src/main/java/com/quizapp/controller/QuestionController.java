package com.quizapp.controller;


import com.quizapp.model.Question;
import com.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {


    @Autowired
    private QuestionService questionService;
    @GetMapping("/allQuestion")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return new ResponseEntity<List<Question>>(questionService.getAllQuestions(), HttpStatus.OK);
    }


    @GetMapping("/category/{topic}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable("topic") String topic){
        return new ResponseEntity<>(questionService.getQuestionByCategory(topic),HttpStatus.OK);
    }


    @PostMapping("/addQuestion")
    public ResponseEntity<?> addQuestion(@RequestBody Question question){
        System.out.println("Question:"+question.getQuestionTitle());
        return new ResponseEntity<Question>(questionService.addQuestion(question),HttpStatus.CREATED);
    }
}
