package io.petersen.oauth.consumer.resources

import io.petersen.oauth.consumer.core.AccessToken
import io.petersen.oauth.consumer.db.AccessTokenDAO

import com.codahale.metrics.annotation.Timed
import java.net.URI
import javax.ws.rs.{ GET, Path, Produces, QueryParam }
import javax.ws.rs.core.Response
import dispatch._, Defaults._
import scala.concurrent.{ Await, Future }
import scala.concurrent.duration._
import org.json4s._
import org.json4s.jackson.JsonMethods._
import io.dropwizard.hibernate.UnitOfWork

@Path("/oauth/callback")
@Produces(Array("application/json"))
class OAuthCallbackResource(val appKey: String, val appSecret: String, val redirectUri: String, val timeout : Long, val dao : AccessTokenDAO) {
  @GET
  @Timed
  @UnitOfWork
  def callback(@QueryParam("code") code: Option[String]) : AccessToken = {
    implicit val formats = DefaultFormats

    // TODO: Make this generic
    val myRequest = host("api.dropbox.com").secure / "1" / "oauth2" / "token"
    val myPost = myRequest.POST.addParameter("code", code.get).addParameter("grant_type", "authorization_code").addParameter("client_id", appKey).addParameter("client_secret", appSecret).addParameter("redirect_uri", redirectUri)

    val json_future : Future[String] = Http(myPost OK as.String)
    val json_str : String = Await.result(json_future, Duration(timeout, MILLISECONDS))
    val json : JValue = parse(json_str)
    val accessTokenStr : String = (json \ "access_token").extract[String]
    val uid : Long =  (json \ "uid").extract[String].toLong

    val accessToken = new AccessToken(accessTokenStr, uid)
    dao.create(accessToken)
    accessToken
  }
}
