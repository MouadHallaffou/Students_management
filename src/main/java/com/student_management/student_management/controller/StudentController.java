package com.student_management.student_management.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.student_management.student_management.model.Student;
import com.student_management.student_management.repository.StudentRepository;
import com.student_management.student_management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping("/create")
    public JsonNode createStudent(@RequestBody JsonNode JsonNode) {
        return StudentService.createStudent(JsonNode);
    }
}
