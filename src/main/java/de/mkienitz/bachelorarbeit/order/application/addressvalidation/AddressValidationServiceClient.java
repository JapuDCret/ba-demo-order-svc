package de.mkienitz.bachelorarbeit.order.application.addressvalidation;

import de.mkienitz.bachelorarbeit.order.domain.Address;
import de.mkienitz.bachelorarbeit.order.domain.ValidationResult;

import javax.ws.rs.*;

@Path("/data/address-validation")
public interface AddressValidationServiceClient {

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    ValidationResult validateAddress(Address address);
}
