package akz.eureka;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.mockStatic;

@SpringBootTest
@DisplayName("MS Eureka Application Test")
class MsEurekaApplicationTests {

	@Test
  @DisplayName("Context load")
	void contextLoads() {
    try (MockedStatic<SpringApplication> mockedSpringApplication = mockStatic(SpringApplication.class)) {
      String [] args = {};
      MsEurekaApplication.main(args);
      mockedSpringApplication.verify(() -> SpringApplication.run(MsEurekaApplication.class, args));
    }
	}

}
