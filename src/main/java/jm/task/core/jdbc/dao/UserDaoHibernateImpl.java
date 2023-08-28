package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.ConnectionManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
User user = new User(name, lastName,age);
        SessionFactory sessionFactory = ConnectionManager.getSessionFactory();
        Transaction transaction = null;
        try (sessionFactory){
            Session currentSession = sessionFactory.openSession();
            transaction = currentSession.beginTransaction();
            currentSession.save(user);
            transaction.commit();

        }catch (Exception e){
            e.printStackTrace();
            assert transaction != null;
            transaction.rollback();
        }
    }

    @Override
    public void removeUserById(long id) {
        SessionFactory sessionFactory = ConnectionManager.getSessionFactory();
        try (sessionFactory) {
            Session currentSession = sessionFactory.openSession();
            currentSession.beginTransaction();
            User user = currentSession.get(User.class, id);
            currentSession.delete(user);
            currentSession.getTransaction().commit();
        } catch (Exception e) {
        }
    }
    @Override
    public List<User> getAllUsers() {
        SessionFactory sessionFactory = ConnectionManager.getSessionFactory();
        Session currentSession = sessionFactory.openSession();
        List<User> list;
        try {
            Transaction transaction = currentSession.beginTransaction();
            list = currentSession.createQuery("from User", User.class).list();
            transaction.commit();
        } finally {
            sessionFactory.close();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {

    }
}
