package com.project.service;

import com.project.exception.InternalServerErrorException;
import com.project.model.Question;

import java.util.List;

public interface IQuestionService {
    List<Question> getAllQuestions();

    void addQuestion(Question question) throws InternalServerErrorException;

    Question searchQuestionById(int questionId);

    String deleteQuestion(int questionId);

    String putQuestion(int questionId, Question question) throws InternalServerErrorException;

    List<Question> getQuestionsByCategory(String category);
}
