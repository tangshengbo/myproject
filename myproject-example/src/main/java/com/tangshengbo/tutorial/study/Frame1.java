package com.tangshengbo.tutorial.study;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;

/**
 * Created by TangShengBo on 2018/2/11.
 */
public class Frame1 {
    public static void main(String[] args) {
        try {

            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");//Nimbus风格，jdk6 update10版本以后的才会出现
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//当前系统风格
//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");//Motif风格，是蓝黑
//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());//跨平台的Java风格
//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");//windows风格
//UIManager.setLookAndFeel("javax.swing.plaf.windows.WindowsLookAndFeel");//windows风格
//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");//java风格
//      UIManager.setLookAndFeel("com.apple.mrj.swing.MacLookAndFeel");//待考察，
            UIManager.LookAndFeelInfo[] lfs = getAllLAFs();
            for (UIManager.LookAndFeelInfo feelInfo : lfs) {
                System.out.println(feelInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        new FirstFrame().setVisible(true);
    }

    public static UIManager.LookAndFeelInfo[] getAllLAFs() {
        UIManager.LookAndFeelInfo[] lafs = UIManager.getInstalledLookAndFeels();
        int i = lafs.length;
        UIManager.LookAndFeelInfo[] lafsAll = new UIManager.LookAndFeelInfo[i + 2];
        System.arraycopy(lafs, 0, lafsAll, 0, i);
        lafsAll[i++] = new UIManager.LookAndFeelInfo("CrossPlatform", UIManager.getCrossPlatformLookAndFeelClassName());
        lafsAll[i++] = new UIManager.LookAndFeelInfo("System", UIManager.getSystemLookAndFeelClassName());
        return lafsAll;
    }
}

class FirstFrame extends JFrame {
    JTextField name;

    public FirstFrame() {
        super("窗体之间数据传递");
        this.setSize(330, 200);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        JLabel a = new JLabel("姓名:");
        JLabel createImg = new JLabel();
        //定义图形验证码的长和宽
//        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
//        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 20);
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 100, 4, 4);
        //图形验证码写出，可以写出到文件，也可以写出到流
        ImageIcon captchaImg = new ImageIcon(captcha.getImage());
        captchaImg.setImage(captchaImg.getImage().getScaledInstance(70, 30, Image.SCALE_DEFAULT));
        createImg.setIcon(captchaImg);
        Locale locale = new Locale("zh", "CN");
        name = new JTextField(locale.getDisplayName(), 20);
        //按钮
        JButton b = new JButton("传递");
        //添加按钮事件
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                FirstFrame.this.name.setText(captcha.getCode());
                new SecondFrame(FirstFrame.this).setVisible(true);
                FirstFrame.this.setVisible(false);
            }
        });
        JPanel pane = new JPanel();
        pane.add(a);
        pane.add(name);
        pane.add(b);
        pane.add(createImg);
        setContentPane(pane);
    }
}

class SecondFrame extends JFrame {
    public SecondFrame(FirstFrame frm) {
        super("显示数据");
        this.setSize(330, 300);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        JLabel a = new JLabel(frm.name.getText(), 10);
        JLabel qrCode = new JLabel();
        int width = 200;
        int height = 200; //这是图片和JLable的宽度和高度
        qrCode.setSize(width, height);
        Image bufferedImage = ZxingUtil.createZxing(width, height, 1, ErrorCorrectionLevel.H, frm.name.getText());
        ImageIcon image = new ImageIcon(bufferedImage);
        image.setImage(image.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        qrCode.setIcon(image);
//        qrCode.setIcon(new ImageIcon(ZxingUtil.));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel pane = new JPanel();
        pane.add(a);
        pane.add(qrCode);
        setContentPane(pane);
        //关闭窗口
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);//退出
            }
        });
    }
}

