package com.tangshengbo.net.url;

import com.tangshengbo.net.address.InetAddressTest;
import jodd.util.ThreadUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Tangshengbo on 2018/2/27.
 */
public class URLTest {

    private static final Logger logger = LoggerFactory.getLogger(InetAddressTest.class);

    @Test
    public void testURL() throws Exception {
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
}
