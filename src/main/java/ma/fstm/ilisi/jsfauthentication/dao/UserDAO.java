package ma.fstm.ilisi.jsfauthentication.dao;

import ma.fstm.ilisi.jsfauthentication.model.User;
import ma.fstm.ilisi.jsfauthentication.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDAO {

    /**
     * Find a user by login (email or username) in the database
     * @param login email or username
     * @return User if found, null otherwise
     */
    public User findByLogin(String login) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            User user = session.createQuery("from User where email = :email or username = :username", User.class)
                    .setParameter("email", login)
                    .setParameter("username", login)
                    .uniqueResult();
            transaction.commit();
            return user;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Create a user in the database
     * @param user the user to create
     * @return true if the user is created, false otherwise
     */
    public boolean create(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Check if an email is already used
     * @param email email to check
     * @return true if the email is already used, false otherwise
     */
    public boolean checkEmail(String email) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            User user = session.createQuery("from User where email = :email", User.class)
                    .setParameter("email", email)
                    .uniqueResult();
            transaction.commit();
            return user != null;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Check if a username is already used
     * @param username username to check
     * @return true if the username is already used, false otherwise
     */
    public boolean checkUsername(String username) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            User user = session.createQuery("from User where username = :username", User.class)
                    .setParameter("username", username)
                    .uniqueResult();
            transaction.commit();
            return user != null;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

}
