package com.Schedule;

import com.alibaba.fastjson.JSON;
import com.domain.CovidBean;
import com.domain.ProvinceTopBean;
import com.util.CrawlerUtils;
import com.util.FileUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class ProvinceTop10Data {
    @Scheduled(cron = "0 0 0/2 * * ?")
    public void process(){
        String jsonStr = "";
        try{
            jsonStr = CrawlerUtils.getHtmlContent();
        }catch(Exception e){
            e.printStackTrace();
        }
        List<CovidBean> pCovidBeans = JSON.parseArray(jsonStr, CovidBean.class);

        //统计现存确诊，累计确诊，死亡，治愈人数
        double result1 = 0,result2 = 0;
        for (CovidBean pCovidBean : pCovidBeans) {
            result1 += Integer.parseInt(pCovidBean.getCurrentConfirmedCount());
            result2 += Integer.parseInt(pCovidBean.getConfirmedCount());
        }

        pCovidBeans.sort(new Comparator<CovidBean>() {
            @Override
            public int compare(CovidBean o1, CovidBean o2) {
                if(Integer.parseInt(o1.getCurrentConfirmedCount())>Integer.parseInt(o2.getCurrentConfirmedCount())){
                    return -1;
                }else if(Integer.parseInt(o1.getCurrentConfirmedCount())==Integer.parseInt(o2.getCurrentConfirmedCount())){
                    return -Integer.compare(Integer.parseInt(o1.getConfirmedCount()),Integer.parseInt(o2.getConfirmedCount()));
                }else {
                    return 1;
                }
            }
        });

        List<ProvinceTopBean> topList = new ArrayList<ProvinceTopBean>();
        for(int i=0;i<10;i++){
            int currentConfirmedCount = Integer.parseInt(pCovidBeans.get(i).getCurrentConfirmedCount());
            int ConfirmedCount =  Integer.parseInt(pCovidBeans.get(i).getConfirmedCount());
            double percentage1 = currentConfirmedCount/result1;
            double percentage2 = ConfirmedCount/result2;

            //保留小数点后五位
            BigDecimal b1 = new BigDecimal(percentage1);
            double newPercentage1 = b1.setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();

            BigDecimal b2 = new BigDecimal(percentage2);
            double newPercentage2 = b2.setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();

            ProvinceTopBean provinceTopBean = new ProvinceTopBean();
            provinceTopBean.setCurrentConfirmedCount_percentage(newPercentage1);
            provinceTopBean.setConfirmedCount_percentage(newPercentage2);
            provinceTopBean.setCurrentConfirmedCount(currentConfirmedCount);
            provinceTopBean.setConfirmedCount(ConfirmedCount);
            provinceTopBean.setProvinceName(pCovidBeans.get(i).getProvinceName());
            topList.add(provinceTopBean);
        }
        String str = JSON.toJSONString(topList);
        new FileUtils().saveFile(str,"provinceTop10.json");
        System.out.println(str);
    }
}
