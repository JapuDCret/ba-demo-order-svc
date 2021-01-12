package de.mkienitz.bachelorarbeit.order.application;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
@ApplicationPath("/data")
public class OrderSvcRestApplication extends Application {

    public OrderSvcRestApplication() {

    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<Class<?>>();

        s.add(OrderResource.class);

        return s;
    }
}
