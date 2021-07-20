package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory = Util.getSessionFactory();
    Session session;
    Transaction transaction = null;

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() throws SQLException, HibernateException {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (" +
                "`id` INT NOT NULL AUTO_INCREMENT," +
                "`name` VARCHAR(45) NOT NULL," +
                "`lastName` VARCHAR(45) NOT NULL," +
                "`age` INT(3) NOT NULL," +
                "PRIMARY KEY (`id`))" +
                "DEFAULT CHARACTER SET = utf8;").executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() throws SQLException, HibernateException {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws HibernateException {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        User newUserForSave = new User(name, lastName, age);
        session.save(newUserForSave);
        System.out.println("User с именем – " + name + " добавлен в базу данных");
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) throws HibernateException {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        User userById = (User) session.get(User.class, id);
        session.delete(userById);
        transaction.commit();
        session.close();
    }


    @Override
    public List<User> getAllUsers() throws HibernateException {
        session = sessionFactory.openSession();
        List<User> userList = new ArrayList<User>();
        transaction = session.beginTransaction();
        userList = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        System.out.println(userList);
        return userList;
    }

    @Override
    public void cleanUsersTable() throws HibernateException {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.createSQLQuery("DELETE FROM users").executeUpdate();
        transaction.commit();
        session.close();
    }
}


