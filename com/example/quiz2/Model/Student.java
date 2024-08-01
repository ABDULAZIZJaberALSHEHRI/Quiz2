package com.example.quiz2.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {

    @NotNull
    private int id;

    @NotEmpty
    private String name;

    @NotNull
    private int age;

    @NotEmpty
    private String major;
}
