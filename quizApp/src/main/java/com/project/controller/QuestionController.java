package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.exception.InternalServerErrorException;
import com.project.model.Question;
import com.project.service.IQuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    private IQuestionService questionService;

    @GetMapping("find/all")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
    }

    @GetMapping("find/by/id/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable("id") int questionId) {
        return new ResponseEntity<>(questionService.searchQuestionById(questionId), HttpStatus.OK);
    }

    @GetMapping("find/by/category/{category}")
    public ResponseEntity<Object> getQuestionByCategory(@PathVariable("category") String category) {
        return ResponseEntity.ok(this.questionService.getQuestionsByCategory(category));
    }

    @PostMapping("add")
    public ResponseEntity<String> postQuestion(@RequestBody Question question) throws InternalServerErrorException {
        questionService.addQuestion(question);
        return new ResponseEntity<>("Successfully added to dataBase", HttpStatus.CREATED);
    }

    @DeleteMapping("delete/by/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable("id") int questionId) {
        return new ResponseEntity<>(questionService.deleteQuestion(questionId), HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateQuestion(@PathVariable("id") int questionId, @RequestBody Question question) throws InternalServerErrorException {
        return ResponseEntity.ok(this.questionService.putQuestion(questionId, question));
    }
}
