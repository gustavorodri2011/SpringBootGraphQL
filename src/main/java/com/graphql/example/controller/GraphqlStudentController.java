package com.graphql.example.controller;

import com.graphql.example.entities.Course;
import com.graphql.example.entities.Student;
import com.graphql.example.graphql.InputStudent;
import com.graphql.example.service.ICourseService;
import com.graphql.example.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphqlStudentController {
    @Autowired
    private IStudentService studentService;
    @Autowired
    private ICourseService courseService;

    @QueryMapping(name = "findStudentById")
    public Student findById(@Argument(name = "studentId") String id) {
        Long studentId = Long.parseLong(id);
        return studentService.findById(studentId);
    }

    @QueryMapping(name = "findAllStudents")
    public List<Student> findByAll() {
        return studentService.findAll();
    }

    @MutationMapping
    public Student createStudent(@Argument InputStudent inputStudent) {
        Long courseId = Long.parseLong(inputStudent.getCourseId());
        Course course = courseService.findById(courseId);

        Student studentNew = new Student();
        studentNew.setName(inputStudent.getName());
        studentNew.setLastName(inputStudent.getLastName());
        studentNew.setAge(inputStudent.getAge());
        studentNew.setCourse(course);

        return studentService.createCourse(studentNew);
    }

    @MutationMapping(name = "deleteStudentById")
    public String deleteStudent(@Argument String studentId) {
        Student student = studentService.findById(Long.parseLong(studentId));
        if (student == null) {
            return "Sorry the Student whith id " + studentId + " not found.";
        }
        studentService.deleteById(Long.parseLong(studentId));
        return "Student whith id " + studentId + " was deleted.";
    }
}
