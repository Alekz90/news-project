package akz.news.remote.dto;

import akz.news.remote.dto.abstracts.AbstractErrorResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SourceResponse extends AbstractErrorResponse implements Serializable {

	private List<Source> sources;

  @Builder
  public SourceResponse(String status, String message, List<Source> sources) {
    super(status, message);
    this.sources = sources;
  }
}