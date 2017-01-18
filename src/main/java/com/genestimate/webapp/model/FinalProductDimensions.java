package com.genestimate.webapp.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Kais on 15.01.2017.
 */

@Entity
@DiscriminatorValue("FINAL")
public class FinalProductDimensions extends Dimensions{

    private PrintingRawMaterialDimensions printingDimensions;
    private int nbPagesPerPaper;
    private int nbCoverCopiesPerPaper;
    private List<Properties> propertiesList;


    @Basic
    @Column(name = "NBPAGESPERPAPER")
    public int getNbPagesPerPaper() {
        return nbPagesPerPaper;
    }

    public void setNbPagesPerPaper(int nbPagesPerPaper) {
        this.nbPagesPerPaper = nbPagesPerPaper;
    }

    @Basic
    @Column(name = "NBCOVERCOPIESPERPAPER")
    public int getNbCoverCopiesPerPaper() {
        return nbCoverCopiesPerPaper;
    }

    public void setNbCoverCopiesPerPaper(int nbCoverCopiesPerPaper) {
        this.nbCoverCopiesPerPaper = nbCoverCopiesPerPaper;
    }

    @ManyToOne
    @JoinColumn(name = "PRINTINGDIMENSIONS", referencedColumnName = "ID")
    public PrintingRawMaterialDimensions getPrintingDimensions() {
        return printingDimensions;
    }

    public void setPrintingDimensions(PrintingRawMaterialDimensions printingDimensions) {
        this.printingDimensions = printingDimensions;
    }

    @OneToMany(mappedBy = "finalDimensions")
    public List<Properties> getPropertiesList() {
        return propertiesList;
    }

    public void setPropertiesList(List<Properties> propertiesList) {
        this.propertiesList = propertiesList;
    }
}
