package de.mkienitz.bachelorarbeit.order.application;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/data")
public class OrderSvcRestApplication extends Application {

    public static final String SYS_ENV_VAR_CART_SERVICE_URL = "BA_CART_SERVICE_URL";
    public static final String SYS_ENV_VAR_ADDRESSVALIDATION_SERVICE_URL = "BA_ADDRESSVALIDATION_SERVICE_URL";

    public OrderSvcRestApplication() {

    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<Class<?>>();

        s.add(CORSFilter.class);
        s.add(OrderResource.class);

        return s;
    }
}
