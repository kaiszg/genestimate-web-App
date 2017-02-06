package com.genestimate.webapp.ejbs;

import com.genestimate.webapp.model.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Kais on 28.01.2017.
 */
@Stateless(name = "PropertiesEJB")
public class PropertiesBean {

    private final Logger LOGGER = LoggerFactory.getLogger(PropertiesBean.class);

    @PersistenceContext
    EntityManager em;

    public List<Properties> findAll(){
        LOGGER.info("retrieving all propertiess");
        return em.createQuery("FROM Properties").getResultList();
    }

    public Properties findByID(int id){
        LOGGER.info("retrieving properties with id " + id);
        return em.find(Properties.class, id);
    }

    public Properties create(Properties properties){
        em.persist(properties);
        em.flush();
        LOGGER.info("properties created with success");
        return properties;
    }

    public Properties update(Properties properties){
        em.merge(properties);
        em.flush();
        LOGGER.info("properties updated with success");
        return properties;
    }

    public void delete(int id){
        Properties properties = this.findByID(id);
        em.remove(properties);
        LOGGER.info("properties deleted with success");
        em.flush();
    }
    
}
