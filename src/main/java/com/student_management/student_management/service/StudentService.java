package com.student_management.student_management.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.student_management.student_management.model.Student;
import com.student_management.student_management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private static StudentRepository studentRepository;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static ObjectNode createStudent(JsonNode jsonNode) {
        ObjectNode response = objectMapper.createObjectNode();
        Student student;

        if (jsonNode.has("studentId")) {
            Integer studentId = jsonNode.get("studentId").asInt();
            Optional<Student> optionalStudent = studentRepository.findById(studentId);

            if (optionalStudent.isPresent()) {
                student = optionalStudent.get();
            } else {
                response.put("status", "error");
                response.put("message", "Student is not available");
                return response;
            }
        } else {
            student = new Student();
        }

        student.setStudentId(jsonNode.get("studentId").asInt());
        student.setStudentName(jsonNode.get("studentName").asText());
        student.setStudentEmail(jsonNode.get("studentEmail").asText());
        student.setStudentPhone(jsonNode.get("studentPhone").asText());

        studentRepository.save(student);

        response.put("status", "success");
        response.put("message", "Student created successfully");
        response.put("studentId", student.getStudentId());

        return response;
    }
}
