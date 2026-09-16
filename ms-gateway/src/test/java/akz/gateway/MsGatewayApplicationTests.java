package akz.gateway;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static akz.gateway.util.Constants.TEST_PROFILE;
import static org.mockito.Mockito.mockStatic;
@ActiveProfiles(TEST_PROFILE)
@SpringBootTest
@DisplayName("MS Gateway Application Test")
class MsGatewayApplicationTests {

	@Test
  @DisplayName("Context load")
	void contextLoads() {
    try (MockedStatic<SpringApplication> mockedStatic = mockStatic(SpringApplication.class)) {
      String [] args = {};
      MsGatewayApplication.main(args);
      mockedStatic.verify(() -> SpringApplication.run(MsGatewayApplication.class, args));
    }
	}
}
