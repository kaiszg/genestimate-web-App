package com.genestimate.webapp.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Kais on 15.01.2017.
 */

@Entity
@DiscriminatorValue("PRINTING")
public class PrintingRawMaterialDimensions extends Dimensions{

    private int nbPagesFromStandard;
    private List<FinalProductDimensions> finalDimensions;
    private StandardRawMaterialDimensions standardDimensions;

    @Basic
    @Column(name = "NBPAGESFROMSTANDARD")
    public int getNbPagesFromStandard() {
        return nbPagesFromStandard;
    }

    public void setNbPagesFromStandard(int nbPagesFromStandard) {
        this.nbPagesFromStandard = nbPagesFromStandard;
    }

    @OneToMany(mappedBy = "printingDimensions")
    public List<FinalProductDimensions> getFinalDimensions() {
        return finalDimensions;
    }

    public void setFinalDimensions(List<FinalProductDimensions> finalDimensions) {
        this.finalDimensions = finalDimensions;
    }

    @ManyToOne
    @JoinColumn(name = "STANDARDDIMENSIONS", referencedColumnName = "ID")
    public StandardRawMaterialDimensions getStandardDimensions() {
        return standardDimensions;
    }

    public void setStandardDimensions(StandardRawMaterialDimensions standardDimensions) {
        this.standardDimensions = standardDimensions;
    }
}
