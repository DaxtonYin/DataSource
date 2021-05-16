package com.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class CrawlerUtils {
    public static String getHtmlContent() throws IOException {
        String html = HttpClientUtils.getHtml("https://ncov.dxy.cn/ncovh5/view/pneumonia");
        Document doc = Jsoup.parse(html);
        String text = doc.select("script#getAreaStat").toString();
        //定义正则表达式
        String pattern = "\\[(.*)\\]";
        Pattern reg = Pattern.compile(pattern);
        Matcher matcher = reg.matcher(text);
        String jsonStr = "";
        if (matcher.find()) {
            jsonStr = matcher.group(0);
        } else {
            System.out.println("没有找到");
        }
        return jsonStr;
    }

}
