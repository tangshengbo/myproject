/**
 * @author Administrator
 * @author Administrator
 */
/**
 * @author Administrator
 *
 */
package com.tangshengbo.util.baofoo.Demo;

import com.alibaba.dubbo.common.utils.NetUtils;
import com.tangshengbo.util.baofoo.http.HttpUtil;
import com.tangshengbo.util.baofoo.util.SecurityUtil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
 * 下载对账文件接口测试用例
 * 本例将对账文件下载到本地。
 * 用户通过解压文件，并解析压缩文件内的内容，
 * 本例公供学习宝付下载对账文件使用，以下代码公供参考
 *
 * 测试前请联系宝付，绑定一个测试IP
 * 作者：宝付技术（大圣）
 * 日期：2016-01
 */
public class TestDemo {

    public static void main(String[] args) throws IOException {


        Map<String, String> poststr = new HashMap<>();


        poststr.put("version", "4.0.0.2");
        poststr.put("member_id", "1203634");//商户号
        poststr.put("file_type", "fi");//收款：fi   出款：fo
        poststr.put("client_ip", NetUtils.getIpByHost("www.goldman.houbank.com"));//要与服务器IP保持一致
        poststr.put("settle_date", "2018-01-20");//指定日期的对帐文件（除当天）

        String request_url = "https://vgw.baofoo.com/boas/api/fileLoadNewRequest";//测试请求地址
        request_url = "https://public.baofoo.com/boas/api/fileLoadNewRequest";

        String PostString = HttpUtil.post(request_url, poststr);

        log("返回：" + PostString);
        String[] splitstr = PostString.split("=");    //解板返回的文件参数
        int StrOf = PostString.indexOf("resp_code=0000");
        if (StrOf < 0) {
            log("下载失败!");
            return;
        }
        byte[] Restr = SecurityUtil.Base64Decode(splitstr[3]);//进行base64解码，解密后为byte类型
        String filename = "d:/" + getDateDay() + ".zip";    //存在本地的路径（自行设置）
        log("存放路径：" + filename);
        InputStream DateByte = new ByteArrayInputStream(Restr);//把获取的zip文件的byte放入输入流



//        File targetFile = new File(filename);
//        targetFile.createNewFile(); //创建文件
//        OutputStream outStream = new FileOutputStream(targetFile);
//        byte[] by = new byte[1024];
//        while (DateByte.available() > 0) {
//            DateByte.read(by); //读取接收的文件流
//            outStream.write(by); //写入文件
//        }
//        DateByte.close();
//        outStream.flush();
//        outStream.close();
//        log("下载成功！");
    }


    public static void log(String msg) {
        System.out.println(getTime() + "\t: " + msg);
    }

    private static String getTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    private static String getDateDay() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static String getIP(){
        String Ip = null;
        try {
            Ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return Ip;
    }

}