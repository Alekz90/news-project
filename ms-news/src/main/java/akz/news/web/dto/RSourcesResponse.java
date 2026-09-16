package akz.news.web.dto;

import akz.news.remote.dto.Source;

import java.io.Serializable;
import java.util.List;

public record RSourcesResponse(List<Source> sources) implements Serializable {
}
