# Scala Dropwizard OAuth Consumer

A dropwizard oauth consumer written in Scala

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

## Running

```sh
java -jar target/scala-2.10/scala-dropwizard-oauth-consumer-assembly-0.1.jar server config.yml
```
