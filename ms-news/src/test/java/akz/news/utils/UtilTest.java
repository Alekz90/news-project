package akz.news.utils;

import akz.news.exception.CustomException;
import akz.news.utils.enums.EError;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.HttpStatus;

import java.time.Duration;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Util Tests")
class UtilTest {

  @ParameterizedTest
  @ValueSource(strings = {"1d", "24h", "1440m", "86400s", "1D", "24H", "1440M", "86400S"})
  void getSuccessTimeToLive(String timeToLive) {
    Duration duration = Util.getTimeToLive(timeToLive);
    assertEquals(Duration.ofMillis(86400000), duration, "The duration isn't correct");
  }

  @ParameterizedTest
  @ValueSource(strings = {"86400", "12x", "a15s", "", "adf"})
  void getErrorTimeToLive(String timeToLive) {
    CustomException errorException = assertThrows(CustomException.class, () -> Util.getTimeToLive(timeToLive));
    assertEquals(HttpStatus.CONFLICT, errorException.getStatus(), "This http status is incorrect.");
    assertEquals(EError.TIME_TO_LIVE_BAD_FORMAT.getId(), errorException.getId(), "This error id is incorrect.");
    assertEquals(EError.TIME_TO_LIVE_BAD_FORMAT.getMessage(), errorException.getMessage(), "This message is incorrect.");
  }

  @ParameterizedTest
  @CsvSource({
    "query, bitcoin",
    "searchIn, crypto",
    "sources, hk",
    "domains, techcrunch.com"
  })
  @DisplayName("Everything required parameters validation")
  void validateEverythingRequiredParameters(String key, String value) {
    assertDoesNotThrow(() -> Util.validateEverythingRequiredParameters(Map.of(key, value)));
  }

  @ParameterizedTest
  @CsvSource({
    "query, bitcoin",
    "country, us",
    "sources, hk",
    "category, business"
  })
  @DisplayName("Top headlines required parameters validation")
  void validateTopHeadlinesRequiredParameters(String key, String value) {
    assertDoesNotThrow(() -> Util.validateTopHeadlinesRequiredParameters(Map.of(key, value)));
  }
}