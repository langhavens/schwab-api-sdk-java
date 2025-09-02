# schwab-api-sdk-java
A Java SDK implementation for the Schwab API by Langhavens. This implementation includes a reactive, non-blocking client and a blocking client.

# Pre-requisites
1. A valid, [Schwab API](https://developer.schwab.com/) Trader Individual, account and an active App with associated `app_key` and `secret` values are required to authenticate with the Schwab API.
2. Requires Java 21+.

# Setup
1. Add `schwab` under `spring.profiles.active:` in your application properties.
2. Set `${SCHWAB_API_APP_KEY}` as an environment variable.
3. Set `${SCHWAB_API_SECRET}` as an environment variable.

When the `schwab` Spring profile is active, the secrets will be read as environment variables.
It is not enabled by default.
For alternative secrets configuration, refer to `src/main/resources/application-schwab.yml` in this repository.

This library currently depends on inclusion in a Spring Boot application to initialize.

# Build
This project is not yet published to Maven Central. 
To use this library as a dependency in your project, run `./gradlew build publishToMavenLocal` to add the built dependency to your local Maven repository.

# Use
For reactive, non-blocking requests use `com.langhavens.sdk.schwabapi.ReactiveSchwabApiClient`. \
For synchronous, blocking requests use `com.langhavens.sdk.schwabapi.SchwabApiClient`.

The client classes are Spring managed beans.

# Development Backlog
- [ ] Add Quote response objects for other instruments (currently only support equities).
- [ ] Publish this library in Maven central.
- [ ] Refactor the dependencies so this SDK can run outside a Spring application.