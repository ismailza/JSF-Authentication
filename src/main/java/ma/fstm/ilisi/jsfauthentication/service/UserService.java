package ma.fstm.ilisi.jsfauthentication.service;

import ma.fstm.ilisi.jsfauthentication.dao.UserDAO;
import ma.fstm.ilisi.jsfauthentication.model.User;

public class UserService {
    private UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    /**
     * Authenticate a user with login and password
     * @param login email or username
     * @param password password
     * @return User if authenticated, null otherwise
     */
    public User authenticate(String login, String password) {
        User user = userDAO.findByLogin(login);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    /**
     * Register a new user
     * @param user user to register
     * @return true if the user is registered, false otherwise
     */
    public boolean register(User user) {
        return userDAO.create(user);
    }

    /**
     * Check if an email is already used
     * @param email email to check
     * @return true if the email is already used, false otherwise
     */
    public boolean checkEmail(String email) {
        return userDAO.checkEmail(email);
    }

    /**
     * Check if a username is already used
     * @param username username to check
     * @return true if the username is already used, false otherwise
     */
    public boolean checkUsername(String username) {
        return userDAO.checkUsername(username);
    }

}
