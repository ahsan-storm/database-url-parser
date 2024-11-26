import org.example.config.DatabaseConfig;
import org.example.factory.DatabaseURLParserFactory;
import org.example.util.SupportedDatabase;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class MySQLParserTest {
  @Test
  public void test_mysql_connection_url_is_parsed_correctly() {
    String URL = "jdbc:mysql://localhost:3306,localhost2:3306/testdb?param1=value1&param2=value2&param3=value3";

    DatabaseConfig mySQLConfig = DatabaseURLParserFactory.getParser(SupportedDatabase.MYSQL.getValue()).parseURL(URL);

    assertThat(mySQLConfig.getHost()).isEqualTo("localhost");
    assertThat(mySQLConfig.getPort()).isEqualTo(3306);
    assertThat(mySQLConfig.getDatabase()).isEqualTo("testdb");
    assertThat(mySQLConfig.getFailoverHosts()).containsExactlyEntriesOf((Map.of("localhost2", "3306")));
    assertThat(mySQLConfig.getProperties()).containsExactlyInAnyOrderEntriesOf((Map.of(
        "param1", "value1",
        "param2", "value2",
        "param3", "value3"
    )));
  }
}
