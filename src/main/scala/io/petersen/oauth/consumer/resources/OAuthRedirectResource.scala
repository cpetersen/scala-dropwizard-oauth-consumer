package io.petersen.oauth.consumer.resources

import com.codahale.metrics.annotation.Timed
import java.net.URI
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.Response

@Path("/oauth")
@Produces(Array("application/json"))
class OAuthRedirectResource(val providerUrl: String, val appKey: String, val redirectUri: String) {
  @GET
  @Timed
  def redirect : Response = {
    val authorizeUrl: String = String.format(providerUrl, appKey, redirectUri)
    Response.temporaryRedirect(new URI(authorizeUrl)).build()
  }
}
