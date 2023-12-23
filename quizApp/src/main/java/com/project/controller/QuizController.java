package com.project.controller;

import com.project.model.*;
import com.project.service.IQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    private IQuizService quizService;

    @PostMapping("create/using/param")
    public ResponseEntity<Quiz> createQuiz(@RequestParam int numQuestions, @RequestParam String category, @RequestParam String title) {
        return new ResponseEntity<>(this.quizService.createQuiz(numQuestions, category, title), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Quiz> createQuizByJson(@RequestBody InputJson inputJson) {
        return new ResponseEntity<>(this.quizService.createQuiz
                (inputJson.getNumQues(), inputJson.getCategory(), inputJson.getTitle()), HttpStatus.OK);
    }

    @GetMapping("getquestion/using/param")
    public ResponseEntity<List<Question>> getQuizQuestionsJpql(@RequestParam int numQuestions, @RequestParam String category, @RequestParam String title) {
        return new ResponseEntity<>(this.quizService.getQuestionJpql(numQuestions, category), HttpStatus.OK);
    }

    @GetMapping("getquestion/using/native")
    public ResponseEntity<List<Question>> getQuizQuestionsNative(@RequestBody InputJson inputJson) {
        return new ResponseEntity<>(this.quizService.getQuestionNative(inputJson.getNumQues(), inputJson.getCategory()), HttpStatus.OK);
    }

    @GetMapping("get/id/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable("id") int quizId) {
        return new ResponseEntity<>(this.quizService.fetchQuizById(quizId), HttpStatus.OK);
    }

    @PostMapping("submit/{id}/{title}")
    public ResponseEntity<Integer> submitResult(@RequestBody List<UserResponse> response, @PathVariable("id") int quizId, @PathVariable("title") String title) {
        return new ResponseEntity<>(this.quizService.getScore(response, title, quizId), HttpStatus.OK);
    }
}
