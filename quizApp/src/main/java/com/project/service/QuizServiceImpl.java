package com.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.IQuizRepository;
import com.project.dao.QuestionRepository;
import com.project.exception.QuestionNotFoundException;
import com.project.exception.QuizNotFoundException;
import com.project.model.Question;
import com.project.model.QuestionWrapper;
import com.project.model.Quiz;
import com.project.model.UserResponse;

@Service
public class QuizServiceImpl implements IQuizService{

	@Autowired
	IQuizRepository quizRepo;
	
	@Autowired
	QuestionRepository questionRepo;
	
	@Override
	public Quiz createQuiz(int num, String category,String title) 
	{
		List<Question> questionByCategoryLimit = this.questionRepo.getQuestionByCategoryLimit(category,num);
		if(questionByCategoryLimit.isEmpty())
			throw new QuestionNotFoundException("Question of mentioned category does not exist");
		
		Quiz objQuiz = new Quiz();
		objQuiz.setListOfQuestion(questionByCategoryLimit);
		objQuiz.setTitle(title);
		this.quizRepo.save(objQuiz);
		
		return objQuiz;
	}

	@Override
	public List<Question> getQuestionJpql(int numQuestions, String category)
	{
		return this.questionRepo.getQuestionByCategoryLimit(category, numQuestions);
	}

	@Override
	public List<Question> getQuestionNative(int numQuestions, String category)
	{
		return this.questionRepo.getQuestionByCategoryUsingNativeQuery(category, numQuestions);
	}

	
	@Override
	public List<QuestionWrapper> fetchQuizById(int id) 
	{
		 Optional<Quiz> findById = this.quizRepo.findById(id);
		 
		 if(findById.isEmpty())
			 throw new QuizNotFoundException("Quiz of mentioned id is not available");
			 
		 List<QuestionWrapper> listQuestion = new ArrayList<>();
		 for(Question q : findById.get().getListOfQuestion())
		 {
			 QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),
					 				q.getOption3(),q.getOption4());
			 listQuestion.add(qw);
		 }
		 return listQuestion;
	}
	
	@Override
	public int getScore(List<UserResponse> response,String title,int quizId)
	{
		Quiz quiz =  this.quizRepo.findQuizByTitleOrId(title,quizId);
		
		if(quiz == null)
			throw new QuizNotFoundException("Quiz of mentioned title/Quiz Id does not exist");
		
		List<Question> listOfQuestion = quiz.getListOfQuestion();
		
		return (int) response.stream()
				.flatMap(responseObj -> listOfQuestion.stream().filter(question -> question.getRightAnswer().equals(responseObj.getAnswer())))
				.count();
	}
}
