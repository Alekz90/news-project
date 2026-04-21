package akz.news.web.dto;

import akz.news.remote.dto.abstracts.AbstractErrorResponse;
import akz.news.utils.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class ResponseDto<T extends Serializable> extends AbstractErrorResponse {

  private T result;

  public ResponseDto(T result) {
    super(Constants.SUCCESS_STATUS_RESPONSE, StringUtils.EMPTY);
    this.result = result;
  }

  public ResponseDto(Exception exception) {
    super(Constants.ERROR_STATUS_RESPONSE, exception.getMessage());
  }
}
