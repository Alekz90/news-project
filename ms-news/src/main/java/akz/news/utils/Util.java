package akz.news.utils;

import akz.news.exception.CustomException;
import akz.news.utils.enums.EError;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.time.Duration;
import java.util.Map;

public final class Util {

  private Util() {}

  public static void validateEverythingRequiredParameters(Map<String, String> params) {
    if (StringUtils.isBlank(params.get("query")) && StringUtils.isBlank(params.get("searchIn"))
        && StringUtils.isBlank(params.get("sources")) && StringUtils.isBlank(params.get("domains"))) {
      throw new CustomException(HttpStatus.BAD_REQUEST, EError.REQUIRED_PARAM_EVERYTHING);
    }
  }

  public static void validateTopHeadlinesRequiredParameters(Map<String, String> params) {
    if (StringUtils.isBlank(params.get("country")) && StringUtils.isBlank(params.get("category"))
        && StringUtils.isBlank(params.get("sources")) && StringUtils.isBlank(params.get("query"))) {
      throw new CustomException(HttpStatus.BAD_REQUEST, EError.REQUIRED_PARAM_TOP_HEADLINE);
    }
  }

  public static void putMapParams(Map<String, String> params, String key, String value) {
    if (StringUtils.isNotBlank(value)) {
      params.put(key, value);
    }
  }

  public static Duration getTimeToLive(String timeToLive) {
    if (!timeToLive.matches(Constants.TIME_TO_LIVE_PATTERN)) {
      throw new CustomException(HttpStatus.CONFLICT, EError.TIME_TO_LIVE_BAD_FORMAT);
    }
    String durationType = timeToLive.substring(timeToLive.length() - 1);
    int time = Integer.parseInt(timeToLive.substring(0, timeToLive.length() - 1));
    return switch (durationType.toLowerCase()) {
      case "d" -> Duration.ofDays(time);
      case "h" -> Duration.ofHours(time);
      case "m" -> Duration.ofMinutes(time);
      default -> Duration.ofSeconds(time);
    };
  }
}
