package com.genestimate.webapp.ejbs;

import com.genestimate.webapp.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kais on 28.01.2017.
 */
@Stateless(name = "EstimateEJB")
public class EstimateBean {

    private final Logger LOGGER = LoggerFactory.getLogger(EstimateBean.class);

    @PersistenceContext
    EntityManager em;

    @EJB
    MachineBean machineBean;
    @EJB
    PropertiesBean propertiesBean;
    @EJB
    ClientBean clientBean;
    @EJB
    ComponentPropertiesBean componentPropertiesBean;

    public List<Estimate> findAll(){
        LOGGER.info("retrieving all estimates");
        List<Estimate> estimates = em.createQuery("FROM Estimate").getResultList();
        for (Estimate estimate : estimates){
            estimate.getProperties().getAssemblyProcess().size();
            estimate.getProperties().getComponentsProperties().size();
        }
        return em.createQuery("FROM Estimate").getResultList();
    }

    public Estimate findByID(int id){
        LOGGER.info("retrieving estimate with id " + id);
        Estimate estimate = em.find(Estimate.class, id);
        estimate.getProperties().getComponentsProperties().size();
        estimate.getProperties().getAssemblyProcess().size();
        return estimate;
    }

    public Estimate create(Estimate estimate){
        em.persist(estimate);
        em.flush();
        LOGGER.info("estimate created with success");
        return estimate;
    }

    public Estimate createFromProperties(Properties properties){

        // ****** Interior paper ************
        double nbSessions = properties.getNbPages() / properties.getFinalDimensions().getNbPagesPerPaper();
        double nbStdrPapers = nbSessions / properties.getFinalDimensions().getPrintingDimensions().getNbPagesFromStandard();
        /*
        This needs to be modified later.
        This will only give right results if the product has ONLY one component
         */
        double innerPaperPrice = 0;
        for (ComponentProperties componentProperties : properties.getComponentsProperties()){
            innerPaperPrice = innerPaperPrice + componentProperties.getRawMaterial().getPrice() * nbStdrPapers;
        }
        innerPaperPrice = innerPaperPrice * properties.getQuantity();
        // Gewinnspanne 20%
        innerPaperPrice = innerPaperPrice * 120 / 100;

        // ********** COVER ***************
        double nbCoverStdrPapers = properties.getQuantity() / properties.getFinalDimensions().getNbCoverCopiesPerPaper() / properties.getFinalDimensions().getPrintingDimensions().getNbPagesFromStandard();
        double coverPaperPrice = nbCoverStdrPapers * properties.getCoverRawMaterial().getPrice();
        coverPaperPrice = coverPaperPrice * 120 / 100;

        // **************************** Final raw material price *************************************
        double rawMaterialPrice = coverPaperPrice + innerPaperPrice;
        // */*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/


        // ***** process price ********
        double processPrice = 0;
        for (ComponentProperties componentProperties : properties.getComponentsProperties()){
            componentProperties.setProcess(getProcessForInnerPaper()); // Just for the demo
            double componentPrice = 0;
            for(Machine machine : componentProperties.getProcess()){
                componentPrice = componentPrice + machine.getHourlyCost() * (nbSessions / machine.getSpeed() + machine.getPreparationTime() + machine.getStartupTime());
            }
            componentProperties.setPrice(innerPaperPrice + (componentPrice * 120 / 100));
            processPrice = processPrice +  componentPrice;
        }

        // **** assembly process price ****
        properties.setAssemblyProcess(getAssemblyProcess()); // Just for the demo
        double assemblyProcessPrice = 0;
        for (Machine machine : properties.getAssemblyProcess()){
            assemblyProcessPrice = assemblyProcessPrice + machine.getHourlyCost() * ( properties.getQuantity() / machine.getSpeed()  + machine.getPreparationTime() + machine.getStartupTime());
        }
        assemblyProcessPrice = assemblyProcessPrice * 120 / 100;
        properties.setCoverPrintingPrice(assemblyProcessPrice + coverPaperPrice);

        /******************************** FINAL PRICE ********************************/
        double finalPrice = 0;
        for (ComponentProperties componentProperties : properties.getComponentsProperties()){
            finalPrice = finalPrice + componentProperties.getPrice();
        }
        finalPrice = finalPrice + properties.getCoverPrintingPrice();
        ///*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/**/

        Estimate estimate = new Estimate();
        estimate.setPrice(finalPrice);
        estimate.setProperties(properties);
        properties.setEstimate(estimate);
        for (ComponentProperties componentProperties : estimate.getProperties().getComponentsProperties()){
            componentProperties.setProperties(estimate.getProperties());
            componentPropertiesBean.create(componentProperties);
        }

        return create(estimate);
    }

    private List<Machine> getAssemblyProcess() {
        List<Machine> machines = new ArrayList<Machine>();
        Machine machine = new Machine();
        machine.setName("PONY");
        machine.setHourlyCost(1000.0);
        machine.setPreparationTime(0.5);
        machine.setSpeed(700.0);
        machine.setStartupTime(0.5);

        machineBean.create(machine);
        machines.add(machine);

        return machines;
    }

    private List<Machine> getProcessForInnerPaper(){
        List<Machine> machines = new ArrayList<Machine>();
        Machine machine = new Machine();
        machine.setName("SM102");
        machine.setHourlyCost(1000.0);
        machine.setNbColors(2);
        machine.setPreparationTime(0.5);
        machine.setSpeed(1000.0);
        machine.setStartupTime(0.5);

        machineBean.create(machine);
        machines.add(machine);

        machine = new Machine();
        machine.setName("Folding Machine");
        machine.setHourlyCost(1500.0);
        machine.setPreparationTime(0.0);
        machine.setSpeed(1000.0);
        machine.setStartupTime(0.25);

        machineBean.create(machine);
        machines.add(machine);

        machine = new Machine();
        machine.setName("Massico");
        machine.setHourlyCost(700.0);
        machine.setPreparationTime(0.0);
        machine.setSpeed(1000.0);
        machine.setStartupTime(0.25);

        machineBean.create(machine);
        machines.add(machine);

        return machines;
    }

    public List<Estimate> getEstimatesOfClient(Client client){
        LOGGER.info("retrieving estimates of client " + client.getName());
        List<Properties> orders = client.getOrders();
        List<Estimate> results = new ArrayList<Estimate>();
        for (Properties order : orders){
            results.add(order.getEstimate());
            order.getComponentsProperties().size();
        }
        return results;
    }

    public Estimate update(Estimate estimate){
        em.merge(estimate);
        em.flush();
        LOGGER.info("estimate updated with success");
        return estimate;
    }

    public void delete(int id){
        Estimate estimate = this.findByID(id);
        em.remove(estimate);
        LOGGER.info("estimate deleted with success");
        em.flush();
    }


}
