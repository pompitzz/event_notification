package me.sun.notification_service.core.crawling.korail;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Tester {
    private static StringBuilder CMD = new StringBuilder();
    public static void main(String[] args) throws IOException, InterruptedException {
        String url = "http://www.letskorail.com/ebizprd/EbizPrdTicketPrConditionalList.do?&txtGoAbrdDt=20200703&txtGoYoil=금&txtGoStartCode=0001&txtGoStart=서울&txtGoEndCode=0020&txtGoEnd=부산&selGoTrain=05&selGoRoom=&selGoRoom1=&txtGoHour=\"+ChgTrnDvCd+\"&txtGoTrnNo=&useSeatFlg=&useServiceFlg=&selGoSeat=&selGoSeat1=015&selGoSeat2=&txtPsgCnt1=1&txtPsgCnt2=0&txtMenuId=41&selGoService=&txtGoPage=2&txtPnrNo=&hidRsvChgNo=0&hidStlFlg=&radJobId=1&SeandYo=&hidRsvTpCd=03&txtGoHour_first=&txtPsgFlg_1=0&txtPsgFlg_2=0&txtPsgFlg_3=0&txtPsgFlg_4=0&txtPsgFlg_5=0&txtPsgFlg_6=1&chkCpn=N&ServiceCode=B121410002GY&menu=2&hidCndFlgDiscNo2=B121410002GY";
        CMD.append("open ");
        CMD.append(url);
        int count = 1;

        while (true) {
            System.out.println("시도 횟수: " + count);
            findTicket(url);
            count++;
            TimeUnit.SECONDS.sleep(30);
        }
    }

    private static void findTicket(String url) throws IOException {
        final Document document = Jsoup.connect(url)
                .method(Connection.Method.POST)
                .get();
        final Element table = document.getElementsByClass("tbl_h").get(0);
        final Elements trTags = table.select("tr[class=\"\"]");
        final List<Element> target = new ArrayList<>();
        for (Element trTag : trTags) {
            if (isTarget(trTag)) {
                for (Element tdTag : trTag.getElementsByTag("td")) {
                    final Elements imgs = tdTag.getElementsByTag("img");
                    if (imgs.size() == 0) {
                        continue;
                    }
                    for (Element img : imgs) {
                        if (altExcludeFilters.contains(img.attr("alt"))) {
                            continue;
                        }

                        if (img.attr("alt").contains("경부기존선")) {
                            continue;
                        }

                        if (!img.attr("alt").equals("할인매진")) {
                            System.out.println(CMD.toString());
                            Runtime.getRuntime().exec(CMD.toString());
                            System.exit(0);
                        }
                    }
                }
            }
        }
    }

    private static Set<String> altExcludeFilters = new HashSet<>(Arrays.asList("4인동반석", "전동휠체어", "운임요금 조회"));

    private static boolean isTarget(Element element) {
        for (Element tdTag : element.getElementsByTag("td")) {
            final String text = tdTag.text();
            if (text.contains("서울")) {
                final String time = text.split(" ")[1];
                final int hour = Integer.parseInt(time.split(":")[0]);
                final boolean result = hour >= 19;
//                if (result) {
//                    System.out.println("서울: " + hour);
//                }
                return result;
            }
        }
        return false;
    }
}
