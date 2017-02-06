package com.genestimate.webapp.ejbs;

import com.genestimate.webapp.model.ComponentProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Kais on 28.01.2017.
 */
@Stateless(name = "ComponentPropertiesEJB")
public class ComponentPropertiesBean {

    private final Logger LOGGER = LoggerFactory.getLogger(ComponentPropertiesBean.class);

    @PersistenceContext
    private EntityManager em;

    public List<ComponentProperties> findAll(){
        LOGGER.info("retrieving all componentPropertiess");
        List<ComponentProperties> componentPropertiess = em.createQuery("FROM ComponentProperties").getResultList();
        return componentPropertiess;
    }

    public ComponentProperties findByID(int id){
        LOGGER.info("retrieving componentProperties with id " + id);
        ComponentProperties componentProperties = em.find(ComponentProperties.class, id);
        return componentProperties;
    }

    public ComponentProperties create(ComponentProperties componentProperties){
        em.persist(componentProperties);
        em.flush();
        LOGGER.info("componentProperties created with success");
        return componentProperties;
    }

    public ComponentProperties update(ComponentProperties componentProperties){
        em.merge(componentProperties);
        em.flush();
        LOGGER.info("componentProperties updated with success");
        return componentProperties;
    }

    public void delete(int id){
        ComponentProperties componentProperties = this.findByID(id);
        em.remove(componentProperties);
        em.flush();
        LOGGER.info("componentProperties deleted with success");
    }

}
