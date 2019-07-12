package com.garbage.classification.test;

import com.garbage.classification.entity.Garbage;
import com.garbage.classification.utils.GarbageCatchUtils;
import com.garbage.classification.utils.StringUtils;
import com.github.pagehelper.util.StringUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
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
public class Test {

    public static void main(String[] args) throws IOException {
//        String urlInfo = "https://lajifenleiapp.com/sk/%E7%BA%B8";
//        URL url = new URL(urlInfo);
//        HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
//        InputStream is = httpUrl.getInputStream();
//        BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = br.readLine()) != null) {
//            //这里是对链接进行处理
//            line = line.replaceAll("</?a[^>]*>", "");
//            //这里是对样式进行处理
//            line = line.replaceAll("<(\\w+)[^>]*>", "<$1>");
//            sb.append(line);
//        }
//        System.out.println(sb);
//        is.close();
//        br.close();
////        StringBuilder sb = new StringBuilder();
////        sb.append("<!DOCTYPE html><html><head><title>纸 - 垃圾分类查询</title><meta><meta><meta><meta><meta><link><link> </head><body><script>\tfunction subsearch() {\t\twindow.location.href=\"/sk/\"+document.forms[\"form\"][\"inputv\"].value\t\treturn false;\t}</script><div>\t<div><div><img></div><div><h1>垃圾分类查询</h1></div></div>\t<br>\t<form>\t<div>\t<input>\t<span><button><strong><span></span> 查询</strong></button></span>\t</div>\t</form>\t<br>\t<div>\t\t\t<div>相关查询：\t\t\t\t 草稿纸 \t\t\t\tA4纸 \t\t\t\t白纸 \t\t\t\t包书纸 \t\t\t\t包装塑料纸 \t\t\t\t包装用纸 \t\t\t\t包装纸 \t\t\t\t包装纸盒 \t\t\t\t包装纸箱 \t\t\t\t报纸 \t\t\t\t报纸、纸皮 \t\t\t</div>\t</div><br><div>\t\t<div><h1><span>纸</span><span>&nbsp;属于&nbsp;</span><span>可回收物</span></h1></div>\t</div><br><div>\t<div>\t\t推荐使用 <span>微信小程序</span> 查询更方便<br>\t\t<img>\t</div></div><br> \t<div>\t\t<div><h3>可回收物是指</h3></div>\t\t<div>废纸张、废塑料、废玻璃制品、废金属、废织物等适宜回收、可循环利用的生活废弃物</div>\t</div>\t<div>\t\t<div><h3>可回收垃圾主要包括</h3></div>\t\t<div>酱油瓶、玻璃瓶、平板玻璃、易拉罐、饮料瓶、洗发水瓶、塑料玩具、书本、报纸、广告单、纸板箱、衣服、床上用品等</div>\t</div>\t<div>\t\t<div><h3>可回收物投放要求</h3></div>\t\t<ul>\t\t\t<li>轻投轻放</li>\t\t\t<li>清洁干燥，避免污染，费纸尽量平整</li>\t\t\t<li>立体包装物请清空内容物，清洁后压扁投放</li>\t\t\t<li>有尖锐边角的、应包裹后投放</li>\t\t</ul>\t</div></div>\t<br></div><footer>  <div>\t<p>Copyright 2018-2019 All Rights Reserved. 垃圾分类查询 垃圾分类</p>  </div></footer><script>var _hmt = _hmt || [];(function() {  var hm = document.createElement(\"script\");  hm.src = \"https://hm.baidu.com/hm.js?5d7f1f2dbe02b30900fb6d5f9f60fbf0\";  var s = document.getElementsByTagName(\"script\")[0];   s.parentNode.insertBefore(hm, s);})();</script><script>(function(){    var bp = document.createElement('script');    var curProtocol = window.location.protocol.split(':')[0];    if (curProtocol === 'https') {        bp.src = 'https://zz.bdstatic.com/linksubmit/push.js';    }    else {        bp.src = 'http://push.zhanzhang.baidu.com/push.js';    }    var s = document.getElementsByTagName(\"script\")[0];    s.parentNode.insertBefore(bp, s);})();</script></body></html>");
//        getGarbage(sb.toString().trim());

        try {
            Garbage garbage = GarbageCatchUtils.catchGarbage("西瓜");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }

//    static Pattern proInfo
//            = Pattern.compile("<span>(.*?)</span><span>(.*?)</span><span>(.*?)</span>", Pattern.DOTALL);
//
//    private static Garbage getGarbage(String str) {
//        //运用正则表达式对获取的网页源码进行数据匹配，提取我们所要的数据，在以后的过程中，我们可以采用httpclient+jsoup,
//        //现在暂时运用正则表达式对数据进行抽取提取
//        String[] info = str.split("</div>");
//        List<String> title = new ArrayList<>();
//        for (String s : info) {
//            Matcher m = proInfo.matcher(s);
//            Product p = null;
//            if (m.find()) {
////                有害垃圾 可回收物 湿垃圾(厨余垃圾)  干垃圾(其它垃圾)
//                String[] ss = m.group().trim().replace(" ", "").split("</span>");
//                Arrays.asList(ss).forEach(temp -> {
//                    Arrays.asList(temp.replace(" ", "").split("<span>")).forEach(name -> {
//                        if (StringUtil.isNotEmpty(name)) {
//                            title.add(name);
//                        }
//                    });
//                });
//            }
//        }
//        title.forEach(System.out::println);
//        Garbage garbage = null;
//        if (title.size() == 3) {
////            1 可回收 2 有害 3 湿垃圾 4 干垃圾
//            String type = title.get(2).trim();
//            Long index = "有害垃圾".equals(type) ? 2L : "可回收物".equals(type) ? 1L : "湿垃圾(厨余垃圾)".equals(type) ? 3L : "干垃圾(其它垃圾)".equals(type) ? 4L : 0L;
//            garbage = new Garbage(index, title.get(0));
//        }
//        return garbage;
//    }
}
