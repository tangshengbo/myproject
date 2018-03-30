package com.tangshengbo.net.url;

import com.tangshengbo.net.address.InetAddressTest;
import jodd.util.ThreadUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;

/**
 * Created by Tangshengbo on 2018/2/27.
 */
public class URLTest {

    private static final Logger logger = LoggerFactory.getLogger(InetAddressTest.class);

    @Test
    public void testURL() throws Exception {
        URL url = new URL("https://www.cnblogs.com/hellochennan/?q=11#sss");
        logger.info("{}", url.getHost());
        logger.info("{}", url.getContent());
        logger.info("{}", url.getFile());
        logger.info("{}", url.getPath());
        logger.info("{}", url.getAuthority());
        logger.info("{}", url.getProtocol());
        logger.info("{}", url.getPort());
        logger.info("{}", url.getDefaultPort());
        logger.info("{}", url.getQuery());
        logger.info("{}", url.getRef());
        logger.info("{}", url.getUserInfo());
        URI uri = url.toURI();
        logger.info("{}", uri);
        logger.info("{}", uri.toURL().toExternalForm());
        URL fileUrl = new URL("file:///E:/%E6%96%B0%E5%BB%BA%E6%96%87%E6%9C%AC%E6%96%87%E6%A1%A3.html");
        logger.info("{}", IOUtils.toString(fileUrl.openStream(), "GBK"));

        URL download = new URL("http://localhost:8085/portal/account/download-file/tt");
        byte[] data = IOUtils.toByteArray(download);
        FileUtils.writeByteArrayToFile(new File("E:/spring-mvc.xml"), data);
//        logger.info("{}", IOUtils.toString(download.openStream(), "UTF-8"));
    }

    @Test
    public void testURI() throws Exception {
        URI uri = new URI("https://www.cnblogs.com/hellochennan/?q=唐声波#sss");
        logger.info("{}", uri.getHost());
        logger.info("{}", uri.toASCIIString());
        logger.info("{}", uri);
    }

    @Test
    public void testURLEncoder() throws Exception {
//        urlEncode();
        QueryString queryString = new QueryString();
        queryString.add("姓名", "唐声波");
        queryString.add("id", "123");
        logger.info("{}", queryString.getQuery());
        String encode = URLEncoder.encode("新建文本文档", "UTF-8");
        String decode = URLDecoder.decode(encode, "UTF-8");
        logger.info("{}, {}", encode, decode);
    }

    private void urlEncode() throws UnsupportedEncodingException {
        logger.info("{}", URLEncoder.encode("This string has spaces",
                "UTF-8"));
        logger.info("{}", URLEncoder.encode("This*string*has*asterisks",
                "UTF-8"));
        logger.info("{}", URLEncoder.encode("This%string%has%percent%signs",
                "UTF-8"));
        logger.info("{}", URLEncoder.encode("This+string+has+pluses",
                "UTF-8"));
        logger.info("{}", URLEncoder.encode("This/string/has/slashes",
                "UTF-8"));
        logger.info("{}", URLEncoder.encode("This\"string\"has\"quote\"marks",
                "UTF-8"));
        logger.info("{}", URLEncoder.encode("This:string:has:colons",
                "UTF-8"));
        logger.info("{}", URLEncoder.encode("This~string~has~tildes",
                "UTF-8"));
        logger.info("{}", URLEncoder.encode("This(string)has(parentheses)",
                "UTF-8"));
        logger.info("{}", URLEncoder.encode("This.string.has.periods",
                "UTF-8"));
        logger.info("{}", URLEncoder.encode("This=string=has=equals=signs",
                "UTF-8"));
        logger.info("{}", URLEncoder.encode("This&string&has&ampersands",
                "UTF-8"));
        logger.info("{}", URLEncoder.encode("Thiséstringéhasé non-ASCII characters", "UTF-8"));
    }

