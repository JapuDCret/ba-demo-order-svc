package de.mkienitz.bachelorarbeit.order.application.addressvalidation;

import de.mkienitz.bachelorarbeit.order.OrderSvcRestApplication;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.net.MalformedURLException;
import java.net.URI;

@ApplicationScoped
public class AddressValidationServiceClientFactory {

    private static final Logger log = LoggerFactory.getLogger(AddressValidationServiceClientFactory.class.getName());

    @Produces
    public AddressValidationServiceClient createClient() throws MalformedURLException {
        URI addressValidationServiceUri = URI.create(System.getenv(OrderSvcRestApplication.ENVVAR_ADDRESSVALIDATION_SERVICE_URL));

        log.info(String.format("createClient(): env.%s = %s", OrderSvcRestApplication.ENVVAR_ADDRESSVALIDATION_SERVICE_URL, addressValidationServiceUri));

        log.debug("createClient(): creating AddressValidationServiceClient");

        AddressValidationServiceClient addressValidationServiceClient = RestClientBuilder
                .newBuilder()
                .baseUrl(addressValidationServiceUri.toURL())
                .build(AddressValidationServiceClient.class);

        log.debug("createClient(): successfully created AddressValidationServiceClient");

        return addressValidationServiceClient;
    }
}
