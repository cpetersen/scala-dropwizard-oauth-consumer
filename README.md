# Scala Dropwizard OAuth Consumer

A dropwizard oauth consumer written in Scala. Given an oauth provider url, app key and app secret, this application will:
 * Redirect the user to the OAuth Provider
 * Assuming the user authorizes the Application, they will be returned to `/oauth/callback`
 * The Application will then take the provided `code` and retrieve the bearer token.
 * The `token` as well `uid` is then saved to the database.

## Motivation

I wanted to build an API on top of Dropbox using Scala and Dropwizard. The Dropbox SDK providers OAuth 2 authorization, but it requires access to the Session, which Dropwizard doesn't provide. So I built an OAuth2 consumer.

## Dependencies

 * Scala 2.10
 * [Dropwizard](http://dropwizard.github.io/dropwizard/) 0.7.0-rc2
 * Dropwizard Hibernate 0.7.0-rc2
 * Dropwizard Migrations 0.7.0-rc2
 * [Dropwizard Scala](https://github.com/bretthoerner/dropwizard-scala) 0.7.0-rc1
 * [Dispatch](http://dispatch.databinder.net/Dispatch.html) 0.11.0
 * [json4s](https://github.com/json4s/json4s) 3.2.7
 * postgresql

## Configuring
```yaml
oauthProviderUrl: "https://www.dropbox.com/1/oauth2/authorize?locale=en_US&client_id=%s&redirect_uri=%s&response_type=code"
oauthAppKey: YOUR APP ID
oauthAppSecret: YOUR APP SECRET
oauthRedirectUri: http://localhost:8080/oauth/callback

# Database settings.
database:
  # the name of your JDBC driver
  driverClass: org.postgresql.Driver

  # the username
  # user: pg-user

  # the password
  # password: iAMs00perSecrEET

  # the JDBC URL
  url: jdbc:postgresql://localhost:5432/YOUR_DATABASE

  # any properties specific to your JDBC driver:
  properties:
    charSet: UTF-8
    hibernate.dialect: org.hibernate.dialect.PostgreSQLDialect

  # the maximum amount of time to wait on an empty pool before throwing an exception
  maxWaitForConnection: 1s

  # the SQL query to run when validating a connection's liveness
  validationQuery: "/* MyApplication Health Check */ SELECT 1"

  # the minimum number of connections to keep open
  minSize: 8

  # the maximum number of connections to keep open
  maxSize: 32

  # whether or not idle connections should be validated
  checkConnectionWhileIdle: false
```

## Building

Build the "fat" jar

```sh
sbt assembly
```

Or clean build the "fat" jar

```sh
sbt clean assembly
```

## Migrate the Database

```sh
java -jar target/scala-2.10/scala-dropwizard-oauth-consumer-assembly-0.1.jar db migrate config.yml
```

## Running

```sh
java -jar target/scala-2.10/scala-dropwizard-oauth-consumer-assembly-0.1.jar server config.yml
```

## Using it

Once the app is running, visit http://localhost:8080/oauth

## ToDo

 1. Remove the last hardcoded reference to Dropbox (in `OAuthCallbackResource.scala`)
 2. Add a `HealthCheck` or two
 3. Add some tests