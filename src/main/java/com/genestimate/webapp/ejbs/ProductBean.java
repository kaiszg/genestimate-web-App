package com.genestimate.webapp.ejbs;

import com.genestimate.webapp.model.Component;
import com.genestimate.webapp.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Kais on 25.01.2017.
 */
@Stateless(name = "ProductEJB")
public class ProductBean {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductBean.class);

    @PersistenceContext
    EntityManager em;

    @EJB
    ComponentBean componentBean;

    public List<Product> findAll(){
        LOGGER.info("retrieving all products");
        return em.createQuery("FROM Product").getResultList();
    }

    public Product findByID(int id){
        LOGGER.info("retrieving product with id " + id);
        return em.find(Product.class, id);
    }

    public Product create(Product product){
        em.persist(product);
        em.flush();
        for (Component component : product.getComponents()){
            componentBean.create(component);
        }
        LOGGER.info("product created with success");
        return product;
    }

    public Product update(Product product){
        em.merge(product);
        em.flush();
        LOGGER.info("product updated with success");
        return product;
    }

    public void delete(int id){
        Product product = this.findByID(id);
        em.remove(product);
        LOGGER.info("product deleted with success");
        em.flush();
    }
}
