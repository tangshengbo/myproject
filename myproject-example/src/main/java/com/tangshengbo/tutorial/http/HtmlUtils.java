package com.tangshengbo.tutorial.http;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tangshengbo
 *
 * @author Tangshengbo
 * @date 2019/10/9
 */
public final class HtmlUtils {

    private static final Pattern REGEXP_IMG_PATTEN = Pattern.compile("(<img.*>)", Pattern.CASE_INSENSITIVE);

    private static final Pattern REGEXP_IMG_URL_PATTEN = Pattern.compile("src=\"?(.*?)([\">])");

    private HtmlUtils() {

    }

    /**
     * 得到网页中图片的地址
     *
     * @param html 网页文本
     * @return 图片地址列表
     */
    public static List<String> getImgUrl(String html) {
        List<String> pics = new ArrayList<>();
        String img;
        Matcher mImage;
        mImage = REGEXP_IMG_PATTEN.matcher(html);
        while (mImage.find()) {
            // 得到<img />数据
            img = mImage.group();
            // 匹配<img>中的src数据
            Matcher m = REGEXP_IMG_URL_PATTEN.matcher(img);
            while (m.find()) {
                pics.add(m.group(1));
            }
        }
        return pics;
    }
}
