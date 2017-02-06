package com.genestimate.webapp.ejbs;

import com.genestimate.webapp.model.PrintingType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Kais on 28.01.2017.
 */
@Stateless(name = "PrintingTypeEJB")
public class PrintingTypeBean {

    private final Logger LOGGER = LoggerFactory.getLogger(PrintingTypeBean.class);

    @PersistenceContext
    EntityManager em;

    public List<PrintingType> findAll(){
        LOGGER.info("retrieving all printingTypes");
        List<PrintingType> types = em.createQuery("FROM PrintingType").getResultList();
        for (PrintingType type : types){
            type.getComponentsProperties().size(); /* since fetching the collection is lazily fetched */
        }
        return types;
    }

    public PrintingType findByID(int id){
        LOGGER.info("retrieving printingType with id " + id);
        return em.find(PrintingType.class, id);
    }

    public PrintingType create(PrintingType printingType){
        em.persist(printingType);
        em.flush();
        LOGGER.info("printingType created with success");
        return printingType;
    }

    public PrintingType update(PrintingType printingType){
        em.merge(printingType);
        em.flush();
        LOGGER.info("printingType updated with success");
        return printingType;
    }

    public void delete(int id){
        PrintingType printingType = this.findByID(id);
        em.remove(printingType);
        LOGGER.info("printingType deleted with success");
        em.flush();
    }
    
}
