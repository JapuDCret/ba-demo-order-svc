package de.mkienitz.bachelorarbeit.order.application;

import de.mkienitz.bachelorarbeit.order.domain.*;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

/**
 *
 */
@ApplicationScoped
public class ValidationService {

    public static final String SYS_ENV_VAR_ADDRESSVALIDATION_SERVICE_URL = "BA_ADDRESSVALIDATION_SERVICE_URL";

    private static Logger log = LoggerFactory.getLogger(ValidationService.class.getName());

    private AddressValidationServiceClient addressValidationServiceClient;

    private ValidationService self;

    public ValidationService() throws MalformedURLException {
        String addressValidationServiceUrl = System.getenv(SYS_ENV_VAR_ADDRESSVALIDATION_SERVICE_URL);

        log.info(String.format("ValidationService(): env.%s = %s", SYS_ENV_VAR_ADDRESSVALIDATION_SERVICE_URL, addressValidationServiceUrl));

        log.debug("ValidationService(): creating AddressValidationServiceClient");

        URL addressValidationServiceUrl2 = new URL(addressValidationServiceUrl);

        AddressValidationServiceClient addressValidationServiceClient = RestClientBuilder
                .newBuilder()
                .baseUrl(addressValidationServiceUrl2)
                .build(AddressValidationServiceClient.class);

        log.debug("ValidationService(): successfully created AddressValidationServiceClient");

        this.addressValidationServiceClient = addressValidationServiceClient;
    }

    @Traced(operationName = "ValidationService.validateBillingAddress")
    public void validateBillingAddress(OrderBillingAddress billingAddress) {
        log.info("validateBillingAddress(): validating " + billingAddress);

        Address a = new Address(
                billingAddress.getStreetName(),
                billingAddress.getStreetNumber().toString(),
                billingAddress.getPostalCode().toString(),
                billingAddress.getCity()
        );

        this.addressValidationServiceClient.validateAddress(a);
    }

    @Traced(operationName = "ValidationService.validateShippingData")
    public ReceiptShippingData validateShippingData(OrderShippingData shippingData) {
        log.info("validateShippingData(): validating " + shippingData);

        Address a = new Address(
                shippingData.getStreetName(),
                shippingData.getStreetNumber().toString(),
                shippingData.getPostalCode().toString(),
                shippingData.getCity()
        );

        this.addressValidationServiceClient.validateAddress(a);

        String firstName = shippingData.getFirstName();
        String firstNameTrimmed = firstName.substring(0, Math.min(firstName.length(), 20));

        String lastName = shippingData.getLastName();
        String lastNameTrimmed = lastName.substring(0, Math.min(lastName.length(), 20));

        ReceiptShippingData receiptShippingData = new ReceiptShippingData(
                shippingData.getSalutation(),
                firstNameTrimmed,
                lastNameTrimmed,
                shippingData.getStreetName(),
                shippingData.getStreetNumber(),
                shippingData.getPostalCode(),
                shippingData.getCity()
        );

        return receiptShippingData;
    }
}