    @Test
    public void downloadURL() throws IOException {
        URL baseUrl = new URL("https", "aecpm.alicdn.com", "");
        URL url = new URL(baseUrl, "/simba/img/TB1W4nPJFXXXXbSXpXXSutbFXXX.jpg");
        BufferedImage img = ImageIO.read(url);
        ImageIO.write(img, "jpg", new File("E:/xx.jpg"));
        new ImageFrame(img).setVisible(true);
        ThreadUtil.sleep(10000);
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream(), "UTF-8"))) {
//            for (String line = br.readLine(); Objects.nonNull(line); line = br.readLine()) {
//                logger.info("{}", line);
//            }
//        }
//        c.disconnect();
    }

    @Test
    public void testSupportedProtocol() {
        // hypertext transfer protocol
        testProtocol("http://www.adc.org");

        // secure http
        testProtocol("https://www.amazon.com/exec/obidos/order2/");

        // file transfer protocol
        testProtocol("ftp://ibiblio.org/pub/languages/java/javafaq/");

        // Simple Mail Transfer Protocol
        testProtocol("mailto:elharo@ibiblio.org");

        // telnet
        testProtocol("telnet://dibner.poly.edu/");

        // local file access
        testProtocol("file:///etc/passwd");

        // gopher
        testProtocol("gopher://gopher.anc.org.za/");

        // Lightweight Directory Access Protocol
        testProtocol(
                "ldap://ldap.itd.umich.edu/o=University%20of%20Michigan,c=US?postalAddress");

        // JAR
        testProtocol(
                "jar:http://cafeaulait.org/books/javaio/ioexamples/javaio.jar!"
                        + "/com/macfaq/io/StreamCopier.class");

        // NFS, Network File System
        testProtocol("nfs://utopia.poly.edu/usr/tmp/");

        // a custom protocol for JDBC
        testProtocol("jdbc:mysql://luna.ibiblio.org:3306/NEWS");

        // rmi, a custom protocol for remote method invocation
        testProtocol("rmi://ibiblio.org/RenderEngine");

        // custom protocols for HotJava
        testProtocol("doc:/UsersGuide/release.html");
        testProtocol("netdoc:/UsersGuide/release.html");
        testProtocol("systemresource://www.adc.org/+/index.html");
        testProtocol("verbatim:http://www.adc.org/");
    }

    private void testProtocol(String url) {
        try {
            URL u = new URL(url);
            logger.info("{}", u.getProtocol() + " is supported");
            logger.info("{}", u);
        } catch (MalformedURLException ex) {
            String protocol = url.substring(0, url.indexOf(':'));
            logger.info("{}", protocol + " is not supported");
        }
    }

    private class ImageFrame extends JFrame {

        public ImageFrame(Image image) {
            this.setLayout(null);
            this.setLocationRelativeTo(null);
            this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
            this.setLocation(0, 0);
            JPanel pane = new JPanel();
            JLabel httpImg = new JLabel();
            JLabel createImg = new JLabel();
            //得到源图宽
            int width = image.getWidth(null);
            //得到源图长
            int height = image.getHeight(null);
            ImageIcon imageIcon = new ImageIcon(image);
            imageIcon.setImage(imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
            httpImg.setIcon(imageIcon);
            pane.add(httpImg);
            setContentPane(pane);
            //关闭窗口
            this.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.exit(0);//退出
                }
            });
        }
    }

    @Test
    public void testURLConnection() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 1);
        jsonObject.put("name", "JavaWeb糖果");
        jsonObject.put("money", 11.2);
        jsonObject.put("createDate", "2017-09-06 11:10:53");

        logger.info("{}", jsonObject.toString());
        String body = jsonObject.toString();
        body = "id&=1&name=JavaWeb&money=11.2&createDate=2017-09-06 11:10:53";
        String url = "http://localhost:8085/portal/account/save-body";
        url = "http://localhost:8085/portal/account/save-urlencoded";
        String contentType = "application/json;charset=UTF-8";
        contentType = "application/x-www-form-urlencoded";
        sendPostByURLConnection(url, body, contentType);
    }

    private void sendPostByURLConnection(String urlStr, String body, String contentType) throws IOException {
        URL url = new URL(urlStr);
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", contentType);
        BufferedOutputStream os = new BufferedOutputStream(conn.getOutputStream());
        os.write(body.getBytes());
        os.flush();
        logger.info("header {} ", conn.getHeaderFields());
        logger.info("body {} ", IOUtils.toString(conn.getInputStream(), "UTF-8"));
    }
}
