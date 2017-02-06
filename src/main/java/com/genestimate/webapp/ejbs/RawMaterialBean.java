package com.genestimate.webapp.ejbs;

import com.genestimate.webapp.model.RawMaterial;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Kais on 28.01.2017.
 */
@Stateless(name = "RawMaterialEJB")
public class RawMaterialBean {

    private final Logger LOGGER = LoggerFactory.getLogger(RawMaterialBean.class);

    @PersistenceContext
    EntityManager em;

    public List<RawMaterial> findAll(){
        LOGGER.info("retrieving all rawMaterials");
        List<RawMaterial> result = em.createQuery("FROM RawMaterial").getResultList();
        // explicitly loading collections to avoid LAZY Loading exception
        for (RawMaterial material : result){
            material.getComponentsProperties().size();
            //material.getProperties().size();
        }
        return result;
    }

    public RawMaterial findByID(int id){
        LOGGER.info("retrieving rawMaterial with id " + id);
        RawMaterial material = em.find(RawMaterial.class, id);
        // explicitly loading collections to avoid LAZY Loading exception
        material.getComponentsProperties().size();
        //material.getProperties().size();

        return material;
    }

    public RawMaterial create(RawMaterial rawMaterial){
        em.persist(rawMaterial);
        em.flush();
        LOGGER.info("rawMaterial created with success");
        return rawMaterial;
    }

    public RawMaterial update(RawMaterial rawMaterial){
        em.merge(rawMaterial);
        em.flush();
        LOGGER.info("rawMaterial updated with success");
        return rawMaterial;
    }

    public void delete(int id){
        RawMaterial rawMaterial = this.findByID(id);
        em.remove(rawMaterial);
        LOGGER.info("rawMaterial deleted with success");
        em.flush();
    }
    
}
