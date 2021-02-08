package repository.hibernate;

import model.Account;
import model.AccountStatus;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.AccountRepository;

import java.util.List;

public class HibernateAccountRepository implements AccountRepository {

    @Override
    public Account save(Account val) throws Exception {
        val.setAccountStatus(AccountStatus.ACTIVE);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Long generatedId = (Long) session.save(val);
        transaction.commit();
        val = session.get(Account.class, generatedId);
        session.close();
        return val;
    }

    @Override
    public void deleteById(Long id) throws Exception {
        Account account = getByID(id);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(account);
        transaction.commit();
        session.close();
    }

    @Override
    public Account getByID(Long id) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Account account = session.get(Account.class, id);
        session.close();
        return account;
    }

    @Override
    public List<Account> getAll() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Account> list = session.createQuery("From Account").list();
        session.close();
        return list;
    }

    @Override
    public Account update(Account val) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(val);
        transaction.commit();
        session.close();
        return val;
    }
}
