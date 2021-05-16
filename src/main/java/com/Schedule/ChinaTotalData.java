package com.Schedule;

import com.alibaba.fastjson.JSON;
import com.domain.ChinaTotalBean;
import com.domain.OverSeasBean;
import com.util.FileUtils;
import com.util.TimeUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChinaTotalData implements PageProcessor {

    @Override
    public void process(Page page) {
        //获取境外输入总数和每日新增境外输入人数
        Selectable selectable = page.getHtml().css("ul.count___2lQ55").nodes().get(0);
        List<Selectable> selectableList = selectable.css("li").nodes();
        List<String> ChinaTotalList = new ArrayList<>();
        for (Selectable data:selectableList){
            String str = data.css("li strong","text").toString();
            ChinaTotalList.add(str);
        }

        System.out.println(ChinaTotalList);
        ChinaTotalBean chinaTotalBean = new ChinaTotalBean();
        chinaTotalBean.setCurrentConfirmedCount(ChinaTotalList.get(0));
        chinaTotalBean.setConfirmedCount(ChinaTotalList.get(3));
        chinaTotalBean.setDeadCount(ChinaTotalList.get(4));
        chinaTotalBean.setCuredCount(ChinaTotalList.get(5));

        String ChinaTotalStr = JSON.toJSONString(chinaTotalBean);
        new FileUtils().saveFile(ChinaTotalStr,"ChinaTotal.json");

        System.out.println(ChinaTotalStr);
    }

    @Override
    public Site getSite() {
        Site site = Site.me()
                .setCharset("utf-8")
                .setTimeOut(10000)
                .setRetryTimes(5)
                .setRetryTimes(3*1000)
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.85 Safari/537.36 Edg/90.0.818.49");
        return site;
    }

    @Scheduled(cron="0 0 0/5 * * ?")
    public void process() {
        //1.加载selenium的配置文件 config.ini
        System.setProperty("selenuim_config","E:\\chromedriver\\config.ini");
        Spider.create(new ChinaTotalData())
                .addUrl("https://ncov.dxy.cn/ncovh5/view/pneumonia")
                .setDownloader(new SeleniumDownloader("E:\\chromedriver\\chromedriver.exe"))
                .thread(5)
                .run();
     }
}
