package com.genestimate.webapp.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Kais on 14.01.2017.
 */
@Entity
public class Properties {
    private int id;
    private int quantity;
    private List<Machine> assemblyProcess;
    private List<ComponentProperties> componentsProperties;
    private Estimate estimate;
    private Client client;
    private Product product;
    private CoverType coverType;
    private RawMaterial coverRawMaterial;
    private FinalProductDimensions finalDimensions;

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
    @Column(name = "QUANTITY")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Properties that = (Properties) o;

        if (id != that.id) return false;
        if (quantity != that.quantity) return false;
        if (assemblyProcess != null ? !assemblyProcess.equals(that.assemblyProcess) : that.assemblyProcess != null)
            return false;
        if (componentsProperties != null ? !componentsProperties.equals(that.componentsProperties) : that.componentsProperties != null)
            return false;
        if (estimate != null ? !estimate.equals(that.estimate) : that.estimate != null) return false;
        if (client != null ? !client.equals(that.client) : that.client != null) return false;
        if (product != null ? !product.equals(that.product) : that.product != null) return false;
        if (coverType != null ? !coverType.equals(that.coverType) : that.coverType != null) return false;
        if (coverRawMaterial != null ? !coverRawMaterial.equals(that.coverRawMaterial) : that.coverRawMaterial != null)
            return false;
        return finalDimensions != null ? finalDimensions.equals(that.finalDimensions) : that.finalDimensions == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + quantity;
        result = 31 * result + (assemblyProcess != null ? assemblyProcess.hashCode() : 0);
        result = 31 * result + (componentsProperties != null ? componentsProperties.hashCode() : 0);
        result = 31 * result + (estimate != null ? estimate.hashCode() : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + (coverType != null ? coverType.hashCode() : 0);
        result = 31 * result + (coverRawMaterial != null ? coverRawMaterial.hashCode() : 0);
        result = 31 * result + (finalDimensions != null ? finalDimensions.hashCode() : 0);
        return result;
    }

    @ManyToMany
    @JoinTable(name = "ASSEMBLYPROCESS", catalog = "GENESTIMATE", schema = "PUBLIC", joinColumns = @JoinColumn(name = "PROPERTIES", referencedColumnName = "ID", nullable = false), inverseJoinColumns = @JoinColumn(name = "MACHINE", referencedColumnName = "ID", nullable = false))
    public List<Machine> getAssemblyProcess() {
        return assemblyProcess;
    }

    public void setAssemblyProcess(List<Machine> assemblyProcess) {
        this.assemblyProcess = assemblyProcess;
    }

    @OneToMany(mappedBy = "properties")
    public List<ComponentProperties> getComponentsProperties() {
        return componentsProperties;
    }

    public void setComponentsProperties(List<ComponentProperties> componentsProperties) {
        this.componentsProperties = componentsProperties;
    }

    @OneToOne(mappedBy = "properties")
    public Estimate getEstimate() {
        return estimate;
    }

    public void setEstimate(Estimate estimate) {
        this.estimate = estimate;
    }

    @ManyToOne
    @JoinColumn(name = "CLIENT", referencedColumnName = "ID")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @ManyToOne
    @JoinColumn(name = "PRODUCT", referencedColumnName = "ID")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @ManyToOne
    @JoinColumn(name= "COVERTYPE", referencedColumnName = "ID")
    public CoverType getCoverType() {
        return coverType;
    }

    public void setCoverType(CoverType coverType) {
        this.coverType = coverType;
    }

    @ManyToOne
    @JoinColumn(name = "DIMENSTIONS", referencedColumnName = "ID")
    public FinalProductDimensions getFinalDimensions() {
        return finalDimensions;
    }

    public void setFinalDimensions(FinalProductDimensions finalDimensions) {
        this.finalDimensions = finalDimensions;
    }

    @ManyToOne
    @JoinColumn(name = "COVERROWMATERIAL", referencedColumnName = "ID")
    public RawMaterial getCoverRawMaterial() {
        return coverRawMaterial;
    }

    public void setCoverRawMaterial(RawMaterial coverRawMaterial) {
        this.coverRawMaterial = coverRawMaterial;
    }
}
