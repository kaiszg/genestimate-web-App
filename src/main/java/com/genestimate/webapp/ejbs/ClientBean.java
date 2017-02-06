package com.genestimate.webapp.ejbs;

import com.genestimate.webapp.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Kais on 28.01.2017.
 */
@Stateless(name = "ClientEJB")
public class ClientBean {

    private final Logger LOGGER = LoggerFactory.getLogger(ClientBean.class);

    @PersistenceContext
    private EntityManager em;

    public List<Client> findAll(){
        LOGGER.info("retrieving all clients");
        List<Client> clients = em.createQuery("FROM Client").getResultList();
        return clients;
    }

    public Client findByID(int id){
        LOGGER.info("retrieving client with id " + id);
        Client client = em.find(Client.class, id);
        return client;
    }

    public Client create(Client client){
        em.persist(client);
        em.flush();
        LOGGER.info("client created with success");
        return client;
    }

    public Client update(Client client){
        em.merge(client);
        em.flush();
        LOGGER.info("client updated with success");
        return client;
    }

    public void delete(int id){
        Client client = this.findByID(id);
        em.remove(client);
        em.flush();
        LOGGER.info("client deleted with success");
    }

}
