import com.genestimate.webapp.control.ProductService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Kais on 22.01.2017.
 */

@ApplicationPath("/api/")
public class GenestimateApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(ProductService.class);

        return classes;
    }
}
