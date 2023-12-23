package com.project.service;

import java.util.List;

import com.project.exception.InternalServerErrorException;
import com.project.model.Question;

public interface IQuestionService {
	public List<Question> getAllQuestions();
	public void addQuestion(Question question) throws InternalServerErrorException;
	public Question searchQuestionById(int questionId);
	public String deleteQuestion(int questionId);
	public String putQuestion(int questionId, Question question) throws InternalServerErrorException;
	public List<Question> getQuestionsByCategory(String category);
}
