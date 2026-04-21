package akz.news.remote.dto;

import akz.news.remote.dto.abstracts.AbstractNewsResponse;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class EverythingResponse extends AbstractNewsResponse {

  @Builder
  public EverythingResponse(int totalResults, List<ArticlesItem> articles, String status, String message) {
    super(totalResults, articles, status, message);
  }
}