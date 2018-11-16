package com.maxcar.base.util.wechat;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * 第三方制作二维码工具类
 * yangshujun
 */
public class ZxingKitUtil {
    private static Logger log = LoggerFactory.getLogger(ZxingKitUtil.class);

    /**
     * Zxing图形码生成工具
     *
     * @param contents
     *            内容
     * @param barcodeFormat
     *            BarcodeFormat对象
     * @param format
     *            图片格式，可选[png,jpg,bmp]
     * @param width
     *            宽
     * @param height
     *            高
     * @param margin
     *            边框间距px
     * @param saveImgFilePath
     *            存储图片的完整位置，包含文件名
     * @return
     */
    public static Boolean encode(String contents, BarcodeFormat barcodeFormat, Integer margin,
                                 ErrorCorrectionLevel errorLevel, String format, int width, int height, String saveImgFilePath) {
        Boolean bool = false;
        BufferedImage bufImg;
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        // 指定纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, errorLevel);
        hints.put(EncodeHintType.MARGIN, margin);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        try {
            // contents = new String(contents.getBytes("UTF-8"), "ISO-8859-1");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, barcodeFormat, width, height, hints);
            MatrixToImageConfig config = new MatrixToImageConfig(0xFF000001, 0xFFFFFFFF);
            bufImg = MatrixToImageWriter.toBufferedImage(bitMatrix, config);
            bool = writeToFile(bufImg, format, saveImgFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bool;
    }

    /**
     * @param srcImgFilePath
     *            要解码的图片地址
     * @return
     */
    @SuppressWarnings("finally")
    public static Result decode(String srcImgFilePath) {
        Result result = null;
        BufferedImage image;
        try {
            File srcFile = new File(srcImgFilePath);
            image = ImageIO.read(srcFile);
            if (null != image) {
                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                Hashtable<DecodeHintType, String> hints = new Hashtable<DecodeHintType, String>();
                hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
                result = new MultiFormatReader().decode(bitmap, hints);
            } else {
                log.debug("Could not decode image.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    /**
     * 将BufferedImage对象写入文件
     *
     * @param bufImg
     *            BufferedImage对象
     * @param format
     *            图片格式，可选[png,jpg,bmp]
     * @param saveImgFilePath
     *            存储图片的完整位置，包含文件名
     * @return
     */
    @SuppressWarnings("finally")
    public static Boolean writeToFile(BufferedImage bufImg, String format, String saveImgFilePath) {
        Boolean bool = false;
        try {
            bool = ImageIO.write(bufImg, format, new File(saveImgFilePath));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return bool;
        }
    }

    public static void main(String[] args) {
        String saveImgFilePath = "D://zxing.png";
        Boolean encode = encode("weixin://wxpay/bizpayurl?sign=CD14BBAB76059804DC7741AE8F9EB1E2appid=wxcf5003ae31ab9bbf&mch_id=1513688731&nonce_str=Xs88mT0OiJSmkN5ptbxp&product_id=20180906&time_stamp=1536292767&key=YLpaibo8618176438893861817643889",
                BarcodeFormat.QR_CODE, 3, ErrorCorrectionLevel.H, "png", 200, 200,
                saveImgFilePath);
        if (encode) {
            Result result = decode(saveImgFilePath);
            String text = result.getText();
            System.out.println(text);
        }
    }
}
