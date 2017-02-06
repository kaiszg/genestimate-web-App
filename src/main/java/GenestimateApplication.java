import com.genestimate.webapp.filters.CORSFilter;
import com.genestimate.webapp.services.*;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Kais on 22.01.2017.
 */

@ApplicationPath("/api/")
public class GenestimateApplication extends Application {
    public GenestimateApplication() {

    }

    @Override
    public Set<Class<?>> getClasses() {
        // Add REST resources
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(DimensionsService.class);
        classes.add(BindingTypeService.class);
        classes.add(RawMaterialService.class);
        classes.add(PrintingTypeService.class);
        classes.add(EstimateService.class);
        classes.add(ProductService.class);
        classes.add(LoginService.class);

        return classes;
    }
}
