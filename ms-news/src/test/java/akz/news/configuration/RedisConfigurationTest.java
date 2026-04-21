package akz.news.configuration;

import akz.news.utils.Util;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
  classes = RedisConfiguration.class,
  properties = {
    "spring.data.redis.host=localhost",
    "spring.data.redis.port=6379",
    "spring.cache.redis.time-to-live=30M"
  }
)
@DisplayName("Redis Configuration Test")
class RedisConfigurationTest {

  @Value("${spring.data.redis.host}")
  private String host;
  @Value("${spring.data.redis.port}")
  private Integer port;
  @Value("${spring.cache.redis.time-to-live}")
  private String timeToLive;

  @Autowired
  private JedisConnectionFactory jedisConnectionFactory;

  @Autowired
  private CacheManager cacheManager;

  @Test
  @DisplayName("Jedis Connection Factory Creation")
  void jedisConnectionFactoryIsCreated() {
    assertAll("Asserts for Jedis Connection Factory",
      () -> assertNotNull(jedisConnectionFactory, "This bean not must be null"),
      () -> assertEquals(host, jedisConnectionFactory.getHostName(), "The hostname is incorrect"),
      () -> assertEquals(port, jedisConnectionFactory.getPort(), "The port is incorrect"),
      () -> assertEquals(Duration.ofMillis(1800000), Util.getTimeToLive(timeToLive))
    );
  }

  @Test
  @DisplayName("Cache Manager Is Creation")
  void cacheManagerIsCreated() {
    assertAll("Asserts for Manager Cache Bean",
      () -> assertNotNull(cacheManager, "This cache manager bean not must be null"),
      () -> assertTrue(cacheManager.getCacheNames().isEmpty(), "There shouldn't be any cache yet.")
    );
  }
}
