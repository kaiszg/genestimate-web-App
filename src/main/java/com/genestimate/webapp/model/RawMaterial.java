package com.genestimate.webapp.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Kais on 14.01.2017.
 */
@Entity
public class RawMaterial {
    private int id;
    private String name;
    private int price;
    private List<ComponentProperties> componentsProperties;
    private Component component;
    private List<Properties> properties;

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
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @OneToMany(mappedBy = "rawMaterial")
    public List<ComponentProperties> getComponentsProperties() {
        return componentsProperties;
    }

    public void setComponentsProperties(List<ComponentProperties> componentsProperties) {
        this.componentsProperties = componentsProperties;
    }

    @ManyToOne
    @JoinColumn(name = "COMPONENT", referencedColumnName = "ID")
    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    @OneToMany(mappedBy = "coverRawMaterial")
    public List<Properties> getProperties() {
        return properties;
    }

    public void setProperties(List<Properties> properties) {
        this.properties = properties;
    }
}
