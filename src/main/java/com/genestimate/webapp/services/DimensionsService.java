package com.genestimate.webapp.services;

import com.genestimate.webapp.ejbs.DimensionsBean;
import com.genestimate.webapp.model.FinalProductDimensions;
import com.genestimate.webapp.model.PrintingRawMaterialDimensions;
import com.genestimate.webapp.model.StandardRawMaterialDimensions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.RolesAllowed;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Kais on 30.01.2017.
 */

@Path("/dimensions")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class DimensionsService {

    private final Logger LOGGER = LoggerFactory.getLogger(DimensionsService.class);

    DimensionsBean dimensionsBean;

    public DimensionsService() {
        try {
            this.dimensionsBean = (DimensionsBean) new InitialContext().lookup("java:module/DimensionsEJB!com.genestimate.webapp.ejbs.DimensionsBean");
        } catch (NamingException e) {
            LOGGER.error("Failed to initialize Dimensions EJB");
            e.printStackTrace();
        }
    }

    @GET
    @Path("/final")
    @RolesAllowed({"USER", "ADMIN"})
    public List<FinalProductDimensions> getAllFinalProductDimensions(){
        FinalProductDimensions finalDimensions = new FinalProductDimensions();
        finalDimensions.setLength(21);
        finalDimensions.setWidth(27);
        finalDimensions.setNbCoverCopiesPerPaper(2);
        finalDimensions.setNbPagesPerPaper(8);
        PrintingRawMaterialDimensions printingDimensions = new PrintingRawMaterialDimensions();
        printingDimensions.setLength(56);
        printingDimensions.setLength(44);
        printingDimensions.setNbPagesFromStandard(2);
        StandardRawMaterialDimensions standardDimensions = new StandardRawMaterialDimensions();
        standardDimensions.setLength(56);
        standardDimensions.setWidth(88);
        printingDimensions.setStandardDimensions(standardDimensions);
        finalDimensions.setPrintingDimensions(printingDimensions);
        dimensionsBean.create(standardDimensions);
        dimensionsBean.create(printingDimensions);
        dimensionsBean.create(finalDimensions);

        return dimensionsBean.findAllFinalDimensions();
    }
}
