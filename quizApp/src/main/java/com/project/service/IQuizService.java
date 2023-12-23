package com.project.service;

import com.project.model.Question;
import com.project.model.QuestionWrapper;
import com.project.model.Quiz;
import com.project.model.UserResponse;

import java.util.List;

public interface IQuizService {
    Quiz createQuiz(int num, String category, String title);

    List<Question> getQuestionJpql(int numQuestions, String category);

    List<Question> getQuestionNative(int numQuestions, String category);

    List<QuestionWrapper> fetchQuizById(int id);

    int getScore(List<UserResponse> response, String title, int quizId);
}
