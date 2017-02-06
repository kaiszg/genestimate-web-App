package com.genestimate.webapp.services;

import com.genestimate.webapp.authentification.AuthAccessElement;
import com.genestimate.webapp.ejbs.EstimateBean;
import com.genestimate.webapp.ejbs.UserBean;
import com.genestimate.webapp.model.Estimate;
import com.genestimate.webapp.model.Properties;
import com.genestimate.webapp.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.RolesAllowed;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Kais on 01.02.2017.
 */

@Path("/estimates")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class EstimateService {

    private final Logger LOGGER = LoggerFactory.getLogger(EstimateService.class);

    EstimateBean estimateBean;
    UserBean userBean;

    public EstimateService() {
        try {
            estimateBean = (EstimateBean) new InitialContext().lookup("java:module/EstimateEJB!com.genestimate.webapp.ejbs.EstimateBean");
            userBean = (UserBean) new InitialContext().lookup("java:module/UserEJB!com.genestimate.webapp.ejbs.UserBean");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"USER", "ADMIN"})
    public Estimate createEstimate(@Context HttpServletRequest request, Properties properties){
        String authId = request.getHeader(AuthAccessElement.PARAM_AUTH_ID);
        String authToken = request.getHeader(AuthAccessElement.PARAM_AUTH_TOKEN);
        User user = userBean.findByUsernameAndAuthToken(authId, authToken);
        properties.setClient(user.getClient());
        return estimateBean.createFromProperties(properties);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"ADMIN"})
    public List<Estimate> getAllEstimates(){
        List<Estimate> results = estimateBean.findAll();
        return results;
    }

    @GET
    @Path("/my-estimates")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"USER", "ADMIN"})
    public List<Estimate> getEstimatesOfCurrentUser(@Context HttpServletRequest request){
        String authId = request.getHeader(AuthAccessElement.PARAM_AUTH_ID);
        String authToken = request.getHeader(AuthAccessElement.PARAM_AUTH_TOKEN);
        User user = userBean.findByUsernameAndAuthToken(authId, authToken);
        List<Estimate> results = estimateBean.getEstimatesOfClient(user.getClient());
        return results;
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"USER", "ADMIN"})
    public Estimate getEstimateByID(@PathParam("id") int id){
        return estimateBean.findByID(id);
    }
}
