package io.petersen.oauth.consumer

import io.dropwizard.Configuration
import io.dropwizard.db.DataSourceFactory
import org.hibernate.validator.constraints.NotEmpty
import javax.validation.constraints.NotNull
import com.fasterxml.jackson.annotation.JsonProperty

class OauthConsumerConfiguration extends Configuration {
  @NotEmpty
  @JsonProperty
  val name : String = "scala-dropwizard-oauth-consumer"

  @NotEmpty
  @JsonProperty
  val oauthProviderUrl : String = null

  @NotEmpty
  @JsonProperty
  val oauthAppKey : String = null

  @NotEmpty
  @JsonProperty
  val oauthAppSecret : String = null

  @NotEmpty
  @JsonProperty
  val oauthRedirectUri : String = null

  @NotNull
  @JsonProperty
  val oauthTimeout : Long = 5000

  @NotNull
  //@Valid
  @JsonProperty("database")
  val dataSourceFactory : DataSourceFactory = null
}
