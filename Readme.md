
# Database URL Parser

A maven based Java (17) Application which parses jdbc database connection string into a configuration object

## How it works
This application uses a static field `URL` in the main class `URLParser`. The program picks up this URL and if the database is supported prints the parsed object in console

## Supported Databases
Right now only MySQL is supported

## How to add support for more Databases
We can add support for more database following these steps:

- Add the database name in enum `SupportedDatabase`
- Add a new parser class for the database and implement `DatabaseURLParser`
- Update the `DatabaseURLParserFactory` to use the correct parser for the new database
- If required, add a new config object which extends the base `DatabaseConfig` for adding more config fields

## Tests
Right now there is only 1 unit test in the class `MySQLParserTest` which verifies that the parsed config object has correct data for a mysql connection string