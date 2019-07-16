package com.garbage.classification.utils;

import com.garbage.classification.entity.Garbage;
import com.github.pagehelper.util.StringUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author domain
 * @date 2019-07-12
 */
public class GarbageCatchUtils {
    static Pattern proInfo
            = Pattern.compile("<span>(.*?)</span><span>(.*?)</span><span>(.*?)</span>", Pattern.DOTALL);


    public static Garbage catchGarbage(String name) throws Exception {
        String urlInfo = "https://lajifenleiapp.com/sk/" + name;
        InputStream is = null;
        BufferedReader br = null;
        Garbage garbage = null;
        try {
            URL url = new URL(urlInfo);
            HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.setConnectTimeout(1000);
            httpUrl.setReadTimeout(1000);
            is = httpUrl.getInputStream();
            br = new BufferedReader(new InputStreamReader(is, "utf-8"));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                //这里是对链接进行处理
                line = line.replaceAll("</?a[^>]*>", "");
                //这里是对样式进行处理
                line = line.replaceAll("<(\\w+)[^>]*>", "<$1>");
                sb.append(line);
            }
            garbage = getGarbage(sb.toString().trim());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } finally {
            if (is != null && br != null) {
                is.close();
                br.close();
            }
        }
        return garbage;
    }

    private static Garbage getGarbage(String str) {
        //运用正则表达式对获取的网页源码进行数据匹配，提取我们所要的数据，在以后的过程中，我们可以采用httpclient+jsoup,
        //现在暂时运用正则表达式对数据进行抽取提取
        String[] info = str.split("</div>");
        List<String> title = new ArrayList<>();
        for (String s : info) {
            Matcher m = proInfo.matcher(s);
            if (m.find()) {
//                有害垃圾 可回收物 湿垃圾(厨余垃圾)  干垃圾(其它垃圾)
                String[] ss = m.group().trim().replace(" ", "").split("</span>");
                Arrays.asList(ss).forEach(temp -> {
                    Arrays.asList(temp.replace(" ", "").split("<span>")).forEach(name -> {
                        if (StringUtil.isNotEmpty(name)) {
                            title.add(name);
                        }
                    });
                });
            }
        }
//        title.forEach(System.out::println);
        Garbage garbage = null;
        if (title.size() == 3) {
//            1 可回收 2 有害 3 湿垃圾 4 干垃圾
            String type = title.get(2).trim();
            Long index = "有害垃圾".equals(type) ? 2L : "可回收物".equals(type) ? 1L : "湿垃圾(厨余垃圾)".equals(type) ? 3L : "干垃圾(其它垃圾)".equals(type) ? 4L : 0L;
            garbage = new Garbage(index, title.get(0));
        }
        return garbage;
    }
}
