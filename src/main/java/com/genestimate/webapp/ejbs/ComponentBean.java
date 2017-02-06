package com.genestimate.webapp.ejbs;

import com.genestimate.webapp.model.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Kais on 28.01.2017.
 */
@Stateless(name = "ComponentEJB")
public class ComponentBean {

    private final Logger LOGGER = LoggerFactory.getLogger(ComponentBean.class);

    @PersistenceContext
    private EntityManager em;

    public List<Component> findAll(){
        LOGGER.info("retrieving all components");
        List<Component> components = em.createQuery("FROM Component").getResultList();
        return components;
    }

    public Component findByID(int id){
        LOGGER.info("retrieving component with id " + id);
        Component component = em.find(Component.class, id);
        return component;
    }

    public Component create(Component component){
        em.persist(component);
        em.flush();
        LOGGER.info("component created with success");
        return component;
    }

    public Component update(Component component){
        em.merge(component);
        em.flush();
        LOGGER.info("component updated with success");
        return component;
    }

    public void delete(int id){
        Component component = this.findByID(id);
        em.remove(component);
        em.flush();
        LOGGER.info("component deleted with success");
    }

}
