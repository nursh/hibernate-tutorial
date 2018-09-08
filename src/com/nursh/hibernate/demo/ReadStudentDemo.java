package com.nursh.hibernate.demo;


import com.nursh.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ReadStudentDemo {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                                        .configure("hibernate.cfg.xml")
                                        .addAnnotatedClass(Student.class)
                                        .buildSessionFactory();


        Session session = sessionFactory.getCurrentSession();
        try {
            Student student = new Student("Daffy", "Duck", "crazyDuck@yahoo.com");
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();

            // Get a student
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            Student rStudent = session.get(Student.class, student.getId());
            System.out.println(rStudent);
            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
