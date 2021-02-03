package repository.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable e){
            System.err.println("Initial SessionFactory creation failed. "+ e);
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
