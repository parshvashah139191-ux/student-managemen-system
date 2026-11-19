
package com.mycompany.sudentmanagementsystem;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAO {

    // CREATE
    public void addStudent(Student student) {

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            Transaction tx = session.beginTransaction();

            try {
                session.save(student);
                tx.commit();
            } catch (RuntimeException e) {
                tx.rollback();
                throw e;
            }
        }
    }

    // READ ALL
    public List<Student> getAllStudents() {

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                "FROM Student ORDER BY id", Student.class
            ).list();
        }
    }

    // READ BY ID
    public Student getStudentById(int id) {

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            return session.get(Student.class, id);
        }
    }

    // UPDATE
    public void updateStudent(Student student) {

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            Transaction tx = session.beginTransaction();

            try {
                session.merge(student);
                tx.commit();
            } catch (RuntimeException e) {
                tx.rollback();
                throw e;
            }
        }
    }

    // DELETE
    public void deleteStudent(int id) {

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            Transaction tx = session.beginTransaction();

            try {
                Student student = session.get(Student.class, id);

                if (student != null) {
                    session.delete(student);
                }

                tx.commit();

            } catch (RuntimeException e) {
                tx.rollback();
                throw e;
            }
        }
    }
}
