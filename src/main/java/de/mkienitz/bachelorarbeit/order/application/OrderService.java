package de.mkienitz.bachelorarbeit.order.application;

import de.mkienitz.bachelorarbeit.order.domain.*;
import org.eclipse.microprofile.opentracing.Traced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

/**
 *
 */
@ApplicationScoped
public class OrderService {

    private static Logger log = LoggerFactory.getLogger(OrderService.class.getName());

    private Random rnd = new SecureRandom();

    @Inject
    private ValidationService validation;

    @Traced(operationName = "OrderService.placeOrder")
    public Receipt placeOrder(Order order, ShoppingCart shoppingCart) throws InvalidOrderException {
        try {
            validation.validateBillingAddress(order.getBillingAddress());

            ReceiptShippingData receiptShippingData = validation.validateShippingData(order.getShippingData());

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
