package de.mkienitz.bachelorarbeit.order.application;

import de.mkienitz.bachelorarbeit.order.application.addressvalidation.ValidationApplicationService;
import de.mkienitz.bachelorarbeit.order.application.cart.CartServiceClient;
import de.mkienitz.bachelorarbeit.order.domain.Order;
import de.mkienitz.bachelorarbeit.order.domain.Receipt;
import de.mkienitz.bachelorarbeit.order.domain.ReceiptShippingData;
import de.mkienitz.bachelorarbeit.order.domain.ShoppingCart;
import org.eclipse.microprofile.opentracing.Traced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import java.security.SecureRandom;
import java.util.Random;

@ApplicationScoped
public class OrderApplicationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderApplicationService.class.getName());

    private Random rnd = new SecureRandom();

    @Inject
    private ValidationApplicationService validation;

    @Inject
    private CartServiceClient cartServiceClient;

    @Traced(operationName = "OrderApplicationService.placeOrder")
    public Receipt placeOrder(final Order order) throws InvalidOrderException {
        ShoppingCart shoppingCart = this.getShoppingCart(order);

        ReceiptShippingData receiptShippingData = validateOrder(order);

        return calculateReceipt(order, shoppingCart, receiptShippingData);
    }

    private ShoppingCart getShoppingCart(Order order) throws InvalidOrderException {
        final String shoppingCartId = order.getShoppingCartId();

        LOGGER.trace("getShoppingCart(): requesting shopping cart with id = " + shoppingCartId);

        try {
            return cartServiceClient.getShoppingCart(shoppingCartId);
        } catch (WebApplicationException e) {
            LOGGER.error("order(): could not request shoppingCart, Exception = ", e);

            throw new InvalidOrderException("Warenkorb konnte nicht abgerufen werden. Fehler: " + e.getMessage());
        }
    }

    private ReceiptShippingData validateOrder(Order order) throws InvalidOrderException {
        LOGGER.debug("validateOrder(): validating order with id = " + order.getShoppingCartId());

        try {
            validation.validateBillingAddress(order.getBillingAddress());

            ReceiptShippingData receiptShippingData = validation.validateShippingData(order.getShippingData());

            return receiptShippingData;
        } catch(WebApplicationException e) {
            throw new InvalidOrderException("Fehler bei Adressvalidierung: \"" + e.getMessage() + "\"");
        }
    }

    private Receipt calculateReceipt(Order order, ShoppingCart shoppingCart, ReceiptShippingData receiptShippingData) throws InvalidOrderException {
        int itemCount = shoppingCart.getItemCount();

        int orderId = this.generateOrderId();
        String paymentType = order.getPaymentType();
        double total = shoppingCart.getTotal();

        // simulate delay, caused by sending data to an external service
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // ignore
        }

        LOGGER.trace("placeOrder(): creating receipt");

        Receipt receipt = new Receipt(orderId, receiptShippingData, paymentType, itemCount, total);

        return receipt;
    }

    private int generateOrderId() {
        int orderId = rnd.nextInt(10000);

        return orderId;
    }
}
