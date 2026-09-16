package akz.news.web.controller;

import akz.news.remote.dto.ArticlesItem;
import akz.news.utils.Constants;
import akz.news.utils.enums.EError;
import akz.news.web.service.INewsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

import static akz.news.utils.TestDataUtils.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NewsController.class)
@DisplayName("News Controller Test")
class NewsControllerTest {

  private static final String EVERYTHING_PATH = "/news/everything";
  private static final String TOP_HEADLINES_PATH = "/news/top-headlines";
  private static final String SOURCES_PATH = "/news/top-headlines/sources";

  @MockitoBean
  private INewsService serviceMock;
  @Autowired
  private MockMvc mockMvc;

  @AfterEach
  void tearDown() {
    reset(serviceMock);
  }

  @Test
  @DisplayName("Get Everything News")
  void getEverything() throws Exception {
    when(serviceMock.getEverything(any())).thenReturn(SUCCESS_RECORD_EVERYTHING_RESPONSE);

    mockMvc.perform(get(EVERYTHING_PATH).param("query", "bitcoin"))
      .andExpectAll(this.newsSuccessResponseMatchers(
        SUCCESS_EVERYTHING_RESPONSE.getTotalResults(), SUCCESS_EVERYTHING_RESPONSE.getArticles()));

    mockMvc.perform(get(EVERYTHING_PATH))
      .andExpectAll(this.newsErrorResponseMatchers(HttpStatus.BAD_REQUEST, EError.REQUIRED_PARAM_EVERYTHING));

    when(serviceMock.getEverything(any())).thenThrow(new NullPointerException(EError.TESTING_MESSAGE.getMessage()));

    mockMvc.perform(get(EVERYTHING_PATH).param("query", "bitcoin"))
      .andExpectAll(this.newsErrorResponseMatchers(HttpStatus.INTERNAL_SERVER_ERROR, EError.TESTING_MESSAGE));
  }

  @Test
  @DisplayName("Get Top Headlines News")
  void getTopHeadlines() throws Exception {
    when(serviceMock.getTopHeadlines(any())).thenReturn(SUCCESS_RECORD_TOP_HEADLINES_RESPONSE);

    mockMvc.perform(get(TOP_HEADLINES_PATH).param("country", "US"))
      .andExpectAll(this.newsSuccessResponseMatchers(
        SUCCESS_TOP_HEADLINES_RESPONSE.getTotalResults(), SUCCESS_TOP_HEADLINES_RESPONSE.getArticles()));

    mockMvc.perform(get(TOP_HEADLINES_PATH))
      .andExpectAll(this.newsErrorResponseMatchers(HttpStatus.BAD_REQUEST, EError.REQUIRED_PARAM_TOP_HEADLINE));

    when(serviceMock.getTopHeadlines(any())).thenThrow(new NullPointerException(EError.TESTING_MESSAGE.getMessage()));

    mockMvc.perform(get(TOP_HEADLINES_PATH).param("country", "US"))
      .andExpectAll(this.newsErrorResponseMatchers(HttpStatus.INTERNAL_SERVER_ERROR, EError.TESTING_MESSAGE));
  }

  @Test
  @DisplayName("Get Top Headlines Sources")
  void getSources() throws Exception {
    when(serviceMock.getSources(any())).thenReturn(SUCCESS_RECORD_SOURCES_RESPONSE);

    mockMvc.perform(get(SOURCES_PATH).param("category", "business"))
      .andExpectAll(this.sourceResponseMatchers());

    when(serviceMock.getSources(any())).thenThrow(new NullPointerException(EError.TESTING_MESSAGE.getMessage()));

    mockMvc.perform(get(SOURCES_PATH).param("category", "business"))
      .andExpectAll(this.newsErrorResponseMatchers(HttpStatus.INTERNAL_SERVER_ERROR, EError.TESTING_MESSAGE));
  }

  private ResultMatcher[] newsSuccessResponseMatchers(int totalResults, List<ArticlesItem> articles) {
    return new ResultMatcher[] {
      status().isOk(),
      content().contentType(MediaType.APPLICATION_JSON),
      jsonPath("$.status").value(Constants.SUCCESS_STATUS_RESPONSE),
      jsonPath("$.result.totalResults").value(totalResults),
      jsonPath("$.result.articles[0]").value(articles.get(0)),
      jsonPath("$.result.articles[1]").value(articles.get(1))
    };
  }

  private ResultMatcher[] sourceResponseMatchers() {
    return new ResultMatcher[] {
      status().isOk(),
      content().contentType(MediaType.APPLICATION_JSON),
      jsonPath("$.status").value(Constants.SUCCESS_STATUS_RESPONSE),
      jsonPath("$.result.sources[0]").value(SUCCESS_SOURCES_RESPONSE.getSources().get(0)),
      jsonPath("$.result.sources[1]").value(SUCCESS_SOURCES_RESPONSE.getSources().get(1)),
      jsonPath("$.result.sources[2]").value(SUCCESS_SOURCES_RESPONSE.getSources().get(2)),
    };
  }

  private ResultMatcher[] newsErrorResponseMatchers(HttpStatus httpStatus, EError eError) {
    return new ResultMatcher[] {
      MAP_STATUS_MATCHER.get(httpStatus),
      content().contentType(MediaType.APPLICATION_JSON),
      jsonPath("$.status").value(Constants.ERROR_STATUS_RESPONSE),
      jsonPath("$.message").value(eError.getMessage())
    };
  }
}