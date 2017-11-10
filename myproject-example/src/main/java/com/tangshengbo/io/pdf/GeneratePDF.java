package com.tangshengbo.io.pdf;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.commons.lang.time.DateFormatUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * Created by TangShengBo on 2017-11-09.
 */
public class GeneratePDF {

    public static void main(String[] args) {
        printPDF();
        captureScreen();
    }

    private static void printPDF() {
        try (OutputStream file = new FileOutputStream(new File("E:/Test.pdf"))) {
            Document document = new Document();
            PdfWriter.getInstance(document, file);
            document.open();
            document.add(new Paragraph("Hello ShengBo Tang"));
            document.add(new Paragraph(DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date())));
            document.close();
            System.out.println("OK!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void captureScreen()  {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot;
        try {
            robot = new Robot();
            BufferedImage image = robot.createScreenCapture(screenRectangle);
            ImageIO.write(image, "png", new File("E:/captureScreen.png"));
            System.out.println("OK!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
