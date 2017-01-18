package com.genestimate.webapp.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Kais on 14.01.2017.
 */
@Entity
public class ComponentProperties {
    private int id;
    private int nbPages;
    private Component component;
    private PrintingType printingType;
    private Properties properties;
    private RawMaterial rawMaterial;
    private List<Machine> process;

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
    @Column(name = "NBPAGES")
    public int getNbPages() {
        return nbPages;
    }

    public void setNbPages(int nbPages) {
        this.nbPages = nbPages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComponentProperties that = (ComponentProperties) o;

        if (id != that.id) return false;
        if (nbPages != that.nbPages) return false;
        if (component != null ? !component.equals(that.component) : that.component != null) return false;
        if (printingType != null ? !printingType.equals(that.printingType) : that.printingType != null) return false;
        if (properties != null ? !properties.equals(that.properties) : that.properties != null) return false;
        if (rawMaterial != null ? !rawMaterial.equals(that.rawMaterial) : that.rawMaterial != null) return false;
        return process != null ? process.equals(that.process) : that.process == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + nbPages;
        result = 31 * result + (component != null ? component.hashCode() : 0);
        result = 31 * result + (printingType != null ? printingType.hashCode() : 0);
        result = 31 * result + (properties != null ? properties.hashCode() : 0);
        result = 31 * result + (rawMaterial != null ? rawMaterial.hashCode() : 0);
        result = 31 * result + (process != null ? process.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "COMPONENT", referencedColumnName = "ID", nullable = false)
    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    @ManyToOne
    @JoinColumn(name = "PRINTINGTYPE", referencedColumnName = "ID")
    public PrintingType getPrintingType() {
        return printingType;
    }

    public void setPrintingType(PrintingType printingType) {
        this.printingType = printingType;
    }

    @ManyToOne
    @JoinColumn(name = "PROPERTIES", referencedColumnName = "ID", nullable = false)
    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @ManyToOne
    @JoinColumn(name = "RAWMATERIAL", referencedColumnName = "ID")
    public RawMaterial getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(RawMaterial rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    @ManyToMany
    @JoinTable(
            name = "PROCESS",
            joinColumns = @JoinColumn(name = "COMPONENTPROPERTIES", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "MACHINE", referencedColumnName = "ID"))
    public List<Machine> getProcess() {
        return process;
    }

    public void setProcess(List<Machine> process) {
        this.process = process;
    }
}
