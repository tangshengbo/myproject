package com.tangshengbo.tutorial.http;

import com.tangshengbo.net.url.QueryString;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

/**
 * Created by Tangshengbo on 2018/3/5.
 */
public class HtmlParser {

    private static final Logger logger = LoggerFactory.getLogger(HtmlParser.class);

    @Test
    public void testJsonp() {
        QueryString queryString = new QueryString();
        queryString.add("ip", "192.168.20.48");
        queryString.add("action", "1");
        try {
            //Jsoup.connect("http://www.ip138.com/ips1388.asp?" + queryString).get();
            Document doc = Jsoup.parse(new URL("http://www.ip138.com/ips1388.asp?" + queryString), 10000);
            Elements elements = doc.select("ul.ul1 li");
            String address = elements.get(0).text();
            address = address.substring(address.indexOf("：") + 1);
            logger.info("{}", address);
        } catch (Exception e) {
            logger.error("{}", ExceptionUtils.getStackTrace(e));
        }
    }
}
