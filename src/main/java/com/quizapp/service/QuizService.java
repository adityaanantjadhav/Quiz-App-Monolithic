package com.quizapp.service;


import com.quizapp.model.Question;
import com.quizapp.model.QuestionWrapper;
import com.quizapp.model.Quiz;
import com.quizapp.model.Response;
import com.quizapp.repository.QuestionRepository;
import com.quizapp.repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    private QuestionRepository questionRepo;

    @Autowired
    private QuizRepo quizRepo;


    public Quiz createQuiz(String category, String difficultyLevel, int noOfQuestions,String title) {
        Quiz quiz=new Quiz();

        quiz.setTitle(title);

        List<Question>list=questionRepo.createQuiz(category,difficultyLevel,noOfQuestions);
        quiz.setQuestions(list);
        if(category==null)
            category="General";

        quiz.setCategory(category);

        if(difficultyLevel==null)
            difficultyLevel="General";

        quiz.setDifficultyLevel(difficultyLevel);

        return quizRepo.save(quiz);

    }

    public List<QuestionWrapper> getQuiz(Integer id) {
        Quiz quiz=quizRepo.findById(id).orElseThrow(()->new RuntimeException("Resource Not found!!"));
        List<Question> listOfQuestions= quiz.getQuestions();
        List<QuestionWrapper> listOfQuestionWrappers=listOfQuestions.stream().map((q)->{
            QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            return qw;
        }).toList();

        return listOfQuestionWrappers;
    }

    public Integer calculateResult(int id, List<Response> responses) {

        Quiz quiz=quizRepo.findById(id).orElseThrow(()->new RuntimeException("No quiz found"));

        List<Question>questionList=quiz.getQuestions();

        Collections.sort(responses,(a,b)-> Integer.compare(a.getId(),b.getId()));
        Collections.sort(questionList,(a,b)-> Integer.compare(a.getId(),b.getId()));

        int marks=0;
        for(int i=0;i<responses.size();i++){
            if(responses.get(i).getResponse().equals(questionList.get(i).getRightAnswer()))
                marks++;
        }

        return marks;
    }
}

