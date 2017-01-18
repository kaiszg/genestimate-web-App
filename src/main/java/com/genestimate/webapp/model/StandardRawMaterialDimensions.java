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
}
