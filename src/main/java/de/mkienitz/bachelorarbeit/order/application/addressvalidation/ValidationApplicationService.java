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
public class ValidationApplicationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationApplicationService.class.getName());

    @Inject
    private AddressValidationServiceClient client;

    @Traced(operationName = "ValidationService.validateBillingAddress")
    public void validateBillingAddress(OrderBillingAddress billingAddress) {
        LOGGER.info("validateBillingAddress(): validating " + billingAddress);

        Address address = new Address(
                billingAddress.getStreetName(),
                billingAddress.getStreetNumber(),
                billingAddress.getPostalCode().toString(),
                billingAddress.getCity()
        );

        this.client.validateAddress(address);
    }

    @Traced(operationName = "ValidationService.validateShippingData")
    public ReceiptShippingData validateShippingData(OrderShippingData shippingData) {
        LOGGER.info("validateShippingData(): validating " + shippingData);

        Address address = new Address(
                shippingData.getStreetName(),
                shippingData.getStreetNumber(),
                shippingData.getPostalCode().toString(),
                shippingData.getCity()
        );

        this.client.validateAddress(address);

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
