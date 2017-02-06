package com.genestimate.webapp.ejbs;

import com.genestimate.webapp.model.Machine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Kais on 28.01.2017.
 */
@Stateless(name = "MachineEJB")
public class MachineBean {

    private final Logger LOGGER = LoggerFactory.getLogger(MachineBean.class);

    @PersistenceContext
    EntityManager em;

    public List<Machine> findAll(){
        LOGGER.info("retrieving all machines");
        return em.createQuery("FROM Machine").getResultList();
    }

    public Machine findByID(int id){
        LOGGER.info("retrieving machine with id " + id);
        return em.find(Machine.class, id);
    }

    public Machine create(Machine machine){
        em.persist(machine);
        em.flush();
        LOGGER.info("machine created with success");
        return machine;
    }

    public Machine update(Machine machine){
        em.merge(machine);
        em.flush();
        LOGGER.info("machine updated with success");
        return machine;
    }

    public void delete(int id){
        Machine machine = this.findByID(id);
        em.remove(machine);
        LOGGER.info("machine deleted with success");
        em.flush();
    }
    
}
