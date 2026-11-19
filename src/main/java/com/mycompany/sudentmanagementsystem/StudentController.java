
package com.mycompany.sudentmanagementsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Home Page
    @GetMapping("/")
    public String home() {
        return "redirect:/students";
    }

    // View All Students
    @GetMapping("/students")
    public String viewStudents(Model model) {

        model.addAttribute("students",
                studentService.getAllStudents());

        return "students";
    }

    // Add Student Form
    @GetMapping("/add")
    public String addForm(Model model) {

        model.addAttribute("student", new Student());

        return "student-form";
    }

    // Save Student
    @PostMapping("/save")
    public String saveStudent(
            @ModelAttribute Student student,
            Model model,
            RedirectAttributes redirect) {

        // Form Validation
        if (student.getName() == null ||
            student.getName().trim().isEmpty() ||
            student.getEmail() == null ||
            !student.getEmail().matches(
                "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$") ||
            student.getCourse() == null ||
            student.getCourse().trim().isEmpty() ||
            student.getAge() < 1 ||
            student.getAge() > 100 ||
            (student.getPhone() != null &&
             !student.getPhone().matches("[0-9]{0,15}"))) {

            model.addAttribute("error",
                    "Please enter valid student details.");

            return "student-form";
        }

        try {

            if (student.getId() == 0) {

                studentService.addStudent(student);

                redirect.addFlashAttribute(
                    "message", "Student Added Successfully!");

            } else {

                studentService.updateStudent(student);

                redirect.addFlashAttribute(
                    "message", "Student Updated Successfully!");
            }

        } catch (Exception e) {

            model.addAttribute("error",
                "Unable to save student. Check the details.");

            return "student-form";
        }

        return "redirect:/students";
    }

    // Edit Student
    @GetMapping("/edit")
    public String editStudent(
            @RequestParam("id") int id,
            Model model) {

        Student student = studentService.getStudentById(id);

        if (student == null) {
            return "redirect:/students";
        }

        model.addAttribute("student", student);

        return "student-form";
    }

    // Delete Student
    @PostMapping("/delete")
    public String deleteStudent(
            @RequestParam("id") int id,
            RedirectAttributes redirect) {

        try {

            studentService.deleteStudent(id);

            redirect.addFlashAttribute(
                "message", "Student Deleted Successfully!");

        } catch (Exception e) {

            redirect.addFlashAttribute(
                "error", "Unable to delete student.");
        }

        return "redirect:/students";
    }
}
