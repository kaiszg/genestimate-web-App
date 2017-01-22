package com.genestimate.webapp.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Kais on 14.01.2017.
 */
@Entity
public class PrintingType {
    private int id;
    private String name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrintingType that = (PrintingType) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return componentsProperties != null ? componentsProperties.equals(that.componentsProperties) : that.componentsProperties == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (componentsProperties != null ? componentsProperties.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "printingType")
    public List<ComponentProperties> getComponentsProperties() {
        return componentsProperties;
    }

    public void setComponentsProperties(List<ComponentProperties> componentsProperties) {
        this.componentsProperties = componentsProperties;
    }
}
