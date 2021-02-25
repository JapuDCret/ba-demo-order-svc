package de.mkienitz.bachelorarbeit.order.application.cart;

import de.mkienitz.bachelorarbeit.order.OrderSvcRestApplication;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.net.MalformedURLException;
import java.net.URI;

@ApplicationScoped
public class CartServiceClientFactory {

    private static final Logger log = LoggerFactory.getLogger(CartServiceClientFactory.class.getName());

    @Produces
    public CartServiceClient createClient() throws MalformedURLException {
        URI cartServiceUri = URI.create(System.getenv(OrderSvcRestApplication.ENVVAR_CART_SERVICE_URL));

        log.info(String.format("createClient(): env.%s = %s", OrderSvcRestApplication.ENVVAR_CART_SERVICE_URL, cartServiceUri));

        log.debug("createClient(): creating CartServiceClient");

        CartServiceClient cartServiceClient = RestClientBuilder
                .newBuilder()
                .baseUrl(cartServiceUri.toURL())
                .build(CartServiceClient.class);

        log.debug("createClient(): successfully created CartServiceClient");

        return cartServiceClient;
    }
}
