package akz.news.utils;

public final class Constants {

  private Constants(){}

  public static final String X_API_KEY_HEADER = "X-Api-Key";
  public static final String USER_AGENT_HEADER_NAME = "User-Agent";
  public static final String USER_AGENT_HEADER_VALUE = "NewsApp/1.0 (alejdaf@outlook.com)";
  public static final String CACHING_TOP_HEADLINES = "top-headlines";
  public static final String CACHING_EVERYTHING_NEWS = "everything-news";
  public static final String CACHING_SOURCES = "sources";
  public static final String TEST_PROFILE = "test";
  public static final String NOT_TEST_PROFILE = "!test";
  public static final String SUCCESS_STATUS_RESPONSE = "ok";
  public static final String ERROR_STATUS_RESPONSE = "error";
  public static final String TIME_TO_LIVE_PATTERN = "\\d+[DdHhMmSs]{1}";
}
