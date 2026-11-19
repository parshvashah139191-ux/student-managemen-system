
package com.mycompany.sudentmanagementsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class StudentGUI extends JFrame {

    JTextField id = new JTextField();
    JTextField name = new JTextField();
    JTextField email = new JTextField();
    JTextField course = new JTextField();
    JTextField age = new JTextField();
    JTextField phone = new JTextField();

    DefaultTableModel model = new DefaultTableModel(
        new String[]{"ID", "Name", "Email",
                     "Course", "Age", "Phone"}, 0);

    JTable table = new JTable(model);

    public StudentGUI() {

        setTitle("Student Management System");
        setSize(900, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel form = new JPanel(new GridLayout(6, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        id.setEditable(false);

        form.add(new JLabel("Student ID"));
        form.add(id);

        form.add(new JLabel("Student Name"));
        form.add(name);

        form.add(new JLabel("Email"));
        form.add(email);

        form.add(new JLabel("Course"));
        form.add(course);

        form.add(new JLabel("Age"));
        form.add(age);

        form.add(new JLabel("Phone"));
        form.add(phone);

        JButton add = new JButton("Add");
        JButton update = new JButton("Update");
        JButton delete = new JButton("Delete");
        JButton view = new JButton("View");
        JButton clear = new JButton("Clear");

        JPanel buttons = new JPanel();

        buttons.add(add);
        buttons.add(update);
        buttons.add(delete);
        buttons.add(view);
        buttons.add(clear);

        JPanel top = new JPanel(new BorderLayout());
        top.add(form, BorderLayout.CENTER);
        top.add(buttons, BorderLayout.SOUTH);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        add.addActionListener(e -> saveStudent(false));
        update.addActionListener(e -> saveStudent(true));
        delete.addActionListener(e -> deleteStudent());
        view.addActionListener(e -> loadStudents());
        clear.addActionListener(e -> clearFields());

        table.setDefaultEditor(Object.class, null);

        table.getSelectionModel().addListSelectionListener(e -> {

            int row = table.getSelectedRow();

            if (row >= 0) {
                id.setText(model.getValueAt(row, 0).toString());
                name.setText(model.getValueAt(row, 1).toString());
                email.setText(model.getValueAt(row, 2).toString());
                course.setText(model.getValueAt(row, 3).toString());
                age.setText(model.getValueAt(row, 4).toString());
                phone.setText(java.util.Objects.toString(
                    model.getValueAt(row, 5), ""));
            }
        });

        loadStudents();
    }

    private boolean validateForm() {

        if (name.getText().trim().isEmpty() ||
            email.getText().trim().isEmpty() ||
            course.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                "Please fill all required fields.");
            return false;
        }

        if (!email.getText().trim().matches(
                "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {

            JOptionPane.showMessageDialog(this,
                "Enter a valid email address.");
            return false;
        }

        try {
            int studentAge = Integer.parseInt(age.getText().trim());

            if (studentAge < 1 || studentAge > 100) {
                throw new NumberFormatException();
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this,
                "Enter a valid age.");
            return false;
        }

        if (!phone.getText().trim().matches("[0-9]{0,15}")) {
            JOptionPane.showMessageDialog(this,
                "Enter a valid phone number.");
            return false;
        }

        return true;
    }

    private void saveStudent(boolean updating) {

        if (updating && id.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Select a student first.");
            return;
        }

        if (!validateForm()) return;

        String sql = updating
            ? "UPDATE students SET name=?, email=?, course=?, age=?, phone=? WHERE id=?"
            : "INSERT INTO students(name,email,course,age,phone) VALUES(?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, name.getText().trim());
            pst.setString(2, email.getText().trim());
            pst.setString(3, course.getText().trim());
            pst.setInt(4, Integer.parseInt(age.getText().trim()));
            pst.setString(5, phone.getText().trim());

            if (updating) {
                pst.setInt(6, Integer.parseInt(id.getText()));
            }

            pst.executeUpdate();

            JOptionPane.showMessageDialog(this,
                updating ? "Student Updated!" : "Student Added!");

            clearFields();
            loadStudents();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void deleteStudent() {

        if (id.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Select a student first.");
            return;
        }

        int choice = JOptionPane.showConfirmDialog(this,
            "Delete this student?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION);

        if (choice != JOptionPane.YES_OPTION) return;

        String sql = "DELETE FROM students WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, Integer.parseInt(id.getText()));
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this,
                "Student Deleted!");

            clearFields();
            loadStudents();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void loadStudents() {

        model.setRowCount(0);

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM students")) {

            while (rs.next()) {

                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("course"),
                    rs.getInt("age"),
                    rs.getString("phone")
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void clearFields() {

        table.clearSelection();
        id.setText("");
        name.setText("");
        email.setText("");
        course.setText("");
        age.setText("");
        phone.setText("");
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new StudentGUI().setVisible(true);
        });
    }
}
