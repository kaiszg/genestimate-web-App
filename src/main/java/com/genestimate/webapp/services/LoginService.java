package com.genestimate.webapp.services;

import com.genestimate.webapp.authentification.AuthAccessElement;
import com.genestimate.webapp.authentification.AuthLoginElement;
import com.genestimate.webapp.ejbs.LoginBean;
import com.genestimate.webapp.ejbs.UserBean;
import com.genestimate.webapp.model.Client;
import com.genestimate.webapp.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.PermitAll;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Created by Kais on 04.02.2017.
 */

@Path("/auth")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class LoginService {

    private Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

    private LoginBean loginBean;
    private UserBean userBean;

    public LoginService() {
        try {
            this.loginBean = (LoginBean) new InitialContext().lookup("java:module/LoginEJB!com.genestimate.webapp.ejbs.LoginBean");
            this.userBean = (UserBean) new InitialContext().lookup("java:module/UserEJB!com.genestimate.webapp.ejbs.UserBean");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @POST
    @Path("login")
    @PermitAll
    public AuthAccessElement login(@Context HttpServletRequest request, AuthLoginElement loginElement) {
        createTestUsers();
        AuthAccessElement accessElement = loginBean.login(loginElement);
        if (accessElement != null) {
            request.getSession().setAttribute(AuthAccessElement.PARAM_AUTH_ID, accessElement.getAuthId());
            request.getSession().setAttribute(AuthAccessElement.PARAM_AUTH_TOKEN, accessElement.getAuthToken());
        }
        return accessElement;
    }

    private void createTestUsers(){
        User user = new User();
        user.setUsername("user");
        user.setPassword("user");
        user.setAuthRole("USER");

        Client client = new Client();
        client.setName("User Max Mustermann");
        client.setStreetAndHouseNr("Musterstraße 123");
        client.setCity("Musterstadt");
        client.setPostalCode(12345);
        client.setCountry("Deutschland");
        client.setPhone("011234567890");

        user.setClient(client);
        userBean.create(user);

        user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setAuthRole("ADMIN");

        client = new Client();
        client.setName("Admin Max Mustermann");
        client.setCity("Musterstadt");
        client.setStreetAndHouseNr("Musterstraße 123");
        client.setPostalCode(12345);
        client.setCountry("Deutschland");
        client.setPhone("01234567891011");

        user.setClient(client);
        userBean.create(user);
    }

    @GET
    @PermitAll
    @Path("logout")
    public void logout(@Context HttpServletRequest request){
        String authId = (String) request.getSession().getAttribute(AuthAccessElement.PARAM_AUTH_ID);
        String authToken = (String) request.getSession().getAttribute(AuthAccessElement.PARAM_AUTH_TOKEN);

        if (authId!= null && authToken!= null){
            loginBean.logout(authId, authToken);
            request.getSession().invalidate();
        }
    }
}
