package akz.news.utils;

import akz.news.remote.dto.*;
import akz.news.web.dto.REverythingResponse;
import akz.news.web.dto.RSourcesResponse;
import akz.news.web.dto.RTopHeadlinesResponse;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Map;

import static akz.news.utils.Constants.SUCCESS_STATUS_RESPONSE;

public final class TestDataUtils {

  public static final EverythingResponse SUCCESS_EVERYTHING_RESPONSE = fillSuccessEverythingResponse();
  public static final TopHeadlineResponse SUCCESS_TOP_HEADLINES_RESPONSE = fillSuccessHeadlinesResponse();
  public static final SourceResponse SUCCESS_SOURCES_RESPONSE = fillSourcesResponse();
  public static final REverythingResponse SUCCESS_RECORD_EVERYTHING_RESPONSE = fillREverythingResponse();
  public static final RTopHeadlinesResponse SUCCESS_RECORD_TOP_HEADLINES_RESPONSE = fillRTopHeadlinesResponse();
  public static final RSourcesResponse SUCCESS_RECORD_SOURCES_RESPONSE = fillRSourcesResponse();
  public static final Map<HttpStatus, ResultMatcher> MAP_STATUS_MATCHER = Map.of(
    HttpStatus.NOT_FOUND, MockMvcResultMatchers.status().isNotFound(),
    HttpStatus.BAD_REQUEST, MockMvcResultMatchers.status().isBadRequest(),
    HttpStatus.CONFLICT, MockMvcResultMatchers.status().isConflict(),
    HttpStatus.CREATED, MockMvcResultMatchers.status().isCreated(),
    HttpStatus.OK, MockMvcResultMatchers.status().isOk(),
    HttpStatus.INTERNAL_SERVER_ERROR, MockMvcResultMatchers.status().isInternalServerError()
  );

  private static EverythingResponse fillSuccessEverythingResponse() {
    return EverythingResponse.builder()
      .totalResults(2)
      .status(SUCCESS_STATUS_RESPONSE)
      .articles(List.of(
        ArticlesItem.builder()
          .author("Kyle Torpey")
          .title("Bitcoin Crashed 50% in 4 Months. Fidelity Says That’s a Good Thing")
          .description("Fidelity Digital Assets analysts do see one ray of hope for bitcoin maxis.")
          .url("https://gizmodo.com/bitcoin-crashed-50-in-4-months-fidelity-says-thats-a-good-thing-2000727284")
          .urlToImage("https://gizmodo.com/app/uploads/2025/10/bitcoin-explosion-1200x675.jpg")
          .publishedAt("2026-02-27T15:30:11Z")
          .content("Bitcoin’s price has taken a beating recently. After reaching a new all-time high above $126,000 in October, the crypto asset fell below $60,000 earlier this month before recovering to trade around $6… [+5115 chars]")
          .source(
              Source.builder()
                  .name("Gizmodo.com")
                  .build())
          .build(),
        ArticlesItem.builder()
          .author("David Morris")
          .title("Jeffrey Epstein saw promise in Bitcoin — and its far-right supporters")
          .description("The tranche of Jeffrey Epstein emails and files released on January 30th tie the infamous pedophile, sex trafficker, and influence peddler to elite figures across the tech industry. The world of cryptocurrency is no exception. Epstein's connections are intrig…")
          .url("https://www.theverge.com/tech/885252/jeffrey-epstein-bitcoin-cryptocurrency-connections")
          .urlToImage("https://platform.theverge.com/wp-content/uploads/sites/2/2026/02/268350_Did_Jeffrey_Epstein_shape_bitcoin_or_not_understand_it_at_all__CVirginia.jpg?quality=90&strip=all&crop=0%2C10.732984293194%2C100%2C78.534031413613&w=1200")
          .publishedAt("2026-02-26T22:59:06Z")
          .content("<ul><li></li><li></li><li></li></ul>\\r\\nJeffrey Epstein saw promise in Bitcoin and its far-right supporters\\r\\nEpstein may not have fully understood crypto, but he helped shape its culture anyway.\\r\\nby\\r\\nD… [+21731 chars]")
          .source(
              Source.builder()
                  .id("the-verge")
                  .name("The Verge")
                  .build())
          .build()
      ))
      .build();
  }

