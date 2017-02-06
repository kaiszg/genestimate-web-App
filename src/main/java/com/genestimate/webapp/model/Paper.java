package com.genestimate.webapp.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Kais on 14.01.2017.
 */
//@Entity
public class Paper implements Serializable{

    /*private FinalProductDimensions finalDimensions;
    private Integer nbPages;
    private PrintingRawMaterialDimensions printingDimensions;

    @Id
    @ManyToOne
    @JoinColumn(name = "FINALDIMENSIONS", referencedColumnName = "ID", nullable = false)
    public FinalProductDimensions getFinalDimensions() {
        return finalDimensions;
    }

    public void setFinalDimensions(FinalProductDimensions finalDimensions) {
        this.finalDimensions = finalDimensions;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "PRINTINGDIMENSIONS", referencedColumnName = "ID", nullable = false)
    public PrintingRawMaterialDimensions getPrintingDimensions() {
        return printingDimensions;
    }

    public void setPrintingDimensions(PrintingRawMaterialDimensions printingDimensions) {
        this.printingDimensions = printingDimensions;
    }

    @Basic
    @Column(name = "NBPAGES")
    public Integer getNbPages() {
        return nbPages;
    }

    public void setNbPages(Integer nbpages) {
        this.nbPages = nbpages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Paper paper = (Paper) o;

        if (finalDimensions != null ? !finalDimensions.equals(paper.finalDimensions) : paper.finalDimensions != null)
            return false;
        if (nbPages != null ? !nbPages.equals(paper.nbPages) : paper.nbPages != null) return false;
        return printingDimensions != null ? printingDimensions.equals(paper.printingDimensions) : paper.printingDimensions == null;
    }

    @Override
    public int hashCode() {
        int result = finalDimensions != null ? finalDimensions.hashCode() : 0;
        result = 31 * result + (nbPages != null ? nbPages.hashCode() : 0);
        result = 31 * result + (printingDimensions != null ? printingDimensions.hashCode() : 0);
        return result;
    }*/
}
