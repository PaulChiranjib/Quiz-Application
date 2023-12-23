package com.project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})

public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Question> listOfQuestion;
}
