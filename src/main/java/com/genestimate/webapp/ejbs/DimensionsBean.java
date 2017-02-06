package com.genestimate.webapp.ejbs;

import com.genestimate.webapp.model.Dimensions;
import com.genestimate.webapp.model.FinalProductDimensions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Kais on 28.01.2017.
 */
@Stateless(name = "DimensionsEJB")
public class DimensionsBean {

    private final Logger LOGGER = LoggerFactory.getLogger(DimensionsBean.class);

    @PersistenceContext
    EntityManager em;

    public List<? extends Dimensions> findAll(){
        LOGGER.info("retrieving all dimensionss");
        return em.createQuery("FROM Dimensions").getResultList();
    }

    public List<FinalProductDimensions> findAllFinalDimensions(){
        LOGGER.info("retrieving all final product dimensions");
        List<FinalProductDimensions> result = em.createQuery("SELECT d FROM Dimensions d WHERE TYPE(d) IN :classes")
                .setParameter("classes", Arrays.asList(FinalProductDimensions.class))
                .getResultList();
//        for (FinalProductDimensions dimensions : result){
//            dimensions.getPrintingDimensions().getFinalDimensions().size();
//            dimensions.getPrintingDimensions().getStandardDimensions().getPrintingDimensionss().size();
//        }
        return result;
    }

    public Dimensions findByID(int id){
        LOGGER.info("retrieving dimensions with id " + id);
        return em.find(Dimensions.class, id);
    }

    public Dimensions create(Dimensions dimensions){
        em.persist(dimensions);
        em.flush();
        LOGGER.info("dimensions created with success");
        return dimensions;
    }

    public Dimensions update(Dimensions dimensions){
        em.merge(dimensions);
        em.flush();
        LOGGER.info("dimensions updated with success");
        return dimensions;
    }

    public void delete(int id){
        Dimensions dimensions = this.findByID(id);
        em.remove(dimensions);
        LOGGER.info("dimensions deleted with success");
        em.flush();
    }
    
}
