package org.example.util;

/**
 * Contain the names of all the supported databases for parsing connection URL
 */
public enum SupportedDatabase {
  MYSQL("mysql");

  private final String value;

  SupportedDatabase(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static SupportedDatabase fromValue(String value) {
    for (SupportedDatabase database : SupportedDatabase.values()) {
      if (database.getValue().equals(value)) {
        return database;
      }
    }
    throw new IllegalArgumentException("Database: " + value + " is not supported for parsing");
  }
}
