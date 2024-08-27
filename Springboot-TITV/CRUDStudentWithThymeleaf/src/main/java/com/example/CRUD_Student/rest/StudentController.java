package com.example.CRUD_Student.rest;

import com.example.CRUD_Student.entity.Student;
import com.example.CRUD_Student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private  StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/list")
    public String getAllStudents(Model model) {
        List<Student> students = studentService.getAllStudent();
        model.addAttribute("students", students);
        return "student/students";
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("student", new Student());
        return "student/studentForm";  // Ensure this matches the Thymeleaf template name
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("student") Student student) {
        boolean result = studentService.save(student);
        return "redirect:/students/list";
    }

    @GetMapping("/update")
    public String update(@RequestParam int id, Model model) {
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "student/studentUpdateForm";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("student") Student student,Model model) {
        studentService.update(student);
        model.addAttribute("student", student);
        return "student/studentUpdateForm";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id) {
        studentService.delete(id);
        return "redirect:/students/list";
    }

//    @DeleteMapping("/delete/{id}")
//    public String deleteA(@PathVariable int id) {
//        studentService.delete(id);
//        return "redirect:/students/list";
//    }

}
