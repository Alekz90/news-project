package akz.news.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Map;

@Data
@Schema(description = "Parameters for fetching news articles")
public class EverythingParamDto {

  private String q;
  private String searchIn;
  private String sources;
  private String domains;
  private String excludeDomains;
  private String from;
  private String to;
  private String language;
  private String sortBy;
  private String pageSize;
  private String page;

  public static EverythingParamDto fromMap(Map<String, String> mapParams) {
    EverythingParamDto dto = new EverythingParamDto();
    dto.setQ(mapParams.get("query"));
    dto.setSearchIn(mapParams.get("searchIn"));
    dto.setSources(mapParams.get("sources"));
    dto.setDomains(mapParams.get("domains"));
    dto.setExcludeDomains(mapParams.get("excludeDomains"));
    dto.setFrom(mapParams.get("from"));
    dto.setTo(mapParams.get("to"));
    dto.setLanguage(mapParams.get("language"));
    dto.setSortBy(mapParams.get("sortBy"));
    dto.setPageSize(mapParams.get("pageSize"));
    dto.setPage(mapParams.get("page"));
    return dto;
  }
}
