package org.example.config;

import java.util.Map;

public class MySQLConfig extends DatabaseConfig {

  // Add MySQL specific properties here if necessary in the future

  public MySQLConfig(String host, int port, Map<String, String> failoverHosts, String database, Map<String, String> properties) {
    super(host, port, failoverHosts, database, properties);
  }
}
