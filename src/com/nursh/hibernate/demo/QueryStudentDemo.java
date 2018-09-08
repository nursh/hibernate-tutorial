package com.nursh.hibernate.demo;


import com.nursh.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class QueryStudentDemo {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                                        .configure("hibernate.cfg.xml")
                                        .addAnnotatedClass(Student.class)
                                        .buildSessionFactory();


        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            List<Student> students = session.createQuery("from Student").getResultList();

            displayStudents(students);


            System.out.println("\n\n\nStudents with last name of Bunny");
            students = session
                    .createQuery("from Student s where s.lastName='Bunny'")
                    .getResultList();

            displayStudents(students);


            System.out.println("\n\n\nStudents with last name of Bunny OR first name of Daffy");
            students = session
                    .createQuery("from Student s where s.lastName='Bunny' OR s.firstName='Daffy'")
                    .getResultList();

            displayStudents(students);

            System.out.println("\n\n\nStudents with email ending in yahoo.com");
            students = session
                    .createQuery("from Student s where s.email LIKE '%yahoo.com' ")
                    .getResultList();

            displayStudents(students);


            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }

    private static void displayStudents(List<Student> students) {
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
