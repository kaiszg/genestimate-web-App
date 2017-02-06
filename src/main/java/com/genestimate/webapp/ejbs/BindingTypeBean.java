package com.genestimate.webapp.ejbs;

import com.genestimate.webapp.model.BindingType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Kais on 27.01.2017.
 */
@Stateless(name = "BindingTypeEJB")
public class BindingTypeBean {

    private final Logger LOGGER = LoggerFactory.getLogger(BindingTypeBean.class);

    @PersistenceContext
    private EntityManager em;

    public List<BindingType> findAll(){
        LOGGER.info("getting all binding types");
        List<BindingType> types = em.createQuery("FROM BindingType").getResultList();
        return types;
    }

    public BindingType findByID(int id){
        LOGGER.info("getting BindingType with id " + id);
        return em.find(BindingType.class, id);
    }

    public BindingType create(BindingType bindingType){
        em.persist(bindingType);
        em.flush();
        LOGGER.info("BindingType created");
        return bindingType;
    }

    public BindingType update(BindingType bindingType){
        em.merge(bindingType);
        em.flush();
        LOGGER.info("BindingType updated");
        return bindingType;
    }

    public void delete(int id){
        BindingType bindingType = this.findByID(id);
        em.remove(bindingType);
        em.flush();
        LOGGER.info("BindingType deleted");
    }
}
