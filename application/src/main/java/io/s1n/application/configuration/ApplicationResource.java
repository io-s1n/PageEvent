package io.s1n.application.configuration;

import static jakarta.ws.rs.core.MediaType.*;

import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.NoCache;
import rest.MetadataRestResource;


@ApplicationScoped
@Path("/resource")
public class ApplicationResource {

  @Inject
  StorageService storageService;

  @POST
  @NoCache
  @RunOnVirtualThread
  @Produces
  @Consumes(APPLICATION_JSON)
  public Response af(MetadataRestResource resource) {
    return Response.ok(
        storageService.processResource(resource)
    ).build();
  }
}
