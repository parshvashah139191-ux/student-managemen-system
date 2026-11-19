
package com.mycompany.sudentmanagementsystem;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory
            = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {

        try {

            Configuration config = new Configuration();

            config.setProperty(
                "hibernate.connection.driver_class",
                "com.mysql.cj.jdbc.Driver");

            config.setProperty(
                "hibernate.connection.url",
                "jdbc:mysql://localhost:3306/student_db");

            config.setProperty(
                "hibernate.connection.username", "root");

            config.setProperty(
                "hibernate.connection.password", "");

            config.setProperty(
                "hibernate.dialect",
                "org.hibernate.dialect.MySQL8Dialect");

            config.setProperty(
                "hibernate.hbm2ddl.auto", "validate");

            config.setProperty(
                "hibernate.show_sql", "true");

            config.setProperty(
                "hibernate.format_sql", "true");

            config.addAnnotatedClass(Student.class);

            return config.buildSessionFactory();

        } catch (Exception e) {

            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        sessionFactory.close();
    }
}
