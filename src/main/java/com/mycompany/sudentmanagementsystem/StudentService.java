
package com.mycompany.sudentmanagementsystem;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentDAO studentDAO;

    @Autowired
    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    // Add Student
    public void addStudent(Student student) {
        studentDAO.addStudent(student);
    }

    // View All Students
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    // Get Student by ID
    public Student getStudentById(int id) {
        return studentDAO.getStudentById(id);
    }

    // Update Student
    public void updateStudent(Student student) {
        studentDAO.updateStudent(student);
    }

    // Delete Student
    public void deleteStudent(int id) {
        studentDAO.deleteStudent(id);
    }
}
