package akz.news.web.service.impl;

import akz.news.remote.dto.EverythingResponse;
import akz.news.remote.dto.TopHeadlineResponse;
import akz.news.remote.service.NewsRtoService;
import akz.news.web.dto.REverythingResponse;
import akz.news.web.dto.RSourcesResponse;
import akz.news.web.dto.RTopHeadlinesResponse;
import akz.news.web.service.INewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class NewsService implements INewsService {

  private final NewsRtoService newsRtoService;

  @Override
  public REverythingResponse getEverything(Map<String, String> params) {
    EverythingResponse response = newsRtoService.getEverything(params);
    return new REverythingResponse(response.getTotalResults(), response.getArticles());
  }

  @Override
  public RTopHeadlinesResponse getTopHeadlines(Map<String, String> params) {
    TopHeadlineResponse response = newsRtoService.getTopHeadlines(params);
    return new RTopHeadlinesResponse(response.getTotalResults(), response.getArticles());
  }

  @Override
  public RSourcesResponse getSources(Map<String, String> params) {
    return new RSourcesResponse(newsRtoService.getSources(params).getSources());
  }
}
