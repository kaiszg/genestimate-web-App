package com.genestimate.webapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Kais on 14.01.2017.
 */
@Entity
@Table(name = "PROPERTIES", schema = "PUBLIC")
public class Properties {
    private int id;
    private int quantity;
    private int nbPages;
    private double coverPrintingPrice;
    private List<Machine> assemblyProcess;
    private List<ComponentProperties> componentsProperties;
    @JsonIgnore
    private Estimate estimate;
    private Client client;
    private Product product;
    private BindingType bindingType;
    private RawMaterial coverRawMaterial;
    private PrintingType coverPrintingType;
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

    @Basic
    @Column(name = "NB_PAGES")
    public int getNbPages() {
        return nbPages;
    }

    public void setNbPages(int nbPages) {
        this.nbPages = nbPages;
    }

    @Basic
    @Column(name = "COVER_PRINTING_PRICE")
    public double getCoverPrintingPrice() {
        return coverPrintingPrice;
    }

    public void setCoverPrintingPrice(double coverPrintingPrice) {
        this.coverPrintingPrice = coverPrintingPrice;
    }

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "ASSEMBLYPROCESS", schema = "PUBLIC", joinColumns = @JoinColumn(name = "PROPERTIES", referencedColumnName = "ID", nullable = false), inverseJoinColumns = @JoinColumn(name = "MACHINE", referencedColumnName = "ID", nullable = false))
    public List<Machine> getAssemblyProcess() {
        return assemblyProcess;
    }

    public void setAssemblyProcess(List<Machine> assemblyProcess) {
        this.assemblyProcess = assemblyProcess;
    }

    @OneToMany(mappedBy = "properties")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<ComponentProperties> getComponentsProperties() {
        return componentsProperties;
    }

    public void setComponentsProperties(List<ComponentProperties> componentsProperties) {
        this.componentsProperties = componentsProperties;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ESTIMATE", referencedColumnName = "ID")
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
    @JoinColumn(name= "BINDINGTYPE", referencedColumnName = "ID")
    public BindingType getBindingType() {
        return bindingType;
    }

    public void setBindingType(BindingType bindingType) {
        this.bindingType = bindingType;
    }

    @ManyToOne
    @JoinColumn(name = "DIMENSIONS", referencedColumnName = "ID")
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

    @ManyToOne
    @JoinColumn(name = "COVERPRINTINGTYPE", referencedColumnName = "ID")
    public PrintingType getCoverPrintingType() {
        return coverPrintingType;
    }

    public void setCoverPrintingType(PrintingType coverPrintingType) {
        this.coverPrintingType = coverPrintingType;
    }
}
