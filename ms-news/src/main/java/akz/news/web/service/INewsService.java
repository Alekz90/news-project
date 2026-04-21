package akz.news.web.service;

import akz.news.web.dto.REverythingResponse;
import akz.news.web.dto.RSourcesResponse;
import akz.news.web.dto.RTopHeadlinesResponse;

import java.util.Map;

public interface INewsService {
  REverythingResponse getEverything(Map<String, String> params);
  RTopHeadlinesResponse getTopHeadlines(Map<String, String> params);
  RSourcesResponse getSources(Map<String, String> params);
}
