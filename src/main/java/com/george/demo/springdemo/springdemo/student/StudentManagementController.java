package com.george.demo.springdemo.springdemo.student;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "management/api/v1/student")
public class StudentManagementController {

    private static final List<Student> students = Arrays.asList(
            new Student(1, "first student"),
            new Student(2, "second student"),
            new Student(3, "third student")
    );

    @GetMapping
    List<Student> getStudents() { return students; }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {return student;}

    @DeleteMapping(path = "{id}")
    public Student deleteStudent(@PathVariable("id") Integer id) {
        Student find = null;
        for (Student student : students) {
            if (Objects.equals(student.getId(), id)) find = student; 
        }
        return find;
    }

    @PutMapping(path = "{id}")
    public Student updateStudent(@PathVariable("id") Integer id, @RequestBody Student student) {
        for (Student s : students) {
            if (Objects.equals(s.getId(), id)) s = student;
        }
        return student;
    }


}

