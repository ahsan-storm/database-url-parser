package org.example;

import org.example.config.DatabaseConfig;
import org.example.factory.DatabaseURLParserFactory;

public class URLParser {
  private final static String URL = "jdbc:mysql://localhost:3306,localhost1:3306/testdb?param1=value1&param2=value2&param3=value3";

  public static void main(String[] args) {
    performBasicValidation(URL);

    String databaseName = extractDatabaseName(URL);
    DatabaseConfig databaseConfig = DatabaseURLParserFactory.getParser(databaseName).parseURL(URL);

    System.out.println("Input URL: " + URL);
    System.out.println("Parsed Object: " + databaseConfig);
  }

  private static String extractDatabaseName(String url) {
    return url.split(":")[1];
  }

  private static void performBasicValidation(String url) {
    if (!url.startsWith("jdbc:") ||
        url.split(":").length < 3) {
      throw new IllegalArgumentException("Invalid jdbc connection URL: " + url);
    }
  }
}