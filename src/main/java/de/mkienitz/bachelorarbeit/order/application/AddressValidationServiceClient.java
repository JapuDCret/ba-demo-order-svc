package de.mkienitz.bachelorarbeit.order.application;

import de.mkienitz.bachelorarbeit.order.domain.Address;
import de.mkienitz.bachelorarbeit.order.domain.ValidationResult;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;

@RegisterRestClient
@Path("/data/address-validation")
@Produces("application/json")
@Consumes("application/json")
public interface AddressValidationServiceClient {

    @GET
    ValidationResult validateAddress(Address address);
}
