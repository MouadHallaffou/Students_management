package com.student_management.student_management.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.student_management.student_management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    
    @PostMapping("/create")
    public JsonNode createStudent(@RequestBody JsonNode jsonNode) {
        return studentService.createStudent(jsonNode);
    }

    @GetMapping("/getAll")
    public JsonNode getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{studentId}")
    public JsonNode getStudentById(@PathVariable Integer studentId) {
        return studentService.getStudentById(studentId);
    }

    @DeleteMapping("/delete/{studentId}")
    public JsonNode deleteStudent(@PathVariable Integer studentId) {
        return studentService.deleteStudent(studentId);
    }

    @PutMapping("/update/{studentId}")
    public JsonNode updateStudent(@PathVariable Integer studentId, @RequestBody JsonNode jsonNode) {
        return studentService.updateStudent(studentId, jsonNode);
    }
    
}
