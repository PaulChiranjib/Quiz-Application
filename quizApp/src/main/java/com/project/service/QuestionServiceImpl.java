package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.QuestionRepository;
import com.project.exception.InternalServerErrorException;
import com.project.exception.QuestionNotFoundException;
import com.project.model.Question;

@Service
public class QuestionServiceImpl implements IQuestionService {

	@Autowired
	QuestionRepository questionRepo;
	
	@Override
	public List<Question> getAllQuestions()
	{
		  List<Question> findAll = (List<Question>) this.questionRepo.findAll();
		  if(findAll.isEmpty())
			throw new QuestionNotFoundException("Question is not available");
		  return findAll;	
	}
	@Override
	public void addQuestion(Question question) throws InternalServerErrorException 
	{
		try 
		{
			this.questionRepo.save(question);
		} catch (Exception e) {
			throw new InternalServerErrorException("Encountered Error in adding Question to the dataBase");
		}		
	}
	@Override
	public Question searchQuestionById(int questionId)
	{
		Optional<Question> findById = this.questionRepo.findById(questionId);
		if(findById.isEmpty())
		{
			System.out.println("Question is not available ");
			throw new QuestionNotFoundException("Question is not available");
		}
		return findById.get(); 
	}
	@Override
	public String deleteQuestion(int questionId)
	{		
		if(this.questionRepo.findById(questionId).isEmpty())
		{
			System.out.println("Question is not available to be Deleted");
			throw new QuestionNotFoundException("Question is not available to be Deleted");
		}
		this.questionRepo.deleteById(questionId);
		return "Successfully Deleted";
	}
	
	@Override
	public String putQuestion(int questionId, Question question) throws InternalServerErrorException {

		try 
		{
			Optional<Question> findById = this.questionRepo.findById(questionId);
			if(findById.isEmpty())
				throw new QuestionNotFoundException("Requested Question is not in dataBase , sorry we cannot Updated");
			
			Question question2 = findById.get();
			question2.setCategory(question.getCategory());
			question2.setDifficultyLevel(question.getDifficultyLevel());
			question2.setOption1(question.getOption1());
			question2.setOption2(question.getOption2());
			question2.setOption3(question.getOption3());
			question2.setOption4(question.getOption4());
			question2.setQuestionTitle(question.getQuestionTitle());
			question2.setRightAnswer(question.getRightAnswer());
			
			this.questionRepo.save(question2);		
			return "Successfully updated";
		} catch (Exception e) {
			throw new InternalServerErrorException("Encountered Error while deleting Question from the dataBase");
		}
	}
	@Override
	public List<Question> getQuestionsByCategory(String category)
	{
		List<Question> findByCategory = this.questionRepo.findByCategory(category);
		
		if(findByCategory.isEmpty())
			throw new QuestionNotFoundException("Question of mentioned category is not available");
		return findByCategory;		 
	}
}
