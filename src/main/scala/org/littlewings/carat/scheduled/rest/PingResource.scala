package org.littlewings.carat.scheduled.rest

import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.core.MediaType
import javax.ws.rs.{GET, Path, Produces}

@ApplicationScoped
@Path("ping")
class PingResource {
  @GET
  @Produces(Array(MediaType.TEXT_PLAIN))
  def execute: String = "Ping OK!"
}
