package com.quizapp.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id;

    private String questionTitle;
    private String category;


    private String option1;
    private String option2;
    private String option3;
    private String option4;


    private String rightAnswer;
    private String difficultyLevel;

}
