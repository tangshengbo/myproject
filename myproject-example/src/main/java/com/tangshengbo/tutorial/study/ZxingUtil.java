package com.tangshengbo.tutorial.study;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;

/**
 * Created by TangShengBo on 2018/2/23.
 */
public class ZxingUtil {


    /**
     * 生成二维码
     *
     * @param width
     * @param height
     * @param margin
     * @param level
     * @param content
     * @return
     * @throws WriterException
     */
    private static BitMatrix getBitMatrix(int width, int height, int margin, ErrorCorrectionLevel level, String content)
            throws WriterException {
        QRCodeWriter writer = new QRCodeWriter();
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, level);// 纠错等级L,M,Q,H
        hints.put(EncodeHintType.MARGIN, margin); // 边距
        return writer.encode(content, BarcodeFormat.QR_CODE, height, width, hints);
    }

    public static Image createZxing(int width, int height, int margin, ErrorCorrectionLevel level, String content) {
        try {
            BitMatrix bitMatrix = getBitMatrix(width, height, margin, level, content);
            //将二维码生成图片对象
            return MatrixToImageWriter.toBufferedImage(bitMatrix);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取二维码
     *
     * @throws IOException
     * @throws NotFoundException
     */
    public static String readZxing(String qrcodeUrl) throws IOException, NotFoundException {
        MultiFormatReader read = new MultiFormatReader();
        URL url = new URL(qrcodeUrl);
        HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
        httpUrl.connect();
        BufferedImage image = ImageIO.read(httpUrl.getInputStream());
        Binarizer binarizer = new HybridBinarizer(new BufferedImageLuminanceSource(image));
        BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
        Result res = read.decode(binaryBitmap);
        return res.toString();
    }
}