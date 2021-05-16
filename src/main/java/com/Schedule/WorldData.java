package com.Schedule;

import com.alibaba.fastjson.JSON;
import com.domain.WorldAddBean;
import com.domain.WorldBean;
import com.util.FileUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
import java.util.*;

@Component
public class WorldData implements PageProcessor {
    String[] country1 = new String[]{
            "美国", "印度", "巴西", "法国", "土耳其", "俄罗斯", "英国", "意大利", "西班牙", "德国", "阿根廷",
            "哥伦比亚", "波兰", "伊朗", "墨西哥", "乌克兰", "秘鲁", "印度尼西亚", "捷克", "南非", "荷兰", "加拿大", "智利",
            "伊拉克", "菲律宾", "罗马尼亚", "瑞典","比利时", "巴基斯坦", "葡萄牙", "以色列", "匈牙利",
            "孟加拉", "约旦", "塞尔维亚", "瑞士"};
    String[] country2 = new String[]{
            "日本", "奥地利", "阿联酋", "黎巴嫩", "摩洛哥", "马来西亚", "沙特阿拉伯",
            "尼泊尔", "保加利亚", "厄瓜多尔", "哈萨克斯坦", "斯洛伐克", "白俄罗斯", "巴拿马", "希腊", "克罗地亚", "阿塞拜疆",
            "格鲁吉亚", "突尼斯", "玻利维亚", "巴勒斯坦", "巴拉圭", "科威特", "哥斯达黎加", "多米尼加", "埃塞俄比亚",
            "丹麦", "立陶宛", "爱尔兰", "摩尔多瓦", "斯洛文尼亚", "埃及"};
    String[] country3 = new String[]{
            "危地马拉", "乌拉圭", "洪都拉斯", "亚美尼亚", "卡塔尔", "委内瑞拉",
            "阿曼", "波黑", "巴林", "利比亚", "尼日利亚", "肯尼亚", "北马其顿", "缅甸", "阿尔巴尼亚", "斯里兰卡", "韩国", "爱沙尼亚",
            "拉脱维亚", "阿尔及利亚", "古巴", "挪威", "吉尔吉斯斯坦", "黑山", "乌兹别克斯坦", "加纳", "赞比亚", "芬兰", "泰国"};
    String[] country4 = new String[]{
            "喀麦隆",
            "萨尔瓦多", "莫桑比克", "塞浦路斯", "卢森堡", "阿富汗", "新加坡", "纳米比亚", "博茨瓦纳", "牙买加", "科特迪瓦", "蒙古", "乌干达",
            "塞内加尔", "马达加斯加", "马尔代夫", "津巴布韦", "苏丹", "马拉维", "马耳他", "刚果（金）",
            "澳大利亚", "安哥拉", "佛得角", "卢旺达", "加蓬", "叙利亚", "几内亚", "柬埔寨", "毛里塔尼亚", "斯威士兰", "圭亚那", "索马里", "马里",
            "特立尼达和多巴哥", "安道尔", "布基纳法索", "塔吉克斯坦", "海地", "多哥", "伯利兹", "巴布亚新几内亚", "吉布提", "刚果（布）",
            "苏里南", "巴哈马", "莱索托", "塞舌尔", "贝宁", "赤道几内亚", "尼加拉瓜", "中非共和国"};
    String[]  country5 = new String[]{
            "冰岛", "也门", "冈比亚", "尼日尔",
            "圣马力诺", "乍得", "圣卢西亚", "布隆迪", "塞拉利昂", "巴巴多斯", "科摩罗",
            "厄立特里亚", "几内亚比绍", "越南", "东帝汶", "列支敦士登公国", "新西兰", "摩纳哥", "利比里亚", "圣文森特和格林纳丁斯", "老挝", "毛里求斯",
            "不丹", "安提瓜和巴布达", "钻石号邮轮", "坦桑尼亚", "文莱", "多米尼克", "格林纳达", "斐济", "圣基茨和尼维斯", "马提尼克岛",
            "梵蒂冈", "所罗门群岛", "马绍尔群岛", "瓦努阿图"};

