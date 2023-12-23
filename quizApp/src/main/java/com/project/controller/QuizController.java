package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.InputJson;
import com.project.model.UserResponse;
import com.project.service.IQuizService;

@RestController
@RequestMapping("quiz")
public class QuizController 
{
	@Autowired
	IQuizService quizService;
	
	@PostMapping("create/using/param")
	public ResponseEntity<Object> createQuiz(@RequestParam int numQuestions , @RequestParam String category , @RequestParam String title)
	{
		return new ResponseEntity<Object>(this.quizService.createQuiz(numQuestions, category,title),HttpStatus.OK);
	}
	
	@PostMapping("create")
	public ResponseEntity<Object> createQuizByJson(@RequestBody InputJson inputJson)
	{
		return new ResponseEntity<Object>(this.quizService.createQuiz
				(inputJson.getNumQues(),inputJson.getCategory(),inputJson.getTitle()),HttpStatus.OK);
	}
	
	@GetMapping("getquestion/using/param")
	public ResponseEntity<Object> getQuizQuestionsJpql(@RequestParam int numQuestions , @RequestParam String category , @RequestParam String title)
	{
		return new ResponseEntity<Object>(this.quizService.getQuestionJpql(numQuestions, category),HttpStatus.OK);
	}
	
	@GetMapping("getquestion/using/native")
	public ResponseEntity<Object> getQuizQuestionsNative(@RequestBody InputJson inputJson)
	{
		return new ResponseEntity<Object>(this.quizService.getQuestionNative(inputJson.getNumQues(),inputJson.getCategory()),HttpStatus.OK);
	}
	
	@GetMapping("get/id/{id}")
	public ResponseEntity<Object> getQuiz(@PathVariable("id") int quizId)
	{
		return new ResponseEntity<Object>(this.quizService.fetchQuizById(quizId),HttpStatus.OK);
	}
	
	@PostMapping("submit/{id}/{title}")
	public ResponseEntity<Object> submitResult(@RequestBody List<UserResponse> response,@PathVariable("id") int quizId,@PathVariable("title") String title)
	{
		return new ResponseEntity<Object>(this.quizService.getScore(response,title,quizId),HttpStatus.OK);
	}
}
