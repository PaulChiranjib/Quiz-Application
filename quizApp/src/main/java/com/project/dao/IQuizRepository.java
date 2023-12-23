package com.project.dao;

import com.project.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuizRepository extends JpaRepository<Quiz, Integer> {
    Quiz findQuizByTitleOrId(String title, int quizId);
}
