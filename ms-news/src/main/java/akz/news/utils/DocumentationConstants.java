package akz.news.utils;

public final class DocumentationConstants {

  private DocumentationConstants() {}

  public static final String USER_AGENT = "User-Agent";
  public static final String USER_AGENT_VALUE = "NewsApp/1.0 (alejdaf@outlook.com)";

  public static final String QUERY_PARAMETER_DESCRIPTION = """
      Keywords or phrases to search for in the article title and body.<br>
      Advanced search is supported here:
      
      - Surround phrases with quotes **(")** for exact match.
      - Prepend words or phrases that must appear with a **+** symbol. Eg: **+bitcoin**
      - Prepend words that must not appear with a **-** symbol. Eg: **-bitcoin**
      - Alternatively you can use the **AND / OR / NOT** keywords, and optionally group these with parenthesis.<br>
      **Eg: crypto AND (ethereum OR litecoin) NOT bitcoin.**
      
      The complete value for q must be URL-encoded. **Max length: 500 chars.**
      """;

  public static final String SEARCH_IN_PARAMETER_DESCRIPTION = """
      The fields to restrict your q search to.<br>
      The possible options are:
      
      - **title**
      - **description**
      - **content**
      
      Multiple options can be specified by separating them with a comma, for example: **title,content.**<br>
      
      This parameter is useful if you have an edge case where searching all the fields is not giving the desired
       outcome, but generally you should not need to set this.
      
      **Default: all fields are searched.**
      """;

  public static final String SOURCES_PARAMETER_DESCRIPTION = """
       A comma-seperated string of identifiers (maximum 20) for the news sources or blogs you want headlines from.
        Use the /sources endpoint to locate these programmatically or look at the sources index.
      """;

  public static final String DOMAINS_PARAMETER_DESCRIPTION = """
       A comma-seperated string of domains (eg bbc.co.uk, techcrunch.com, engadget.com) to restrict the search to.
      """;

  public static final String EXCLUDE_DOMAINS_PARAMETER_DESCRIPTION = """
       A comma-seperated string of domains (eg bbc.co.uk, techcrunch.com, engadget.com) to remove from the results.
      """;

  public static final String FROM_PARAMETER_DESCRIPTION = """
      A date and optional time for the oldest article allowed. This should be in ISO 8601 format
       (e.g. 2026-03-18 or 2026-03-18T06:02:26)
      
      Default: the oldest according to your plan.
      """;

  public static final String TO_PARAMETER_DESCRIPTION = """
      A date and optional time for the newest article allowed. This should be in ISO 8601 format
       (e.g. 2026-03-18 or 2026-03-18T06:02:26)
      
      Default: the newest according to your plan.
      """;

  public static final String LANGUAGE_PARAMETER_DESCRIPTION = """
      The 2-letter ISO-639-1 code of the language you want to get headlines for. Possible options: ar|de|en|es|fr|he|it|nl|no|pt|ru|sv|ud|zh.
      
      Default: all languages returned.
      """;

  public static final String SORT_BY_PARAMETER_DESCRIPTION = """
      The order to sort the articles in. Possible options: relevancy, popularity, publishedAt.
      relevancy = articles more closely related to q come first.
      popularity = articles from popular sources and publishers come first.
      publishedAt = newest articles come first.
      
      Default: publishedAt
      """;

  public static final String PAGE_SIZE_PARAMETER_DESCRIPTION = """
      The number of results to return per page.
      
      Default: 100. Maximum: 100.
      """;

  public static final String PAGE_PARAMETER_DESCRIPTION = """
      Use this to page through the results.
      
      Default: 1.
      """;

  public static final String COUNTRY_TOP_HEADLINES_PARAMETER_DESCRIPTION = """
    The 2-letter ISO 3166-1 code of the country you want to get headlines for. Possible options: us. Note:
     you can't mix this param with the sources param.
    """;

  public static final String CATEGORY_TOP_HEADLINES_PARAMETER_DESCRIPTION = """
    The category you want to get headlines for. Possible options: business, entertainment, general, health, science,
     sports, technology. Note: you can't mix this param with the sources param.
    """;

  public static final String SOURCES_TOP_HEADLINES_PARAMETER_DESCRIPTION = """
    A comma-seperated string of identifiers for the news sources or blogs you want headlines from. Use the
     /top-headlines/sources endpoint to locate these programmatically or look at the sources index. Note:
      you can't mix this param with the country or category params.
    """;

  public static final String QUERY_TOP_HEADLINES_PARAMETER_DESCRIPTION = """
    Keywords or a phrase to search for.
    """;

  public static final String PAGE_SIZE_TOP_HEADLINES_PARAMETER_DESCRIPTION = """
    The number of results to return per page (request). 20 is the default, 100 is the maximum.
    """;

  public static final String PAGE_TOP_HEADLINES_PARAMETER_DESCRIPTION = """
    Use this to page through the results if the total results found is greater than the page size.
    """;

  public static final String CATEGORY_SOURCE_PARAMETER_DESCRIPTION = """
    Find sources that display news of this category. Possible options: business, entertainment, general, health,
     science, sports, technology. Default: all categories.
    """;

  public static final String LANGUAGE_SOURCE_HEADLINES_PARAMETER_DESCRIPTION = """
    Find sources that display news in a specific language. Possible options: ar | de | en | es | fr | he | it | nl |
     no | pt | ru | sv | ud | zh. Default: all languages.
  """;

  public static final String COUNTRY_SOURCE_HEADLINES_PARAMETER_DESCRIPTION = """
    Find sources that display news in a specific country. Possible options:  ae | ar | at | au | be | bg | br | ca | ch
     | cn | co | cu | cz | de | eg | fr | gb | gr | hk | hu | id | ie | il | in | it | jp | kr | lt | lv | ma | mx | my
     | ng | nl | no | nz | ph | pl | pt | ro | rs | ru | sa | se | sg | si | sk | th | tr | tw | ua | us | ve | za.<br>
     Default: all countries.
  """;
}
