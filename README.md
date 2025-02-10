# schwab-api-sdk-java
A Java SDK implementation for the Schwab API by Langhavens. This implementation includes a reactive, non-blocking client and a blocking client.

# Setup
This library requires a valid [Schwab API](https://developer.schwab.com/) - Trader Individual account as well as an active App with associated `app_key` and `secret` values. \
The default configuration attempts to read these secrets as environment variables. \
`${SCHWAB_API_APP_KEY}` \
`${SCHWAB_API_SECRET}`

This library currently depends on inclusion in a Spring Boot application to initialize.

# Use
For reactive, non-blocking requests to the Schwab API use `com.langhavens.sdk.schwabapi.ReactiveSchwabApiClient`. \
For synchronous, blocking requests use `com.langhavens.sdk.schwabapi.SchwabApiClient`.

These client classes are Spring managed beans.