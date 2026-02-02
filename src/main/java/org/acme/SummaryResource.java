package org.acme;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@Path("/summarize")
public class SummaryResource {

  @Channel("note-requests-out")
  Emitter<String> noteRequestEmitter;

  @POST
  @Path("/request")
  @Consumes(MediaType.TEXT_PLAIN)
  @Produces(MediaType.TEXT_PLAIN)
  public String createRequest(String text) {
    noteRequestEmitter.send(text);
    return "text";
  }
}
