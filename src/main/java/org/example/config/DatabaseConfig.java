package org.example.config;

import java.util.HashMap;
import java.util.Map;

/**
 * Base class for the database configuration having common fields require for almost all connection strings
 */
public class DatabaseConfig {
  private final String host;
  private final int port;
  private final Map<String, String> failoverHosts;
  private final String database;
  private final Map<String, String> properties;

  public DatabaseConfig(String host, int port, Map<String, String> failoverHosts, String database, Map<String, String> properties) {
    this.host = host;
    this.port = port;
    this.failoverHosts = failoverHosts == null ? new HashMap<>() : failoverHosts;
    this.database = database;
    this.properties = properties == null ? new HashMap<>() : properties;
  }

  public String getHost() {
    return host;
  }

  public int getPort() {
    return port;
  }

  public String getDatabase() {
    return database;
  }

  public Map<String, String> getFailoverHosts() {
    return failoverHosts;
  }

  public Map<String, String> getProperties() {
    return properties;
  }

  public void addProperty(String key, String value) {
    this.properties.put(key, value);
  }

  public void addFailoverHost(String key, String value) {
    this.failoverHosts.put(key, value);
  }

  @Override
  public String toString() {
    return "DatabaseConfig{" +
        "host='" + host + '\'' +
        ", port='" + port + '\'' +
        ", failoverHosts='" + failoverHosts + '\'' +
        ", database='" + database + '\'' +
        ", properties='" + properties + '\'' +
        '}';
  }
}
