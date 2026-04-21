package akz.news;

import akz.news.remote.repository.INewsRtoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.data.redis.autoconfigure.DataRedisAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static akz.news.utils.Constants.TEST_PROFILE;
import static org.mockito.Mockito.mockStatic;

@SpringBootTest
@ActiveProfiles(TEST_PROFILE)
@DisplayName("MS News Application Test")
@ImportAutoConfiguration(exclude = { DataRedisAutoConfiguration.class, FeignAutoConfiguration.class })
class MsNewsApplicationTests {

	@Test
  @DisplayName("Context load")
	void contextLoads() {
    try (MockedStatic<SpringApplication> mockedSpringApplication = mockStatic(SpringApplication.class)) {
      String[] args = {};
      MsNewsApplication.main(args);
      mockedSpringApplication.verify(() -> SpringApplication.run(MsNewsApplication.class, args));
    }
	}

}
