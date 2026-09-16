package akz.news.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static akz.news.utils.Constants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest(
  classes = FeignConfiguration.class,
  properties = {
    "web-service-news.api.key=test-api-key"
  }
)
@DisplayName("Feign Configuration Tests")
class FeignConfigurationTest {

  @Value("${web-service-news.api.key}")
  private String apiKey;

  @Autowired
  private RequestInterceptor interceptor;

  @Test
  @DisplayName("Interceptor header adds")
  void interceptorAddsHeaders() {
    RequestTemplate template = new RequestTemplate();
    interceptor.apply(template);

    assertAll("Asserts of interceptor headers",
      () -> assertThat(template.headers().get(X_API_KEY_HEADER)).containsExactly(apiKey),
      () -> assertThat(template.headers().get(USER_AGENT_HEADER_NAME)).containsExactly(USER_AGENT_HEADER_VALUE)
    );
  }
}
