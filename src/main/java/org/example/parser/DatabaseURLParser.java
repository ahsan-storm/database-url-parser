package org.example.parser;

import org.example.config.DatabaseConfig;

/**
 * To be implemented by all database specific parsers
 */
public interface DatabaseURLParser {
  DatabaseConfig parseURL(String url);
}
