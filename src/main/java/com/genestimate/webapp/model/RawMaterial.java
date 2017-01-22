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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RawMaterial that = (RawMaterial) o;

        if (id != that.id) return false;
        if (price != that.price) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (componentsProperties != null ? !componentsProperties.equals(that.componentsProperties) : that.componentsProperties != null)
            return false;
        if (component != null ? !component.equals(that.component) : that.component != null) return false;
        return properties != null ? properties.equals(that.properties) : that.properties == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + (componentsProperties != null ? componentsProperties.hashCode() : 0);
        result = 31 * result + (component != null ? component.hashCode() : 0);
        result = 31 * result + (properties != null ? properties.hashCode() : 0);
        return result;
    }
}
