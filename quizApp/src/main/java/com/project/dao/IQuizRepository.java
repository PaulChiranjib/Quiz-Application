package com.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.model.Quiz;

@Repository
public interface IQuizRepository extends JpaRepository<Quiz, Integer> 
{

	public Quiz findQuizByTitleOrId(String title,int quizId);
	
}
