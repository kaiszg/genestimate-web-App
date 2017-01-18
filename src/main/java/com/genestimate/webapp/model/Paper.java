package com.genestimate.webapp.model;

import javax.persistence.*;

/**
 * Created by Kais on 14.01.2017.
 */
@Entity
public class Paper {
    private FinalProductDimensions finalDimensions;
    private Integer nbPages;
    private Dimensions printingDimensions;

    @Id
    @ManyToOne
    @JoinColumn(name = "FINALDIMENSIONS", referencedColumnName = "ID", nullable = false)
    public FinalProductDimensions getFinalDimensions() {
        return finalDimensions;
    }

    public void setFinalDimensions(FinalProductDimensions finalDimensions) {
        this.finalDimensions = finalDimensions;
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

        if (!finalDimensions.equals(paper.finalDimensions)) return false;
        if (!nbPages.equals(paper.nbPages)) return false;
        return printingDimensions.equals(paper.printingDimensions);
    }

    @Override
    public int hashCode() {
        int result = finalDimensions.hashCode();
        result = 31 * result + nbPages.hashCode();
        result = 31 * result + printingDimensions.hashCode();
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "PRINTINGDIMENSIONS", referencedColumnName = "ID", nullable = false)
    public Dimensions getPrintingDimensions() {
        return printingDimensions;
    }

    public void setPrintingDimensions(Dimensions printingDimensions) {
        this.printingDimensions = printingDimensions;
    }
}
