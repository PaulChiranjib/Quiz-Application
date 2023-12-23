package com.project.service;

import java.util.List;

import com.project.model.Question;
import com.project.model.QuestionWrapper;
import com.project.model.Quiz;
import com.project.model.UserResponse;

public interface IQuizService {
	public Quiz createQuiz(int num , String category,String title);
	public List<Question> getQuestionJpql(int numQuestions,String category);
	public List<Question> getQuestionNative(int numQuestions,String category);
	public List<QuestionWrapper> fetchQuizById(int id);
	public int getScore(List<UserResponse> response,String title,int quizId);
}
