package de.mkienitz.bachelorarbeit.order.application;

import de.mkienitz.bachelorarbeit.order.domain.*;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

/**
 *
 */
@ApplicationScoped
public class OrderService {

    public static final String SYS_ENV_VAR_ADDRESSVALIDATION_SERVICE_URL = "BA_ADDRESSVALIDATION_SERVICE_URL";

    private static Logger log = LoggerFactory.getLogger(OrderService.class.getName());

    private AddressValidationServiceClient addressValidationServiceClient;

    private Random rnd = new SecureRandom();

    public OrderService() throws MalformedURLException {
        String addressValidationServiceUrl = System.getenv(SYS_ENV_VAR_ADDRESSVALIDATION_SERVICE_URL);

        log.info(String.format("OrderResource(): env.%s = %s", SYS_ENV_VAR_ADDRESSVALIDATION_SERVICE_URL, addressValidationServiceUrl));

        log.debug("OrderResource(): creating AddressValidationServiceClient");

        URL addressValidationServiceUrl2 = new URL(addressValidationServiceUrl);

        AddressValidationServiceClient addressValidationServiceClient = RestClientBuilder
                .newBuilder()
                .baseUrl(addressValidationServiceUrl2)
                .build(AddressValidationServiceClient.class);

        log.debug("OrderResource(): successfully created AddressValidationServiceClient");

        this.addressValidationServiceClient = addressValidationServiceClient;
    }

    public Receipt placeOrder(Order order, ShoppingCart shoppingCart) throws InvalidOrderException {
        try {
            validateBillingAddress(order.getBillingAddress());

            ReceiptShippingData receiptShippingData = validateShippingData(order.getShippingData());

            int orderId = this.generateOrderId(order);
            String paymentType = this.extractPaymentType(order.getPaymentData());
            int itemCount = this.calculateItemCount(shoppingCart);
            double total = this.calculateTotal(shoppingCart);

            // simulate sending data to an external service
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Receipt receipt = new Receipt(orderId, receiptShippingData, paymentType, itemCount, total);

            return receipt;
        } catch(Exception e) {
            throw new InvalidOrderException("Ein unbekannter Fehler ist aufgetreten: \"" + e.getMessage() + "\"");
        }
    }

    private void validateBillingAddress(OrderBillingAddress billingAddress) {
        log.info("validateBillingAddress(): validating " + billingAddress);

        Address a = new Address(
                billingAddress.getStreetName(),
                billingAddress.getStreetNumber().toString(),
                billingAddress.getPostalCode().toString(),
                billingAddress.getCity()
        );

        this.addressValidationServiceClient.validateAddress(a);
    }

    private ReceiptShippingData validateShippingData(OrderShippingData shippingData) {
        log.info("validateShippingData(): validating " + shippingData);

        Address a = new Address(
                shippingData.getStreetName(),
                shippingData.getStreetNumber().toString(),
                shippingData.getPostalCode().toString(),
                shippingData.getCity()
        );

        this.addressValidationServiceClient.validateAddress(a);

        ReceiptShippingData receiptShippingData = new ReceiptShippingData(
                shippingData.getSalutation(),
                shippingData.getFirstName().substring(0, 20),
                shippingData.getLastName().substring(0, 20),
                shippingData.getStreetName(),
                shippingData.getStreetNumber(),
                shippingData.getPostalCode(),
                shippingData.getCity()
        );

        return receiptShippingData;
    }

    private int generateOrderId(Order order) throws InvalidOrderException {
        int orderId = rnd.nextInt(10000);

        return orderId;
    }

    private String extractPaymentType(OrderPaymentData paymentData) throws InvalidOrderException {
        // only one paymentType should not be null

        if(paymentData.getRechnungData() != null) {
            return "Rechnung";
        }
        if(paymentData.getLastschriftData() != null) {
            return "Lastschrift";
        }
        if(paymentData.getPaypalData() != null) {
            return "PayPal";
        }
        if(paymentData.getKreditkartenData() != null) {
            return "Kreditkarte";
        }

        throw new InvalidOrderException("Es wurde keine Zahlungsart ausgewählt.");
    }

    private int calculateItemCount(ShoppingCart shoppingCart) {
        List<ShoppingCartItem> items = shoppingCart.getItems();

        return items.size();
    }

    private double calculateTotal(ShoppingCart shoppingCart) {
        List<ShoppingCartItem> items = shoppingCart.getItems();

        double total = 0;

        for(ShoppingCartItem item : items) {
            Integer amount = item.getAmount();
            Double price = item.getPrice();

            total += amount * price;
        }

        return total;
    }
}
