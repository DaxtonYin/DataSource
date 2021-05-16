package com.Schedule;

import com.alibaba.fastjson.JSON;
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

import java.util.List;


@Component
public class OverseasInput implements PageProcessor {

    @Override
    public void process(Page page) {
        //获取境外输入总数和每日新增境外输入人数
        Selectable selectable = page.getHtml().css("div.import").nodes().get(0);
        String text1 = selectable.css("div.add span","text").toString();
        String text2 = selectable.css("div.number","text").toString();
        OverSeasBean overSeas = new OverSeasBean();
        overSeas.setAddOverseasCount(text1);
        overSeas.setTotalOverseasCount(text2);
        overSeas.setDate(TimeUtils.format(System.currentTimeMillis()-24*60*60*1000,"MM-dd"));

        //获取文件内容
        String str = new FileUtils().getFileContent("overseas.json");
        //将其解析为对象
        List<OverSeasBean> overSeasList = JSON.parseArray(str, OverSeasBean.class);

        if(!(overSeasList.get(overSeasList.size()-1).getTotalOverseasCount().equals(overSeas.getTotalOverseasCount()))){
            overSeasList.remove(0);
            overSeasList.add(overSeas);
        }
        String overseasData = JSON.toJSONString(overSeasList);
        new FileUtils().saveFile(overseasData,"overseas.json");
        System.out.println(overseasData);
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

    @Scheduled(cron="0 0 0/4 * * ?")
    public void process() {
        //1.加载selenium的配置文件 config.ini
        System.setProperty("selenuim_config","E:\\chromedriver\\config.ini");
        Spider.create(new OverseasInput())
                .addUrl("https://news.qq.com/zt2020/page/feiyan.htm#/")
                .setDownloader(new SeleniumDownloader("E:\\chromedriver\\chromedriver.exe"))
                .thread(5)
                .run();
    }
}
