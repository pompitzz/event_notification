package me.sun.notification_service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Slf4j
public class Crawler {
    public static void main(String[] args) throws IOException {
        followExternalOnly("http://oreilly.com");
    }

    private static List<Element> getLinks(String articleUrl) {
        final String url = String.format("https://en.wikipedia.org%s", articleUrl);
        try {
            final Document docs = Jsoup.connect(url).get();
            final Element bodyContent = docs.getElementById("bodyContent");
            final Elements select = bodyContent.select("a[href~=^(/wiki/)((?!:).)*$]");
            return new ArrayList<>(select);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
    }

    private static List<String> getInternalLinks(Document docs, String includeUrl) {
        Set<String> internalLinks = new HashSet<>();
        for (Element link : docs.select("a[href~=^(/|.*" + includeUrl + ")")) {
            final String href = link.attr("href");
            if (href.startsWith("/")) {
                internalLinks.add(includeUrl + href);
            } else {
                internalLinks.add(href);
            }
        }
        return new ArrayList<>(internalLinks);
    }

    private static List<String> getExternalLinks(Document docs, String excludeUrl) {
        Set<String> externalLinks = new HashSet<>();
        for (Element link : docs.select("a[href~=^(http[s]?:|www\\.)((?!" + excludeUrl + ").*)$]")) {
            final String href = link.attr("href");
            externalLinks.add(href);
        }
        return new ArrayList<>(externalLinks);
    }

    private static String getRandomExternalLinks(String startingPageUrl) {
        Document docs;
        try {
            docs = Jsoup.connect(startingPageUrl).get();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        final List<String> externalLinks = getExternalLinks(docs, startingPageUrl.replaceFirst("^(http[s]?)", ""));
        if (externalLinks.size() == 0) {
            System.out.println("외부 링크가 없습니다. 내부 링크를 타고 들어가 재탐색합니다.");
            final List<String> internalLinks = getInternalLinks(docs, startingPageUrl);
            return getRandomExternalLinks(internalLinks.get(ThreadLocalRandom.current().nextInt(internalLinks.size())));
        } else {
            return externalLinks.get(ThreadLocalRandom.current().nextInt(externalLinks.size()));
        }
    }

    private static void followExternalOnly(String startingSiteUrl) {
        final String externalLink = getRandomExternalLinks(startingSiteUrl);
        System.out.println("externalLink :: " + externalLink);
        followExternalOnly(externalLink);
    }
}
