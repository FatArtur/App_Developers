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
        return HibernateUtil.getSessionFactory().openSession().get(Developer.class, id);
    }

    @Override
    public List<Developer> getAll() throws Exception {
        List<Developer> list = HibernateUtil.getSessionFactory().openSession().createQuery("From Developer").list();
        return list;
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
