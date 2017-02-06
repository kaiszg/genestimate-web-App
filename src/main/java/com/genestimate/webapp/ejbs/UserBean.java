package com.genestimate.webapp.ejbs;

import com.genestimate.webapp.authentification.EncryptPasswordFunction;
import com.genestimate.webapp.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Kais on 04.02.2017.
 */
@Stateless(name = "UserEJB")
public class UserBean {

    private final Logger LOGGER = LoggerFactory.getLogger(UserBean.class);

    @PersistenceContext
    EntityManager em;

    public List<User> findAll(){
        LOGGER.info("retrieving all users");
        List<User> result = em.createQuery("FROM User").getResultList();
        return result;
    }

    public User findByID(int id){
        LOGGER.info("retrieving user with id " + id);
        User user = em.find(User.class, id);
        return user;
    }

    public User findByUsernameAndPassword(String username, String password){
        LOGGER.info("retrieving user with username \"" + username + "\" and password \"" + password + "\"");
        List<User> results = em.createQuery("FROM User u WHERE u.username LIKE :username AND u.password LIKE :password")
                .setParameter("username", username)
                .setParameter("password", EncryptPasswordFunction.get_SHA_512_SecurePassword(password))
                .getResultList();
        if(!results.isEmpty()){
            return results.get(0);
        }
        return null;
    }

    public User findByUsernameAndAuthToken(String username, String authToken) {
        LOGGER.info("retrieving user with username \"" + username + "\" and token \"" + authToken + "\"");
        List<User> results = em.createQuery("FROM User u WHERE u.username LIKE :username AND u.authToken LIKE :authToken")
                .setParameter("username", username)
                .setParameter("authToken", authToken)
                .getResultList();
        if(!results.isEmpty()){
            return results.get(0);
        }
        return null;
    }

    public User create(User user){
        user.setPassword(EncryptPasswordFunction.get_SHA_512_SecurePassword(user.getPassword()));
        em.persist(user);
        em.flush();
        LOGGER.info("user created with success");
        return user;
    }

    public User update(User user){
        em.merge(user);
        em.flush();
        LOGGER.info("user updated with success");
        return user;
    }

    public void delete(int id){
        User user = this.findByID(id);
        em.remove(user);
        LOGGER.info("user deleted with success");
        em.flush();
    }
}
