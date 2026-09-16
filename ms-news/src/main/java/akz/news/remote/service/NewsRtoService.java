package akz.news.remote.service;

import akz.news.exception.CustomException;
import akz.news.remote.dto.EverythingResponse;
import akz.news.remote.dto.SourceResponse;
import akz.news.remote.dto.TopHeadlineResponse;
import akz.news.remote.repository.INewsRtoRepository;
import akz.news.web.EverythingParamDto;
import akz.news.web.SourceParamDto;
import akz.news.web.TopHeadlineParamDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

import static akz.news.utils.Constants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsRtoService {

  private static final String NEWS_INSTANCE = "news-instance";
  private final INewsRtoRepository repository;

  @Cacheable(cacheNames = CACHING_EVERYTHING_NEWS, key = "T(String).join(\";\", #params.values())")
  @CircuitBreaker(name = NEWS_INSTANCE, fallbackMethod = "failGetEverything")
  public EverythingResponse getEverything(Map<String, String> params) {
    log.info("Get Everything news Key: {}", String.join(";", params.values()));
    return repository.getEverything(EverythingParamDto.fromMap(params));
  }

  EverythingResponse failGetEverything(Map<String, String> params, Throwable t) {
    log.error("Failed to get everything with params: {}, error: {}", params.toString(), t.getMessage());
    throw new CustomException(t.getMessage());
  }

  @Cacheable(cacheNames = CACHING_TOP_HEADLINES, key = "T(String).join(\";\", #params.values())")
  @CircuitBreaker(name = NEWS_INSTANCE, fallbackMethod = "failGetTopHeadlines")
  public TopHeadlineResponse getTopHeadlines(Map<String, String> params) {
    log.info("Get Top Headlines news Key: {}", String.join(";", params.values()));
    return repository.getTopHeadlines(TopHeadlineParamDto.fromMap(params));
  }

  TopHeadlineResponse failGetTopHeadlines(Map<String, String> params, Throwable t) {
    log.error("Failed to get top headlines with params: {}, error: {}", params.toString(), t.getMessage());
    throw new CustomException(t.getMessage());
  }

  @Cacheable(cacheNames = CACHING_SOURCES, key = "T(String).join(\";\", #params.values())")
  @CircuitBreaker(name = NEWS_INSTANCE, fallbackMethod = "failGetSources")
  public SourceResponse getSources(Map<String, String> params) {
    log.info("Get Sources Key: {}", String.join(";", params.values()));
    return repository.getSources(SourceParamDto.fromMap(params));
  }

  SourceResponse failGetSources(Map<String, String> params, Exception t) {
    log.error("Failed to get sources with params: {}, error: {}", params.toString(), t.getMessage());
    throw new CustomException(t.getMessage());
  }
}
