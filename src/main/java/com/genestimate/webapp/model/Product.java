package com.genestimate.webapp.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Kais on 14.01.2017.
 */
@Entity
public class Product {
    private int id;
    private String name;
    private List<Component> components;
    private List<Properties> orders;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (components != null ? !components.equals(product.components) : product.components != null) return false;
        return orders != null ? orders.equals(product.orders) : product.orders == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (components != null ? components.hashCode() : 0);
        result = 31 * result + (orders != null ? orders.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "product")
    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    @OneToMany(mappedBy = "product")
    public List<Properties> getOrders() {
        return orders;
    }

    public void setOrders(List<Properties> orders) {
        this.orders = orders;
    }
}
