package com.Schedule;

import com.util.CrawlerUtils;
import com.util.FileUtils;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class ProvinceData {
    @Scheduled(cron = "0 0 0/3 * * ?")
    public void process() throws IOException {
        String content = CrawlerUtils.getHtmlContent();
        new FileUtils().saveFile(content,"province.json");
        System.out.println(content);
    }
}
