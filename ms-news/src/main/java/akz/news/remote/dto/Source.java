package akz.news.remote.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Source implements Serializable {
	private String name;
	private String id;
  private String country;
  private String description;
  private String language;
  private String category;
  private String url;
}