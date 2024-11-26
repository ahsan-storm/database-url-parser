package org.example.factory;

import org.example.util.SupportedDatabase;
import org.example.parser.DatabaseURLParser;
import org.example.parser.MySQLParser;

/**
 * Returns the database specific parser
 */
public class DatabaseURLParserFactory {
  public static DatabaseURLParser getParser(String databaseType) {
    return switch (SupportedDatabase.fromValue(databaseType)) {
      case MYSQL -> new MySQLParser();
    };
  }
}
