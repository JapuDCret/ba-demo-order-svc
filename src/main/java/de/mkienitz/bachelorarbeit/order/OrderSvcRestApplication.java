package de.mkienitz.bachelorarbeit.order;

import de.mkienitz.bachelorarbeit.order.application.OrderResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/data")
public class OrderSvcRestApplication extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderSvcRestApplication.class.getName());

    public static final String ENVVAR_CART_SERVICE_URL = "BA_CART_SERVICE_URL";
    public static final String ENVVAR_ADDRESSVALIDATION_SERVICE_URL = "BA_ADDRESSVALIDATION_SERVICE_URL";

    public OrderSvcRestApplication() {
        LOGGER.info("env." + ENVVAR_CART_SERVICE_URL + " = " + System.getenv(ENVVAR_CART_SERVICE_URL));
        LOGGER.info("env." + ENVVAR_ADDRESSVALIDATION_SERVICE_URL + " = " + System.getenv(ENVVAR_ADDRESSVALIDATION_SERVICE_URL));
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<Class<?>>();

        s.add(CORSFilter.class);
        s.add(OrderResource.class);

        return s;
    }
}
