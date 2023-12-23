package com.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.model.Question;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Integer> 
{
	public List<Question> findByCategory(String category);
	
	//Using JPQL
	@Query(value="SELECT q FROM Question q WHERE q.category = :category ORDER BY RAND() LIMIT :n")
	public List<Question> getQuestionByCategoryLimit(@Param("category") String category, @Param("n") int num); 

	//Using Native Query
	@Query(value = "SELECT * FROM Question as q WHERE q.category = ?1 ORDER BY RAND() LIMIT ?2", nativeQuery = true)
	public List<Question> getQuestionByCategoryUsingNativeQuery(String category, int num);

	
}
