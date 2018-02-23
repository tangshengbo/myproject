package com.tangshengbo.tutorial.study;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by TangShengBo on 2018/2/11.
 */
public class Frame1 {
    public static void main(String[] args) {
        new FirstFrame().setVisible(true);
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
        name = new JTextField("姓   名", 10);
        //按钮
        JButton b = new JButton("传递");
        //添加按钮事件
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new SecondFrame(FirstFrame.this).setVisible(true);
                FirstFrame.this.setVisible(false);
            }
        });
        JPanel pane = new JPanel();
        pane.add(a);
        pane.add(name);
        pane.add(b);
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

