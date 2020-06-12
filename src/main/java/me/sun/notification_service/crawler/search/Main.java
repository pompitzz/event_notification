package me.sun.notification_service.crawler.search;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        final SearchCrawler google = SearchCrawler.builder()
                .name("google")
                .searchUrl("https://www.google.com/search?q=python")
                .contentWrapperListing("div[class=g]")
                .articleUrlTag("div[class=r] > a")
                .titleTag("div[class=r] h3")
                .descriptionTag("div[class=s] span[class=st]")
                .isAbsoluteUrl(false)
                .build();

        final List<Content> pythonContents = google.search("python");
        pythonContents.forEach(System.out::println);

    }
}
