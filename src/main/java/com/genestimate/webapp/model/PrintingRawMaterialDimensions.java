package com.genestimate.webapp.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Kais on 15.01.2017.
 */

@Entity
@DiscriminatorValue("PRINTING")
public class PrintingRawMaterialDimensions extends Dimensions{

    private int nbPagesFromStandard;

//    private List<FinalProductDimensions> finalDimensions;

    private StandardRawMaterialDimensions standardDimensions;

    @Basic
    @Column(name = "NBPAGESFROMSTANDARD")
    public int getNbPagesFromStandard() {
        return nbPagesFromStandard;
    }

    public void setNbPagesFromStandard(int nbPagesFromStandard) {
        this.nbPagesFromStandard = nbPagesFromStandard;
    }

//    @OneToMany(mappedBy = "printingDimensions")
//    public List<FinalProductDimensions> getFinalDimensions() {
//        return finalDimensions;
//    }
//
//    public void setFinalDimensions(List<FinalProductDimensions> finalDimensions) {
//        this.finalDimensions = finalDimensions;
//    }

    @ManyToOne
    @JoinColumn(name = "STANDARDDIMENSIONS", referencedColumnName = "ID")
    public StandardRawMaterialDimensions getStandardDimensions() {
        return standardDimensions;
    }

    public void setStandardDimensions(StandardRawMaterialDimensions standardDimensions) {
        this.standardDimensions = standardDimensions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PrintingRawMaterialDimensions that = (PrintingRawMaterialDimensions) o;

        if (nbPagesFromStandard != that.nbPagesFromStandard) return false;
        return standardDimensions != null ? standardDimensions.equals(that.standardDimensions) : that.standardDimensions == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + nbPagesFromStandard;
        result = 31 * result + (standardDimensions != null ? standardDimensions.hashCode() : 0);
        return result;
    }
}
