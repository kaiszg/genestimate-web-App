package com.genestimate.webapp.ejbs;

import com.genestimate.webapp.authentification.AuthAccessElement;
import com.genestimate.webapp.authentification.AuthLoginElement;
import com.genestimate.webapp.model.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

/**
 * Created by Kais on 04.02.2017.
 */
@Stateless(name = "LoginEJB")
public class LoginBean {

    @EJB
    UserBean userBean;

    public AuthAccessElement login(AuthLoginElement loginElement){
        User user = userBean.findByUsernameAndPassword(loginElement.getUsername(), loginElement.getPassword());
        if (user != null) {
            String authToken = UUID.randomUUID().toString();
            user.setAuthToken(authToken);
            userBean.update(user);
            return new AuthAccessElement(loginElement.getUsername(), authToken, user.getAuthRole());
        }
        return null;
    }

    public void logout(String authId, String authToken) {
        User user = userBean.findByUsernameAndAuthToken(authId, authToken);
        user.setAuthToken(null);
        userBean.update(user);
    }
}
