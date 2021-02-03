package repository.hibernate;

import model.Account;
import repository.AccountRepository;

import java.util.List;

public class HibernateAccountRepository implements AccountRepository {

    @Override
    public Account save(Account val) throws Exception {
        return null;
    }

    @Override
    public void deleteById(Long id) throws Exception {

    }

    @Override
    public Account getByID(Long id) throws Exception {
        return HibernateUtil.getSessionFactory().openSession().get(Account.class, id);
    }

    @Override
    public List<Account> getAll() throws Exception {
        return null;
    }

    @Override
    public Account update(Account val) throws Exception {
        return null;
    }
}
