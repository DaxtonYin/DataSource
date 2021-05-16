package com.Schedule;

import com.alibaba.fastjson.JSON;
import com.domain.NewsBean;
import com.util.FileUtils;
import com.util.HttpClientUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewsData{

    @Scheduled(cron = "0 0 0/3 * * ?")
    public void process() {
        String html = null;
        Document doc = null;
        List<NewsBean> newsBeanList = new ArrayList<>();
        try {
            html = HttpClientUtils.getHtml("http://www.xinhuanet.com/politics/xxgzbdgrdfyyq/index.htm");
        }catch (Exception e){
            e.printStackTrace();
        }
        if(html!=null){
            doc = Jsoup.parse(html);
            Elements elements = doc.select("ul#autoData li");
            int count=0;
            for (Element element:elements){
                NewsBean newsBean = new NewsBean();
                String date = element.select("dl dt").text();
                String href = element.select("dl dd h3 a").attr("href");
                String title = element.select("dl dd h3 a").text();
                String content = element.select("dl dd p").text();
                newsBean.setDate(date);
                newsBean.setHref(href);
                newsBean.setContent(content);
                newsBean.setTitle(title);
                newsBeanList.add(newsBean);
                if(++count>=10){
                    break;
                }
            }
        }
        String newsString = JSON.toJSONString(newsBeanList);
        new FileUtils().saveFile(newsString,"news.json");

        System.out.println(newsString);

    }
}

