package akz.news.web.service.impl;

import akz.news.exception.CustomException;
import akz.news.remote.repository.INewsRtoRepository;
import akz.news.utils.enums.EError;
import akz.news.web.dto.REverythingResponse;
import akz.news.web.dto.RSourcesResponse;
import akz.news.web.dto.RTopHeadlinesResponse;
import akz.news.web.service.INewsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.data.redis.autoconfigure.DataRedisAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Map;

import static akz.news.utils.Constants.TEST_PROFILE;
import static akz.news.utils.TestDataUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles(TEST_PROFILE)
@DisplayName("News Service Test")
@ImportAutoConfiguration(exclude = { DataRedisAutoConfiguration.class })
class NewsServiceTest {

  @MockitoBean
  private INewsRtoRepository repositoryMock;

  @Autowired
  private INewsService service;

  @AfterEach
  void tearDown() {
    reset(repositoryMock);
  }

  @Test
  @DisplayName("Get all news")
  void getEverything() {
    Map<String, String> params = Map.of("query", "bitcoin");

    when(repositoryMock.getEverything(any())).thenReturn(SUCCESS_EVERYTHING_RESPONSE);
    REverythingResponse successResponse = service.getEverything(params);
    assertAll("Success Everything news assertions",
      () -> assertNotNull(successResponse, "The response can't be null"),
      () -> assertNotNull(successResponse.articles(), "The articles can't be null"),
      () -> assertEquals(2, successResponse.articles().size(), "The first article isn't correct"),
      () -> assertEquals(
        SUCCESS_EVERYTHING_RESPONSE.getArticles().getFirst(),
        successResponse.articles().getFirst(),
        "The first item of articles isn't correct"
      ), () -> assertEquals(
        SUCCESS_EVERYTHING_RESPONSE.getArticles().getLast(),
        successResponse.articles().getLast(),
        "The first item of articles isn't correct"
      )
    );

    when(repositoryMock.getEverything(any())).thenThrow(new RuntimeException(EError.TESTING_MESSAGE.getMessage()));
    assertThrows(CustomException.class, () -> service.getEverything(params));

    verify(repositoryMock, times(2)).getEverything(any());
  }

  @Test
  @DisplayName("Get top headlines")
  void getTopHeadlines() {
    Map<String, String> params = Map.of("country", "US");

    when(repositoryMock.getTopHeadlines(any())).thenReturn(SUCCESS_TOP_HEADLINES_RESPONSE);
    RTopHeadlinesResponse successResponse = service.getTopHeadlines(params);
    assertAll("Success Top headlines news assertions",
      () -> assertNotNull(successResponse, "The response can't be null"),
      () -> assertNotNull(successResponse.articles(), "The articles can't be null"),
      () -> assertEquals(2, successResponse.articles().size(), "The size of articles isn't correct"),
      () -> assertEquals(SUCCESS_TOP_HEADLINES_RESPONSE.getArticles().getFirst(), successResponse.articles().getFirst(), "The first article isn't correct")
    );

    when(repositoryMock.getTopHeadlines(any())).thenThrow(new RuntimeException(EError.TESTING_MESSAGE.getMessage()));
    assertThrows(CustomException.class, () -> service.getTopHeadlines(params));

    verify(repositoryMock, times(2)).getTopHeadlines(any());
  }

  @Test
  @DisplayName("Get sources")
  void getSources() {
    Map<String, String> params = Map.of("category", "business");

    when(repositoryMock.getSources(any())).thenReturn(SUCCESS_SOURCES_RESPONSE);
    RSourcesResponse successResponse = service.getSources(params);
    assertAll("Success Sources assertions",
      () -> assertNotNull(successResponse, "The response can't be null"),
      () -> assertEquals(3, successResponse.sources().size(), "The size of sources isn't correct"),
      () -> assertEquals(SUCCESS_SOURCES_RESPONSE.getSources().getFirst(), successResponse.sources().getFirst(), "The first source isn't correct")
    );

    when(repositoryMock.getSources(any())).thenThrow(new RuntimeException(EError.TESTING_MESSAGE.getMessage()));
    assertThrows(CustomException.class, () -> service.getSources(params));

    verify(repositoryMock, times(2)).getSources(any());
  }
}