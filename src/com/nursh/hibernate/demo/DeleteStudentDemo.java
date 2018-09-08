package com.nursh.hibernate.demo;


import com.nursh.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeleteStudentDemo {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                                        .configure("hibernate.cfg.xml")
                                        .addAnnotatedClass(Student.class)
                                        .buildSessionFactory();


        Session session = sessionFactory.getCurrentSession();
        try {

            int studentId = 2;
            session.beginTransaction();
            // One way of deleting student row from table
//            Student student = session.get(Student.class, studentId);
//
//            session.delete(student);

            // Alternate approach of deleting a table row
            session.createQuery("delete from Student where id=3").executeUpdate();

            session.getTransaction().commit();


        } finally {
            sessionFactory.close();
        }
    }
}
