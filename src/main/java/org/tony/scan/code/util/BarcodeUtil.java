package org.tony.scan.code.util;

import org.apache.commons.lang3.StringUtils;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @version 1.0.0
 * @Description 条形码工具类
 * @Date 2017/11/21
 * @Author xuanyi
 * @pageageName is org.tony.scan.code.util
 * @projectName is ScanCodeGunDemo
 */
public class BarcodeUtil {
    /**
     * 生成文件
     *
     * @param msg
     * @param path
     * @return
     */
    public File generateFiles(String msg, String path) {
        File file = new File(path);
        try {
            generates(msg, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    /**
     * 生成字节
     *
     * @param msg
     * @return
     * @throws IOException
     */
    public  byte[] generates(String msg) throws IOException {
        ByteArrayOutputStream ous = new ByteArrayOutputStream();
        generates(msg, ous);
        ous.close();
        return ous.toByteArray();
    }

    /**
     * 生成到流
     *
     * @param msg
     * @param ous
     */
    public static  void generates(String msg, OutputStream ous) {
        if (StringUtils.isEmpty(msg) || ous == null) {
            return;
        }
        // 条形码类型
        //Code39Bean bean = new Code39Bean();
        Code128Bean bean = new Code128Bean();
        // 精细度
        final int dpi = 150;
        // module宽度
        final double moduleWidth = UnitConv.in2mm(2.0f / dpi);

        // 配置对象
        bean.setModuleWidth(moduleWidth);
        bean.doQuietZone(false);

        String format = "image/png";
        try {

            // 输出到流
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format, dpi, BufferedImage.TYPE_BYTE_BINARY,
                    false, 0);

            // 生成条形码
            bean.generateBarcode(canvas, msg);

            // 结束绘制
            canvas.finish();
            ous.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