    String[] country_translate1 = new String[]{
                "USA", "India", "Brazil", "France", "Turkey", "Russia", "UK", "Italy", "Spain", "Germany", "Argentina", "Colombia",
                "Poland", "Iran", "Mexico", "Ukraine", "Peru", "Indonesia", "Czech Republic", "South Africa", "Netherlands",
                "Canada", "Chile", "Iraq", "Philippines", "Romania", "Sweden", "Belgium", "Pakistan", "Portugal", "Israel",
                "Hungary", "Bangladesh", "Jordan", "Serbia", "Switzerland"};
    String[] country_translate2 = new String[]{"Japan", "Austria", "UAE", "Lebanon", "Morocco",
                "Malaysia", "Saudi Arabia", "Nepal", "Bulgaria", "Ecuador", "Kazakhstan", "Slovakia", "Belarus",
                "Panama", "Greece", "Croatia", "Azerbaijan", "Georgia", "Tunisia", "Bolivia", "Palestine", "Paraguay",
                "Kuwait", "Costa Rica", "Dominica", "Ethiopia", "Denmark", "Lithuania", "Ireland", "Moldova", "Slovenia", "Egypt"};
    String[] country_translate3 = new String[]{
                 "Guatemala", "Uruguay", "Honduras", "Armenia", "Qatar", "Venezuela", "Oman", "Bosnia and Herzegovina", "Bahrain",
                "Libya", "Nigeria", "Kenya", "North Macedonia", "Myanmar", "Albania", "Sri Lanka", "Korea", "Estonia",
                "Latvia", "Algeria", "Cuba", "Norway", "Kyrgyzstan", "Montenegro", "Uzbekistan", "Ghana", "Zambia", "Finland",
                "Thailand"};
    String[] country_translate4 = new String[]{"Cameroon", "El Salvador", "Mozambique", "Cyprus", "Luxembourg", "Afghanistan", "Singapore", "Namibia",
                "Botswana", "Jamaica", "Cote d'Ivoire", "Mongolia", "Uganda", "Senegal", "Madagascar", "Maldives", "Zimbabwe", "Sudan",
                "Malawi", "Malta", "Congo (DRC)", "Australia", "Angola", "Cape Verde", "Rwanda", "Gabon", "Syria", "Guinea", "Cambodia",
                "Mauritania", "Swaziland", "Guyana", "Somalia", "Mali", "Trinidad and Tobago", "Andorra", "Burkina Faso", "Tajikistan",
                "Haiti", "Togo", "Belize", "Papua New Guinea", "Djibouti", "Congo", "Suriname", "Bahamas", "Lesotho", "Seychelles",
                "Benin", "Equatorial Guinea", "Nicaragua", "Central African Republic"};
      String[] country_translate5 = {
               "Iceland", "Yemen", "Gambia", "Niger",
                "San Marino", "Chad", "Saint Lucia", "Burundi", "Sierra Leone", "Barbados", "Comoros", "Eritrea", "Guinea-Bissau",
                "Viet Nam", "Timor-Leste","Principality of Liechtenstein", "New Zealand", "Monaco", "Liberia", "Saint Vincent and the Grenadines",
                "Laos","Mauritius", "Bhutan", "Antigua and Barbuda", "Cruise Diamond", "Tanzania", "Brunei", "Dominica", "Grenada", "Fiji",
                "Saint Kitts and Nevis", "Martinique", "Vatican", "Solomon Islands", "Marshall Islands", "Vanuatu"
        };

    Map<String,String> map = new HashMap<>();
    List<WorldBean> worldBeanList = new ArrayList<>();
    List<WorldBean> sortWorldBeanList = new ArrayList<>();
    List<WorldAddBean> addWorldBeanList = new ArrayList<>();

