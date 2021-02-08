package repository.hibernate;

import model.Developer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.DeveloperRepository;

import java.util.List;

public class HibernateDeveloperRepository implements DeveloperRepository {
    @Override
    public Developer save(Developer val) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Long generatedId = (Long) session.save(val);
        transaction.commit();
        val = session.get(Developer.class, generatedId);
        session.close();
        return val;
    }

    @Override
    public void deleteById(Long id) throws Exception {
        Developer developer = getByID(id);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(developer);
        transaction.commit();
        session.close();
    }

    @Override
    public Developer getByID(Long id) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Developer developer = session.get(Developer.class, id);
        session.close();
        return developer;
    }

    @Override
    public List<Developer> getAll() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Developer> developer = session.createQuery("From Developer").list();
        session.close();
        return developer;
    }

    @Override
    public Developer update(Developer val) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(val);
        transaction.commit();
        session.close();
        return val;
    }
}
