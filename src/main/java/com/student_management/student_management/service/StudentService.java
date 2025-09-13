package com.student_management.student_management.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.student_management.student_management.model.Student;
import com.student_management.student_management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public ObjectNode createStudent(JsonNode jsonNode) {
        ObjectNode response = objectMapper.createObjectNode();
        Student student;

        Integer studentId = null;
        if (jsonNode.has("studentId") && jsonNode.get("studentId") != null) {
            studentId = jsonNode.get("studentId").asInt();
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

        // Si studentId est présent, le setter est appelé, sinon ignoré
        if (jsonNode.has("studentId") && jsonNode.get("studentId") != null) {
            student.setStudentId(jsonNode.get("studentId").asInt());
        }
        if (jsonNode.has("studentName") && jsonNode.get("studentName") != null) {
            student.setStudentName(jsonNode.get("studentName").asText());
        }
        if (jsonNode.has("studentEmail") && jsonNode.get("studentEmail") != null) {
            student.setStudentEmail(jsonNode.get("studentEmail").asText());
        }
        if (jsonNode.has("studentPhone") && jsonNode.get("studentPhone") != null) {
            student.setStudentPhone(jsonNode.get("studentPhone").asText());
        }

        studentRepository.save(student);

        response.put("status", "success");
        response.put("message", "Student created successfully");
        response.put("studentId", student.getStudentId());

        return response;
    }

    public JsonNode getAllStudents() {
        ObjectNode response = objectMapper.createObjectNode();

        List<Student> studentList =  studentRepository.findAll();
        if (studentList.isEmpty()) {
            response.put("status", "error");
            response.put("message", "Student list is empty");
            return response;
        }

        ArrayNode arrayNode = objectMapper.createArrayNode();
        if (studentList != null && !studentList.isEmpty()) {
            for (Student student : studentList) {
                ObjectNode studentNode = objectMapper.createObjectNode();
                studentNode.put("studentId", student.getStudentId());
                studentNode.put("studentName", student.getStudentName());
                studentNode.put("studentEmail", student.getStudentEmail());
                studentNode.put("studentPhone", student.getStudentPhone());
                arrayNode.add(studentNode);
            }
        }
        response.put("status", "success");
        response.put("message", "All students fetched successfully");
        response.set("students", arrayNode);

        return response;
    }

    public JsonNode getStudentById(Integer studentId) {
        ObjectNode response = objectMapper.createObjectNode();

        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            ObjectNode studentNode = objectMapper.createObjectNode();
            studentNode.put("studentId", student.getStudentId());
            studentNode.put("studentName", student.getStudentName());
            studentNode.put("studentEmail", student.getStudentEmail());
            studentNode.put("studentPhone", student.getStudentPhone());

            response.put("status", "success");
            response.put("message", "Student fetched successfully");
            response.set("student", studentNode);
        } else {
            response.put("status", "error");
            response.put("message", "Student not found");
        }

        return response;
    }

    public JsonNode deleteStudent(Integer studentId) {
        ObjectNode response = objectMapper.createObjectNode();

        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            studentRepository.delete(student);
            response.put("status", "success");
            response.put("message", "Student " + student.getStudentName() + " deleted successfully");
        } else {
            response.put("status", "error");
            response.put("message", "Student not found");
        }

        return response;
    }

    public JsonNode updateStudent(Integer studentId, JsonNode jsonNode) {
        ObjectNode response = objectMapper.createObjectNode();

        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();

            if (jsonNode.has("studentName") && jsonNode.get("studentName") != null) {
                student.setStudentName(jsonNode.get("studentName").asText());
            }
            if (jsonNode.has("studentEmail") && jsonNode.get("studentEmail") != null) {
                student.setStudentEmail(jsonNode.get("studentEmail").asText());
            }
            if (jsonNode.has("studentPhone") && jsonNode.get("studentPhone") != null) {
                student.setStudentPhone(jsonNode.get("studentPhone").asText());
            }

            studentRepository.save(student);

            response.put("status", "success");
            response.put("message", "Student updated successfully");
            response.put("studentId", student.getStudentId());
        } else {
            response.put("status", "error");
            response.put("message", "Student not found");
        }

        return response;
    }

}
