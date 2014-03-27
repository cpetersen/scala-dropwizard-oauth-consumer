package io.petersen.oauth.consumer

import com.massrelevance.dropwizard.ScalaApplication
import com.massrelevance.dropwizard.bundles.ScalaBundle

import io.dropwizard.db.DataSourceFactory

import io.dropwizard.hibernate.HibernateBundle
import io.dropwizard.migrations.MigrationsBundle

import io.dropwizard.setup.{Environment, Bootstrap}

import core.AccessToken
import db.AccessTokenDAO
import resources.{ OAuthRedirectResource, OAuthCallbackResource }

object OauthConsumerApplication extends ScalaApplication[OauthConsumerConfiguration] {
  val hibernateBundle : HibernateBundle[OauthConsumerConfiguration] = new HibernateBundle[OauthConsumerConfiguration](classOf[AccessToken]) {
    override def getDataSourceFactory(configuration : OauthConsumerConfiguration) : DataSourceFactory = configuration dataSourceFactory
  }
  val migrationsBundle : MigrationsBundle[OauthConsumerConfiguration] = new MigrationsBundle[OauthConsumerConfiguration]() {
    override def getDataSourceFactory(configuration : OauthConsumerConfiguration) : DataSourceFactory = configuration dataSourceFactory
  }

  def initialize(bootstrap: Bootstrap[OauthConsumerConfiguration]) {
    bootstrap.addBundle( new ScalaBundle )
    bootstrap.addBundle( migrationsBundle )
    bootstrap.addBundle( hibernateBundle )
  }

  def run(configuration: OauthConsumerConfiguration, environment: Environment) {
    val accessTokenDao : AccessTokenDAO = new AccessTokenDAO(hibernateBundle.getSessionFactory)

    environment.jersey().register(new OAuthRedirectResource(configuration oauthProviderUrl, configuration oauthAppKey, configuration oauthRedirectUri))
    environment.jersey().register(new OAuthCallbackResource(configuration oauthAppKey, configuration oauthAppSecret, configuration oauthRedirectUri, configuration oauthTimeout, accessTokenDao))
    // environment.addHealthCheck(new TemplateHealthCheck(configuration template))
  }
}
