
package com.mycompany.sudentmanagementsystem;

import org.hibernate.Session;
import java.util.List;

public class HibernateTest {

    public static void main(String[] args) {

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            List<Student> students = session
                    .createQuery("FROM Student", Student.class)
                    .list();

            System.out.println("Hibernate Connected Successfully!");
            System.out.println("Total Students: " + students.size());

            for (Student student : students) {

                System.out.println(
                    student.getId() + " | " +
                    student.getName() + " | " +
                    student.getEmail() + " | " +
                    student.getCourse()
                );
            }

        } catch (Exception | LinkageError e) {

            e.printStackTrace();

        } finally {

            HibernateUtil.shutdown();
        }
    }
}
