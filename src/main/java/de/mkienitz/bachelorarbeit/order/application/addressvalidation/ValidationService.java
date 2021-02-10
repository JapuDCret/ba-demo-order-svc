package de.mkienitz.bachelorarbeit.order.application.addressvalidation;

import de.mkienitz.bachelorarbeit.order.domain.Address;
import de.mkienitz.bachelorarbeit.order.domain.OrderBillingAddress;
import de.mkienitz.bachelorarbeit.order.domain.OrderShippingData;
import de.mkienitz.bachelorarbeit.order.domain.ReceiptShippingData;
import org.eclipse.microprofile.opentracing.Traced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ValidationService {

    private static final Logger log = LoggerFactory.getLogger(ValidationService.class.getName());

    @Inject
    private AddressValidationServiceClient addressValidationServiceClient;

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
