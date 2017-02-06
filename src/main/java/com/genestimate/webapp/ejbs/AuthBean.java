package com.genestimate.webapp.ejbs;

import com.genestimate.webapp.model.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Set;

/**
 * Created by Kais on 04.02.2017.
 */
@Stateless(name = "AuthEJB")
public class AuthBean {


    @EJB
    UserBean userBean;

    public boolean isAuthorized(String authId, String authToken, Set<String> rolesAllowed) {
        User user = userBean.findByUsernameAndAuthToken(authId, authToken);
        if (user != null) {
            return rolesAllowed.contains(user.getAuthRole());
        } else {
            return false;
        }
    }
}
