package Controller;

import Models.User;
import db.UserDAO;

public class UserController {
    private UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserDAO();
    }

    public boolean register(String username, String email, String password) {
        User user = new User(0, username, email, password);
        return userDAO.registerUser(user);
    }

    public User login(String email, String password) {
        return userDAO.loginUser(email, password);
    }
}
