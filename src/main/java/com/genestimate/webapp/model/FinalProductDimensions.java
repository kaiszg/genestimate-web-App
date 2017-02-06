package com.genestimate.webapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    //private List<Properties> propertiesList;


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
/*

    @OneToMany(mappedBy = "finalDimensions", fetch = FetchType.EAGER)
    public List<Properties> getPropertiesList() {
        return propertiesList;
    }

    public void setPropertiesList(List<Properties> propertiesList) {
        this.propertiesList = propertiesList;
    }
*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        FinalProductDimensions that = (FinalProductDimensions) o;

        if (nbPagesPerPaper != that.nbPagesPerPaper) return false;
        if (nbCoverCopiesPerPaper != that.nbCoverCopiesPerPaper) return false;
        return printingDimensions != null ? printingDimensions.equals(that.printingDimensions) : that.printingDimensions == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (printingDimensions != null ? printingDimensions.hashCode() : 0);
        result = 31 * result + nbPagesPerPaper;
        result = 31 * result + nbCoverCopiesPerPaper;
        return result;
    }
}
