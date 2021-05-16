package com.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public abstract class HttpClientUtils {
    private static PoolingHttpClientConnectionManager cm;

    public static String getHtml(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(getConfig());
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Safari/537.36");
        httpGet.setHeader("Cookie", "dxy_da_cookie-id=1c0a40c352c97b02c44d8e340a55a5821619763995848");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        String html = "";
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity httpEntity = response.getEntity();
            html = EntityUtils.toString(httpEntity, "utf-8");
        }
        response.close();
        return html;
    }

    private static RequestConfig getConfig() {
        HttpHost httpHost1 = new HttpHost("47.116.76.219", 80);
        HttpHost httpHost2 = new HttpHost("60.255.151.82", 80);
        HttpHost httpHost3 = new HttpHost("120.194.55.139", 6969);
//        HttpHost httpHost4 = new HttpHost("47.102.47.126",8080);
//        List<HttpHost> list = new ArrayList<HttpHost>();
//        list.add(httpHost1);
//        list.add(httpHost2);
//        list.add(httpHost3);
//        list.add(httpHost4);
        return RequestConfig.custom()
                .setConnectTimeout(100000)
                .setSocketTimeout(100000)
                .setConnectionRequestTimeout(100000)
                .build();
    }


}
