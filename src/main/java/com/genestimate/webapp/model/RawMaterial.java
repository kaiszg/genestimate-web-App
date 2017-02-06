package com.genestimate.webapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Kais on 14.01.2017.
 */
@Entity
@Table(name = "RAW_MATERIAL", schema = "PUBLIC")
public class RawMaterial {
    private int id;
    private String name;
    private double price;

    @JsonIgnore
    private List<ComponentProperties> componentsProperties;
    //private List<Properties> properties;

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
    @Column(name = "PRICE")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @OneToMany(mappedBy = "rawMaterial")
    public List<ComponentProperties> getComponentsProperties() {
        return componentsProperties;
    }

    public void setComponentsProperties(List<ComponentProperties> componentsProperties) {
        this.componentsProperties = componentsProperties;
    }
/*

    @OneToMany(mappedBy = "coverRawMaterial")
    public List<Properties> getProperties() {
        return properties;
    }

    public void setProperties(List<Properties> properties) {
        this.properties = properties;
    }
*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RawMaterial material = (RawMaterial) o;

        if (id != material.id) return false;
        if (Double.compare(material.price, price) != 0) return false;
        if (name != null ? !name.equals(material.name) : material.name != null) return false;
        return componentsProperties != null ? componentsProperties.equals(material.componentsProperties) : material.componentsProperties == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (componentsProperties != null ? componentsProperties.hashCode() : 0);
        return result;
    }
}
