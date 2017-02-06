package com.genestimate.webapp.services;

import com.genestimate.webapp.ejbs.BindingTypeBean;
import com.genestimate.webapp.model.BindingType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.RolesAllowed;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Kais on 30.01.2017.
 */

@Path("/binding-types")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class BindingTypeService {

    private final Logger LOGGER = LoggerFactory.getLogger(BindingTypeService.class);

    BindingTypeBean bindingTypeBean;

    public BindingTypeService() {
        try {
            this.bindingTypeBean = (BindingTypeBean) new InitialContext().lookup("java:module/BindingTypeEJB!com.genestimate.webapp.ejbs.BindingTypeBean");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @GET
    @RolesAllowed({"USER", "ADMIN"})
    public List<BindingType> getAllBindingTypes(){
        BindingType bindingType = new BindingType();
        bindingType.setName("Hard Cover");
        bindingTypeBean.create(bindingType);
        return bindingTypeBean.findAll();
    }
}
