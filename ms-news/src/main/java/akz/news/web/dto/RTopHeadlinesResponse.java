package akz.news.web.dto;

import akz.news.remote.dto.ArticlesItem;

import java.io.Serializable;
import java.util.List;

public record RTopHeadlinesResponse(int totalResults, List<ArticlesItem> articles) implements Serializable {
}
