package com.graphql.example.service.implementation;

import com.graphql.example.entities.Student;
import com.graphql.example.persistence.IStudentDAO;
import com.graphql.example.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private IStudentDAO studentDAO;

    @Override
    @Transactional(readOnly = true)
    public Student findById(Long id) {
        Optional<Student> optionalStudent = studentDAO.findById(id);
        if (optionalStudent.isPresent()) {
            return optionalStudent.get();
        }        //return studentDAO.findById(id).orElseThrow();
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> findAll() {
        return (List<Student>) studentDAO.findAll();
    }

    @Override
    @Transactional
    public Student createCourse(Student student) {
        studentDAO.save(student);
        return student;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        studentDAO.deleteById(id);
    }
}
