package repository.hibernate;

import model.Account;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    static {
        try {
//            sessionFactory = new Configuration().configure().buildSessionFactory();
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Account.class);
                sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable e){
            System.err.println("Initial SessionFactory creation failed. "+ e);
//             e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