  private static TopHeadlineResponse fillSuccessHeadlinesResponse() {
    return TopHeadlineResponse.builder()
      .totalResults(2)
      .status(SUCCESS_STATUS_RESPONSE)
      .articles(
        List.of(
          ArticlesItem.builder()
            .publishedAt("2026-03-20T04:32:00Z")
            .author("author")
            .urlToImage("https://fortune.com/img-assets/wp-content/uploads/2026/03/GettyImages-2155472642-e1773976186755.jpg?resize=1200,600")
            .description("The arrest of board member Yih-Shyan “Wally” Liaw—who previously resigned following an accounting scandal in 2018—led an after-hours trading drop of 12% in the Nvidia-linked server manufacturer's stock.")
            .source(
                Source.builder()
                    .id("Fortune")
                    .name("fortune")
                    .build()
            )
            .title("Supermicro's co-founder was just arrested for allegedly smuggling $2.5 billion in GPUs to China - Fortune")
            .url("https://fortune.com/2026/03/19/supermicro-arrested-founder-smuggling-gpu-china/")
            .content("Federal agents on Thursday arrested Yih-Shyan Wally Liaw, a prominent Silicon Valley executive deep in the AI ecosystem who co-founded Supermicro in 1993 and is a close confidante of CEO and chairman… [+11450 chars]")
            .build(),
          ArticlesItem.builder()
            .publishedAt("2026-03-20T04:09:46Z")
            .author("Kate Santaliz")
            .urlToImage("https://images.axios.com/q7HxSajEuDqVkud-A-NYg_CniFk=/0x0:6000x3375/1366x768/2026/03/19/1773957460429.jpeg")
            .description("Expect to hear lots of talk about \"offsets\" from fiscal hawks.")
            .source(
                Source.builder()
                    .id("Axios")
                    .name("axios")
                    .build()
            )
            .title("House GOP welcomes $200B Pentagon request to jump-start reconciliation 2.0 - Axios")
            .url("https://www.axios.com/2026/03/19/pentagon-200-billion-reconciliation-house-republicans")
            .content("<ul><li>The Pentagon's request \"opens up the door to a second reconciliation package,\" Rep. Michael Cloud (R-Texas) said.</li></ul>Zoom in: Any reconciliation process is typically painful and prolong… [+2416 chars]")
            .build()
        )
      ).build();
  }

  private static SourceResponse fillSourcesResponse() {
    return SourceResponse.builder()
      .status(SUCCESS_STATUS_RESPONSE)
      .sources(
        List.of(
          Source.builder()
            .id("australian-financial-review")
            .name("Australian Financial Review")
            .country("au")
            .description("The Australian Financial Review reports the latest news from business, finance, investment and politics, updated in real time. It has a reputation for independent, award-winning journalism and is essential reading for the business and investor community.")
            .language("en")
            .category("business")
            .url("http://www.afr.com")
            .build(),
          Source.builder()
            .id("bloomberg")
            .name("Bloomberg")
            .country("us")
            .description("Bloomberg delivers business and markets news, data, analysis, and video to the world, featuring stories from Businessweek and Bloomberg News.")
            .language("en")
            .category("business")
            .url("http://www.bloomberg.com")
            .build(),
          Source.builder()
            .id("business-insider")
            .name("Business Insider")
            .country("us")
            .description("Business Insider is a fast-growing business site with deep financial, media, tech, and other industry verticals. Launched in 2007, the site is now the largest business news site on the web.")
            .language("en")
            .category("business")
            .url("http://www.businessinsider.com")
            .build()
        )
      ).build();
  }

  private static REverythingResponse fillREverythingResponse() {
    return new REverythingResponse(SUCCESS_EVERYTHING_RESPONSE.getTotalResults(), SUCCESS_EVERYTHING_RESPONSE.getArticles());
  }

  private static RTopHeadlinesResponse fillRTopHeadlinesResponse() {
    return new RTopHeadlinesResponse(SUCCESS_TOP_HEADLINES_RESPONSE.getTotalResults(), SUCCESS_TOP_HEADLINES_RESPONSE.getArticles());
  }

  private static RSourcesResponse fillRSourcesResponse() {
    return new RSourcesResponse(SUCCESS_SOURCES_RESPONSE.getSources());
  }
}
