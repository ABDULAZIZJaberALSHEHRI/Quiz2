package com.example.quiz2.Controller;

import com.example.quiz2.ApiResponse.ApiResponse;
import com.example.quiz2.Model.Student;
import com.example.quiz2.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get-students")
    public ResponseEntity getStudents() {
        ArrayList<Student> students = studentService.getStudents();
        return ResponseEntity.status(200).body(students);
    }

    @PostMapping("/add-student")
    public ResponseEntity addStudent(@Valid @RequestBody Student student, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        studentService.addStudent(student);
        return ResponseEntity.status(201).body("Student added");
    }

    @DeleteMapping("/del-student/{id}")
    public ResponseEntity delStudent(@PathVariable int id) {
        boolean isDel = studentService.deleteStudent(id);
        if (isDel) {
            return ResponseEntity.status(200).body(new ApiResponse("Student deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Student not found"));
    }

    @PutMapping("/update-student/{id}")
    public ResponseEntity updateStudent(@PathVariable int id, @Valid @RequestBody Student student, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdate = studentService.updateStudent(id, student);
        if (isUpdate){
            return ResponseEntity.status(200).body(new ApiResponse("Student updated"));
        }
            return ResponseEntity.status(400).body(new ApiResponse("Student not found"));
        }

        @GetMapping("/get-by-name/{name}")
        public ResponseEntity getStudentByName(@PathVariable String name) {
        if (studentService.getByName(name) == null){
            return ResponseEntity.status(404).body(new ApiResponse("Student not found"));
        }
        return ResponseEntity.status(200).body(studentService.getByName(name));
        }

        @GetMapping("/get-by-major/{major}")
        public ResponseEntity getByMajor(@PathVariable String major){

        if (studentService.getStudentsByMajor(major) == null)
            return ResponseEntity.status(400).body(new ApiResponse("there is no student in this major"));
        return ResponseEntity.status(200).body(studentService.getStudentsByMajor(major));
    }

}
