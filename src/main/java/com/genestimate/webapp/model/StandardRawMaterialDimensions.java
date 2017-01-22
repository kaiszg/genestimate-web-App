package com.genestimate.webapp.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Kais on 15.01.2017.
 */

@Entity
@DiscriminatorValue("STANDARD")
public class StandardRawMaterialDimensions extends Dimensions{

    private List<PrintingRawMaterialDimensions> printingDimensionss;

    @OneToMany(mappedBy = "standardDimensions")
    public List<PrintingRawMaterialDimensions> getPrintingDimensionss() {
        return printingDimensionss;
    }

    public void setPrintingDimensionss(List<PrintingRawMaterialDimensions> printingDimensionss) {
        this.printingDimensionss = printingDimensionss;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StandardRawMaterialDimensions that = (StandardRawMaterialDimensions) o;

        return printingDimensionss != null ? printingDimensionss.equals(that.printingDimensionss) : that.printingDimensionss == null;
    }

    @Override
    public int hashCode() {
        return printingDimensionss != null ? printingDimensionss.hashCode() : 0;
    }
}
