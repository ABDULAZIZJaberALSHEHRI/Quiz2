package com.example.quiz2.Controller;

import com.example.quiz2.ApiResponse.ApiResponse;
import com.example.quiz2.Model.Teacher;
import com.example.quiz2.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("/get-teachers")
    public ResponseEntity getTeachers(){
        return ResponseEntity.status(200).body(teacherService.getTeachers());
    }

    @PostMapping("/add-teacher")
    public ResponseEntity addTeacher(@Valid @RequestBody Teacher teacher, Errors errors) {
        if(errors.hasErrors()){
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        teacherService.addTeacher(teacher);
        return ResponseEntity.status(200).body(new ApiResponse("Teacher added successfully"));
    }

    @DeleteMapping("/del-teacher/{id}")
    public ResponseEntity delTeacher(@PathVariable int id) {
        boolean isDel = teacherService.removeTeacher(id);
        if(isDel){
            return ResponseEntity.status(200).body(new ApiResponse("Teacher deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Teacher not found"));
    }

    @PutMapping("update-teacher/{id}")
    public ResponseEntity updateTeacher(@PathVariable int id,@Valid @RequestBody Teacher teacher,Errors errors) {
        if(errors.hasErrors()){
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean isUpdated = teacherService.updateTeacher(id,teacher);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("Teacher updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Teacher not found"));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity getTeacher(@PathVariable int id) {
        if(teacherService.getTeacher(id) == null){
            return ResponseEntity.status(404).body(new ApiResponse("Teacher not found"));
        }
        return ResponseEntity.status(200).body(teacherService.getTeacher(id));
    }


    @GetMapping("/get-by-salary/{salary}")
    public ResponseEntity getBySalary(@PathVariable int salary){

        if (teacherService.getTeacherBySalary(salary) == null) {
            return ResponseEntity.status(404).body(new ApiResponse("Teacher who has this salary or above not found"));
        }
        return ResponseEntity.status(200).body(teacherService.getTeacherBySalary(salary));
    }


}
