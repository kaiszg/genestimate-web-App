package com.genestimate.webapp.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Kais on 14.01.2017.
 */
@Entity
public class Machine {
    private int id;
    private String name;
    private Double speed;
    private Double hourlyCost;
    private Double preparationTime;
    private Double startupTime;
    private int length;
    private int width;
    private Integer nbColors;
    private List<Properties> properties;
    private List<ComponentProperties> componentsProperties;

    @Id
    @GeneratedValue
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "SPEED")
    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    @Basic
    @Column(name = "HOURLYCOST")
    public Double getHourlyCost() {
        return hourlyCost;
    }

    public void setHourlyCost(Double hourlyCost) {
        this.hourlyCost = hourlyCost;
    }

    @Basic
    @Column(name = "PREPARATIONTIME")
    public Double getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(Double preparationTime) {
        this.preparationTime = preparationTime;
    }

    @Basic
    @Column(name = "STARTUPTIME")
    public Double getStartupTime() {
        return startupTime;
    }

    public void setStartupTime(Double startupTime) {
        this.startupTime = startupTime;
    }

    @Basic
    @Column(name = "LENGTH")
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Basic
    @Column(name = "WIDTH")
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Basic
    @Column(name = "NBCOLORS")
    public Integer getNbColors() {
        return nbColors;
    }

    public void setNbColors(Integer nbColors) {
        this.nbColors = nbColors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Machine machine = (Machine) o;

        if (id != machine.id) return false;
        if (length != machine.length) return false;
        if (width != machine.width) return false;
        if (name != null ? !name.equals(machine.name) : machine.name != null) return false;
        if (speed != null ? !speed.equals(machine.speed) : machine.speed != null) return false;
        if (hourlyCost != null ? !hourlyCost.equals(machine.hourlyCost) : machine.hourlyCost != null) return false;
        if (preparationTime != null ? !preparationTime.equals(machine.preparationTime) : machine.preparationTime != null)
            return false;
        if (startupTime != null ? !startupTime.equals(machine.startupTime) : machine.startupTime != null) return false;
        if (nbColors != null ? !nbColors.equals(machine.nbColors) : machine.nbColors != null) return false;
        if (properties != null ? !properties.equals(machine.properties) : machine.properties != null) return false;
        return componentsProperties != null ? componentsProperties.equals(machine.componentsProperties) : machine.componentsProperties == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (speed != null ? speed.hashCode() : 0);
        result = 31 * result + (hourlyCost != null ? hourlyCost.hashCode() : 0);
        result = 31 * result + (preparationTime != null ? preparationTime.hashCode() : 0);
        result = 31 * result + (startupTime != null ? startupTime.hashCode() : 0);
        result = 31 * result + length;
        result = 31 * result + width;
        result = 31 * result + (nbColors != null ? nbColors.hashCode() : 0);
        result = 31 * result + (properties != null ? properties.hashCode() : 0);
        result = 31 * result + (componentsProperties != null ? componentsProperties.hashCode() : 0);
        return result;
    }

    @ManyToMany(mappedBy = "assemblyProcess")
    public List<Properties> getProperties() {
        return properties;
    }

    public void setProperties(List<Properties> properties) {
        this.properties = properties;
    }

    @ManyToMany(mappedBy = "process")
    public List<ComponentProperties> getComponentsProperties() {
        return componentsProperties;
    }

    public void setComponentsProperties(List<ComponentProperties> componentsProperties) {
        this.componentsProperties = componentsProperties;
    }
}
