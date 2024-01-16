package com.training.jdbcdemoforcrud.service.impl;

import com.training.jdbcdemoforcrud.exception.StudentNotFoundException;
import com.training.jdbcdemoforcrud.log.Logger;
import com.training.jdbcdemoforcrud.model.Student;
import com.training.jdbcdemoforcrud.repository.StudentRepository;
import com.training.jdbcdemoforcrud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired()
    Logger logger;
    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudentList() {
        logger.info("Retrieving all students from student table");
        List<Student> studentList = studentRepository.findAll();
        if (!studentList.isEmpty()) {
            return studentList;
        } else {
            throw new StudentNotFoundException("Students are not Available");
        }
    }

    @Override
    public Student addStudent(Student student) {
        logger.info("Adding new student in student table");
        return studentRepository.save(student);
    }

    @Override
    public Student getStudent(int id) {
        logger.info("Retrieving a student data from student table with provided id");
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            return optionalStudent.get();
        } else {
            throw new StudentNotFoundException("Student not found with given id: " + id);
        }
    }

    @Override
    public void deleteStudent(int id) {
        logger.info("Deleting a student from student table with provided id");
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new StudentNotFoundException("Student not found with given id: " + id);
        }
    }
}
