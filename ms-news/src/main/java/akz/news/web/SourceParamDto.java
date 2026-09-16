package akz.news.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Map;

@Data
@Schema(description = "Parameters for fetching news articles")
public class SourceParamDto {

  private String category;
  private String language;
  private String country;

  public static SourceParamDto fromMap(Map<String, String> mapParams) {
    SourceParamDto dto = new SourceParamDto();
    dto.setCountry(mapParams.get("country"));
    dto.setLanguage(mapParams.get("language"));
    dto.setCategory(mapParams.get("category"));
    return dto;
  }
}