    @Override
    public void process(Page page) {
        Map<String,String> map = new HashMap<>();
        for(int i=0;i<country1.length;i++){
            map.put(country1[i],country_translate1[i]);
        }
        for(int i=0;i<country2.length;i++){
            map.put(country2[i],country_translate2[i]);
        }
        for(int i=0;i<country3.length;i++){
            map.put(country3[i],country_translate3[i]);
        }
        for(int i=0;i<country4.length;i++) {
            map.put(country4[i], country_translate4[i]);
        }
        for(int i=0;i<country5.length;i++){
            map.put(country5[i],country_translate5[i]);
        }

        List<Selectable> selectedList = page.getHtml().css("div#foreignWraper tbody").nodes();
        for(Selectable selectable:selectedList){
            List<String> contentList = new ArrayList<>();

            String name = selectable.css("tr th span","text").toString();
            List<Selectable> dataList = selectable.css("td").nodes();

            for(Selectable data:dataList){
                contentList.add(data.css("td","text").toString());
            }
            contentList.remove(contentList.size()-1);

            WorldBean worldBean = new WorldBean();
            WorldBean sortWorldBean = new WorldBean();
            WorldAddBean addWorldBean = new WorldAddBean();
            
            worldBean.setName(map.get(name));
            worldBean.setValue(contentList.get(1));

            sortWorldBean.setName(name);
            sortWorldBean.setValue(contentList.get(1));

            addWorldBean.setName(name);
            addWorldBean.setAddConfirmCount(contentList.get(0));
            addWorldBean.setHeal(contentList.get(2));
            addWorldBean.setDeath(contentList.get(3));

            worldBeanList.add(worldBean);
            sortWorldBeanList.add(sortWorldBean);
            addWorldBeanList.add(addWorldBean);
        }

        WorldBean worldBean = new WorldBean();
        worldBean.setName("China");
        worldBean.setValue("104193");

        WorldBean sortWorldBean = new WorldBean();
        sortWorldBean.setName("中国");
        sortWorldBean.setValue("104193");

        worldBeanList.add(worldBean);
        sortWorldBeanList.add(sortWorldBean);

        sortWorldBeanList.sort(new Comparator<WorldBean>() {
            @Override
            public int compare(WorldBean o1, WorldBean o2) {
                return -Integer.compare(Integer.parseInt(o1.getValue()),Integer.parseInt(o2.getValue()));
            }
        });

        addWorldBeanList.sort(new Comparator<WorldAddBean>() {
            @Override
            public int compare(WorldAddBean o1, WorldAddBean o2) {
                return -Integer.compare(Integer.parseInt(o1.getAddConfirmCount()),Integer.parseInt(o2.getAddConfirmCount()));
            }
        });

        List<WorldBean> sortWorldTop10List = new ArrayList<>();
        List<WorldAddBean> sortAddWorldList = new ArrayList<>();

        for (int j=0;j<10;j++){
            sortWorldTop10List.add(sortWorldBeanList.get(j));
        }

        for (int i=0;i<10;i++){
            sortAddWorldList.add(addWorldBeanList.get(i));
        }

        String worldData = JSON.toJSONString(worldBeanList);
        String sortWorldData = JSON.toJSONString(sortWorldTop10List);
        String addWorldData = JSON.toJSONString(sortAddWorldList);

        new FileUtils().saveFile(worldData,"world.json");
        new FileUtils().saveFile(sortWorldData,"worldTop10.json");
        new FileUtils().saveFile(addWorldData,"worldAddConfirm.json");

        System.out.println(worldData);
        System.out.println(sortWorldData);
        System.out.println(addWorldData);
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

    @Scheduled(cron="0 0 0/3 * * ?")
    public void process(){
        System.setProperty("selenuim_config","E:\\chromedriver\\config.ini");
        Spider.create(new WorldData())
                .addUrl("https://news.qq.com/zt2020/page/feiyan.htm#/global")
                .setDownloader(new SeleniumDownloader("E:\\chromedriver\\chromedriver.exe"))
                .thread(5)
                .run();
    }
}
