package com.genestimate.webapp.services;

import com.genestimate.webapp.ejbs.PrintingTypeBean;
import com.genestimate.webapp.model.PrintingType;

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
 * Created by Kais on 31.01.2017.
 */

@Path("/printing-types")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class PrintingTypeService {

    PrintingTypeBean printingTypeBean;

    public PrintingTypeService() {
        try {
            this.printingTypeBean = (PrintingTypeBean) new InitialContext().lookup("java:module/PrintingTypeEJB!com.genestimate.webapp.ejbs.PrintingTypeBean");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @GET
    @RolesAllowed({"USER", "ADMIN"})
    public List<PrintingType> getAllPrintingTypes(){
        PrintingType printingType1 = new PrintingType();
        printingType1.setName("One Color");
        printingTypeBean.create(printingType1);

        PrintingType printingType2 = new PrintingType();
        printingType2.setName("Multiple colors");
        printingTypeBean.create(printingType2);

        return printingTypeBean.findAll();
    }
}
