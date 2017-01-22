package com.genestimate.webapp.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Kais on 15.01.2017.
 */

@Entity
public class CoverType {

    private int id;
    private String name;
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

    @OneToMany(mappedBy = "coverType")
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

        CoverType coverType = (CoverType) o;

        if (id != coverType.id) return false;
        if (name != null ? !name.equals(coverType.name) : coverType.name != null) return false;
        return properties != null ? properties.equals(coverType.properties) : coverType.properties == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (properties != null ? properties.hashCode() : 0);
        return result;
    }
}
