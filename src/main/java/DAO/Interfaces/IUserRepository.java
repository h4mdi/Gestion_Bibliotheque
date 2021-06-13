package DAO.Interfaces;

import Models.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserRepository extends IRepository<User> {
    public User auth(String login,String password) ;
        public List<User> getAllUsers();
    public void addUser(User user);
    public void deleteUser(int id);
    public void editUser(User user);
    public int GetTotalUsers() ;
    public User getPhotos(String photo) throws SQLException;


}
