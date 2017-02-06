package com.genestimate.webapp.model;

import javax.persistence.*;

/**
 * Created by Kais on 14.01.2017.
 */
@Entity
@Table(name = "ESTIMATE", schema = "PUBLIC")
public class Estimate {
    private int id;
    private double price;
    private Properties properties;

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
    @Column(name = "PRICE")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @OneToOne
    @JoinColumn(name = "PROPERTIES", referencedColumnName = "ID")
    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Estimate estimate = (Estimate) o;

        if (id != estimate.id) return false;
        if (Double.compare(estimate.price, price) != 0) return false;
        return properties != null ? properties.equals(estimate.properties) : estimate.properties == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (properties != null ? properties.hashCode() : 0);
        return result;
    }
}
