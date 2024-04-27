package dev.ebento.resource;

import dev.ebento.dto.PersonDTO;
import dev.ebento.service.PersonService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/person")
public class PersonResource {

    @Inject
    PersonService personService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{personId}")
    public Uni<PersonDTO> findById(@PathParam(PersonDTO.Fields.personId) Long personId) {
        return personService.findById();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<PersonDTO> findAll() {
        return personService.findAll();
    }

}
