package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDao daoHib = new UserDaoHibernateImpl();

    public void createUsersTable() throws SQLException {
        daoHib.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        daoHib.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        daoHib.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) throws SQLException {
        daoHib.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return daoHib.getAllUsers();
    }

    public void cleanUsersTable() throws SQLException {
        daoHib.cleanUsersTable();
    }
}
