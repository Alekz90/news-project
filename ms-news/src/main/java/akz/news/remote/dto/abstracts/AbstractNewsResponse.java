package akz.news.remote.dto.abstracts;

import akz.news.remote.dto.ArticlesItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractNewsResponse extends AbstractErrorResponse {

  private int totalResults;
  private List<ArticlesItem> articles;

  protected AbstractNewsResponse(int totalResults, List<ArticlesItem> articles, String status, String message) {
    super(status, message);
    this.totalResults = totalResults;
    this.articles = articles;
  }
}
