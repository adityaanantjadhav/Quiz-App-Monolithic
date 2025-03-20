package com.quizapp.service;


import com.quizapp.model.Question;
import com.quizapp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepo;


    public List<Question> getAllQuestions(){
        return questionRepo.findAll();
    }

    public List<Question> getQuestionByCategory(String topic) {
        return questionRepo.findAllByCategory(topic);

    }

    public Question addQuestion(Question question) {
        return questionRepo.save(question);
    }
}
