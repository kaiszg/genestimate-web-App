package com.genestimate.webapp.services;

import com.genestimate.webapp.ejbs.ProductBean;
import com.genestimate.webapp.model.Component;
import com.genestimate.webapp.model.Product;

import javax.annotation.security.RolesAllowed;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kais on 01.02.2017.
 */

@Path("/products")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class ProductService {

    ProductBean productBean;

    public ProductService() {
        try {
            this.productBean = (ProductBean) new InitialContext().lookup("java:module/ProductEJB!com.genestimate.webapp.ejbs.ProductBean");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @POST
    @RolesAllowed({"USER", "ADMIN"})
    public Product createProduct(){
        Product product = new Product();
        product.setName("Book with one cover and one inner paper type");
        Component component = new Component();
        component.setName("Inner paper");
        List<Component> components = new ArrayList<Component>();
        components.add(component);
        product.setComponents(components);
        productBean.create(product);
        return product;
    }
}
