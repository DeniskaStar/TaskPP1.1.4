package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String SQL = "CREATE TABLE IF NOT EXISTS Users " +
                "(id INT AUTO_INCREMENT, " +
                "name VARCHAR (45) NOT NULL, " +
                "lastname VARCHAR (45) NOT NULL, " +
                "age INT NOT NULL, " +
                "PRIMARY KEY (id))";

        session.createSQLQuery(SQL).addEntity(User.class).executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String SQL = "DROP TABLE IF EXISTS Users ";
        session.createSQLQuery(SQL).addEntity(User.class).executeUpdate();

        session.getTransaction().commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        session.save(new User(name, lastName, age));

        session.getTransaction().commit();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        User user = (User) session.get(User.class, id);
        session.delete(user);

        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List<User> usersList = session.createQuery("from User").list();

        session.getTransaction().commit();
        return usersList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete User").executeUpdate();
        session.getTransaction().commit();
    }
}
