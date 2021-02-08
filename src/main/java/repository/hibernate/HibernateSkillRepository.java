package repository.hibernate;

import model.Skill;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.SkillRepository;
import java.util.List;

public class HibernateSkillRepository implements SkillRepository {
    @Override
    public Skill save(Skill val) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Long generatedId = (Long) session.save(val);
        transaction.commit();
        val = session.get(Skill.class, generatedId);
        session.close();
        return val;
    }

    @Override
    public void deleteById(Long id) throws Exception {
        Skill skill = getByID(id);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(skill);
        transaction.commit();
        session.close();
    }

    @Override
    public Skill getByID(Long id) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Skill skill = session.get(Skill.class, id);
        session.close();
        return skill;
    }

    @Override
    public List<Skill> getAll() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Skill> skills = session.createQuery("From Skill").list();
        session.close();
        return skills;
    }

    @Override
    public Skill update(Skill val) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(val);
        transaction.commit();
        session.close();
        return val;
    }
}
