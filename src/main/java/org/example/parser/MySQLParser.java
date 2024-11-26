package org.example.parser;

import org.example.config.DatabaseConfig;
import org.example.config.MySQLConfig;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MySQLParser implements DatabaseURLParser {
  @Override
  public DatabaseConfig parseURL(String url) {
    // sample value = localhost:3306,localhost1:3307/testdb?param1=value1&param2=value2&param3=value3
    String urlWithoutPrefix = url.split("//")[1];

    // sample value = localhost:3306,localhost1:3307
    String hostsFromUrl = urlWithoutPrefix.split("/")[0];
    String[] hosts = hostsFromUrl.split(",");

    // assuming that at least 1 host should be present in the url
    if (hosts.length < 1) {
      throw new IllegalArgumentException("Host not found in url: " + url);
    }

    String host = getPrimaryHost(hosts);
    int port = getPrimaryPort(hosts);
    Map<String, String> failoverHosts = getFailoverHosts(hosts);

    // sample value = testdb?param1=value1&param2=value2&param3=value3
    String urlWithDatabaseAndProperties = urlWithoutPrefix.split("/")[1];
    String[] databaseAndProperties = urlWithDatabaseAndProperties.split("\\?");

    // assuming that database name must be specified
    if (databaseAndProperties.length == 0) {
      throw new IllegalArgumentException("Database not found in url: " + url);
    }
    String database = databaseAndProperties[0];

    Map<String, String> properties = new HashMap<>();

    if (databaseAndProperties.length > 1) {
      properties = getOptionalProperties(databaseAndProperties[1]);
    }

    return new MySQLConfig(
        host,
        port,
        failoverHosts,
        database,
        properties
    );
  }

  private String getPrimaryHost(String[] hosts) {
    String primaryHost = Arrays.stream(hosts).findFirst().get();
    String[] hostAndPort = primaryHost.split(":");
    if (hostAndPort.length < 1) {
      throw new IllegalArgumentException("Invalid host found in url");
    }
    return hostAndPort[0];
  }

  private int getPrimaryPort(String[] hosts) {
    String primaryHost = Arrays.stream(hosts).findFirst().get();
    String[] hostAndPort = primaryHost.split(":");
    if (hostAndPort.length < 2) {
      throw new IllegalArgumentException("Port is missing in url");
    }
    return Integer.parseInt(hostAndPort[1]);
  }

  private Map<String, String> getFailoverHosts(String[] hosts) {
    Map<String, String> failoverHosts = new HashMap<>();

    if (hosts.length > 1) {
      Arrays.stream(hosts)
          .skip(1)
          .forEach(host -> {
            String[] hostAndPort = host.split(":");
            if (hostAndPort.length != 2) {
              throw new IllegalArgumentException("Invalid failover host found in url");
            }
            failoverHosts.put(hostAndPort[0], hostAndPort[1]);
          });
    }

    return failoverHosts;
  }

  private Map<String, String> getOptionalProperties(String params) {
    Map<String, String> properties = new HashMap<>();

    for (String property : params.split("&")) {
      String[] propertyNameAndValue = property.split("=");
      if (propertyNameAndValue.length != 2) {
        throw new IllegalArgumentException("Parameters are invalid in url");
      }

      String key = propertyNameAndValue[0];
      String value = propertyNameAndValue[1];
      properties.put(key, value);
    }

    return properties;
  }
}
