package me.sun.notification_service.crawler.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class SearchCrawler {
    private String name;
    private String url;
    private String searchUrl;
    private String contentWrapperListing;
    private String titleTag;
    private String articleUrlTag;
    private String descriptionTag;
    private boolean isAbsoluteUrl;

    public List<Content> search(String topic) {
        List<Content> result = new ArrayList<>();

        final String searchUrl = this.searchUrl + topic;
        final Document document = JsoupWarpper.get(searchUrl);
        for (Element contentWrapper : document.select(contentWrapperListing)) {
            final String title = contentWrapper.select(titleTag).text();
            final String articleUrl = contentWrapper.select(articleUrlTag).attr("href");
            final String description = contentWrapper.select(descriptionTag).text();
            final Content content = Content.builder()
                    .title(title)
                    .articleUrl(articleUrl)
                    .description(description)
                    .isAbsoluteUrl(this.isAbsoluteUrl)
                    .build();
            result.add(content);
        }
        return result;
    }
}
