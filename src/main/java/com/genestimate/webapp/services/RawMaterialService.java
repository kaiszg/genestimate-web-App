package com.genestimate.webapp.services;

import com.genestimate.webapp.ejbs.RawMaterialBean;
import com.genestimate.webapp.model.RawMaterial;

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

@Path("/raw-materials")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class RawMaterialService {

    RawMaterialBean rawMaterialBean;

    public RawMaterialService() {
        try {
            rawMaterialBean = (RawMaterialBean) new InitialContext().lookup("java:module/RawMaterialEJB!com.genestimate.webapp.ejbs.RawMaterialBean");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @GET
    @RolesAllowed({"USER", "ADMIN"})
    public List<RawMaterial> getAllRawMaterials(){
        RawMaterial rawMaterial1 = new RawMaterial();
        rawMaterial1.setName("Uncoated White 50 lb");
        rawMaterial1.setPrice(0.06);
        rawMaterialBean.create(rawMaterial1);
        rawMaterial1 = new RawMaterial();
        rawMaterial1.setName("Coated bright white 60 lb");
        rawMaterial1.setPrice(0.07);
        rawMaterialBean.create(rawMaterial1);
        return rawMaterialBean.findAll();
    }
}
