package com.genestimate.webapp.model;

import javax.persistence.*;

/**
 * Created by Kais on 14.01.2017.
 */
@Entity
public class Estimate {
    private int id;
    private Float price;
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
    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Estimate estimate = (Estimate) o;

        if (id != estimate.id) return false;
        if (price != null ? !price.equals(estimate.price) : estimate.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "PROPERTIES", referencedColumnName = "ID")
    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
