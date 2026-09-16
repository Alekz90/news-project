package akz.news.web;

import akz.news.utils.DocumentationConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Map;

@Data
@Schema(description = "Parameters for fetching news articles")
public class TopHeadlineParamDto {

  private String country;
  private String category;
  private String sources;
  private String q;
  private String pageSize;
  private String page;

  public static TopHeadlineParamDto fromMap(Map<String, String> mapParams) {
    TopHeadlineParamDto dto = new TopHeadlineParamDto();
    dto.setCountry(mapParams.get("country"));
    dto.setCategory(mapParams.get("category"));
    dto.setSources(mapParams.get("sources"));
    dto.setQ(mapParams.get("query"));
    dto.setPageSize(mapParams.get("pageSize"));
    dto.setPage(mapParams.get("page"));
    return dto;
  }
}
