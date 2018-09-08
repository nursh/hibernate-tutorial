package com.nursh.hibernate.demo;


import com.nursh.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class UpdateStudentDemo {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                                        .configure("hibernate.cfg.xml")
                                        .addAnnotatedClass(Student.class)
                                        .buildSessionFactory();


        Session session = sessionFactory.getCurrentSession();
        try {

            int studentId = 2;
            session.beginTransaction();
            Student student = session.get(Student.class, studentId);
            student.setEmail("goofy@gmail.com");
            session.getTransaction().commit();

            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("update Student set email='legendaryTroller@yahoo.com'")
                    .executeUpdate();
            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
