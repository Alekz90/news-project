package akz.news.web.controller;

import akz.news.exception.CustomException;
import akz.news.utils.DocumentationConstants;
import akz.news.utils.Util;
import akz.news.web.dto.REverythingResponse;
import akz.news.web.dto.RSourcesResponse;
import akz.news.web.dto.RTopHeadlinesResponse;
import akz.news.web.dto.ResponseDto;
import akz.news.web.service.INewsService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/news")
@Tag(name = "NewsController", description = "Controller for fetching news articles based on various parameters")
@RequiredArgsConstructor
public class NewsController {

  private final INewsService service;

  @GetMapping("/everything")
  public ResponseEntity<ResponseDto<REverythingResponse>> getEverything(
      @Schema(example = "bitcoin", description = DocumentationConstants.QUERY_PARAMETER_DESCRIPTION)
      @RequestParam(required = false) String query,
      @Schema(example = "crypto", description = DocumentationConstants.SEARCH_IN_PARAMETER_DESCRIPTION)
      @RequestParam(required = false) String searchIn,
      @Schema(example = "hk", description = DocumentationConstants.SOURCES_PARAMETER_DESCRIPTION)
      @RequestParam(required = false) String sources,
      @Schema(example = "techcrunch.com,thenextweb.com", description = DocumentationConstants.DOMAINS_PARAMETER_DESCRIPTION)
      @RequestParam(required = false) String domains,
      @Schema(example = "thenextweb.com", description = DocumentationConstants.EXCLUDE_DOMAINS_PARAMETER_DESCRIPTION)
      @RequestParam(required = false) String excludeDomains,
      @Schema(example = "2026-03-10", description = DocumentationConstants.FROM_PARAMETER_DESCRIPTION)
      @RequestParam(required = false) String from,
      @Schema(example = "2026-03-18", description = DocumentationConstants.TO_PARAMETER_DESCRIPTION)
      @RequestParam(required = false) String to,
      @Schema(example = "fr", description = DocumentationConstants.LANGUAGE_PARAMETER_DESCRIPTION)
      @RequestParam(required = false) String language,
      @Schema(example = "publishedAt", description = DocumentationConstants.SORT_BY_PARAMETER_DESCRIPTION)
      @RequestParam(required = false) String sortBy,
      @Schema(example = "100", description = DocumentationConstants.PAGE_SIZE_PARAMETER_DESCRIPTION)
      @RequestParam(required = false) String pageSize,
      @Schema(example = "1", description = DocumentationConstants.PAGE_PARAMETER_DESCRIPTION)
      @RequestParam(required = false) String page
  ) {
    try {
      Map<String, String> params = new HashMap<>();
      Util.putMapParams(params,"query", query);
      Util.putMapParams(params,"searchIn", searchIn);
      Util.putMapParams(params,"sources", sources);
      Util.putMapParams(params,"domains", domains);
      Util.putMapParams(params,"excludeDomains", excludeDomains);
      Util.putMapParams(params,"from", from);
      Util.putMapParams(params,"to", to);
      Util.putMapParams(params,"language", language);
      Util.putMapParams(params,"sortBy", sortBy);
      Util.putMapParams(params,"pageSize", pageSize);
      Util.putMapParams(params,"page", page);
      Util.validateEverythingRequiredParameters(params);
      return ResponseEntity.ok(new ResponseDto<>(service.getEverything(params)));
    } catch (CustomException ex) {
      return this.getErrorResponseEntity(ex);
    } catch (Exception ex) {
      return this.getErrorResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }
  }

  @GetMapping("/top-headlines")
  public ResponseEntity<ResponseDto<RTopHeadlinesResponse>> getTopHeadlines(
      @Schema(example = "bitcoin", description = DocumentationConstants.QUERY_TOP_HEADLINES_PARAMETER_DESCRIPTION)
      @RequestParam(required = false) String query,
      @Schema(example = "hk", description = DocumentationConstants.SOURCES_TOP_HEADLINES_PARAMETER_DESCRIPTION)
      @RequestParam(required = false) String sources,
      @Schema(example = "us", description = DocumentationConstants.COUNTRY_TOP_HEADLINES_PARAMETER_DESCRIPTION)
      @RequestParam(required = false) String country,
      @Schema(example = "business", description = DocumentationConstants.CATEGORY_TOP_HEADLINES_PARAMETER_DESCRIPTION)
      @RequestParam(required = false) String category,
      @Schema(example = "100", description = DocumentationConstants.PAGE_SIZE_TOP_HEADLINES_PARAMETER_DESCRIPTION)
      @RequestParam(required = false) String pageSize,
      @Schema(example = "1", description = DocumentationConstants.PAGE_TOP_HEADLINES_PARAMETER_DESCRIPTION)
      @RequestParam(required = false) String page
  ) {
    try {
      Map<String, String> params = new HashMap<>();
      Util.putMapParams(params,"query", query);
      Util.putMapParams(params,"sources", sources);
      Util.putMapParams(params,"country", country);
      Util.putMapParams(params,"category", category);
      Util.putMapParams(params,"pageSize", pageSize);
      Util.putMapParams(params,"page", page);
      Util.validateTopHeadlinesRequiredParameters(params);
      return ResponseEntity.ok(new ResponseDto<>(service.getTopHeadlines(params)));
    } catch (CustomException ex) {
      return this.getErrorResponseEntity(ex);
    } catch (Exception ex) {
      return this.getErrorResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }
  }

  @GetMapping("top-headlines/sources")
  public ResponseEntity<ResponseDto<RSourcesResponse>> getSources(
      @Schema(example = "entertainment", description = DocumentationConstants.CATEGORY_SOURCE_PARAMETER_DESCRIPTION)
      @RequestParam(required = false) String category,
      @Schema(example = "it", description = DocumentationConstants.LANGUAGE_SOURCE_HEADLINES_PARAMETER_DESCRIPTION)
      @RequestParam(required = false) String language,
      @Schema(example = "mx", description = DocumentationConstants.COUNTRY_SOURCE_HEADLINES_PARAMETER_DESCRIPTION)
      @RequestParam(required = false) String country
  ) {
    try {
      Map<String, String> params = new HashMap<>();
      Util.putMapParams(params,"country", country);
      Util.putMapParams(params,"category", category);
      Util.putMapParams(params,"language", language);
      return ResponseEntity.ok(new ResponseDto<>(service.getSources(params)));
    } catch (Exception ex) {
      return this.getErrorResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }
  }

  private <T extends Serializable> ResponseEntity<ResponseDto<T>> getErrorResponseEntity(CustomException ex) {
    return this.getErrorResponseEntity(ex.getStatus(), ex);
  }

  private <T extends Serializable> ResponseEntity<ResponseDto<T>> getErrorResponseEntity(HttpStatus httpStatus, Exception ex) {
    return HttpStatus.BAD_REQUEST == httpStatus
      ? ResponseEntity.badRequest().body(new ResponseDto<>(ex))
      : ResponseEntity.internalServerError().body(new ResponseDto<>(ex));
  }
}
