package com.genestimate.webapp.control;

import com.genestimate.webapp.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by Kais on 22.01.2017.
 */
@Path("/products/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Stateless
public class ProductService {

    private Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @GET
    public void getAllProducts(){
        LOGGER.info("getAllProducts method called");
    }

}
