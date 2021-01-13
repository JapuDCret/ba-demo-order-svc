package de.mkienitz.bachelorarbeit.order.application;

import de.mkienitz.bachelorarbeit.order.domain.Address;
import de.mkienitz.bachelorarbeit.order.domain.ValidationResult;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;

@RegisterRestClient
@Path("/data/address-validation")
public interface AddressValidationServiceClient {

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    ValidationResult validateAddress(Address address);
}
