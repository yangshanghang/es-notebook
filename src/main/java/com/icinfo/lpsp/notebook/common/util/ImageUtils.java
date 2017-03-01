package com.icinfo.lpsp.notebook.common.util;

import com.alibaba.simpleimage.ImageRender;
import com.alibaba.simpleimage.SimpleImageException;
import com.alibaba.simpleimage.render.ReadRender;
import com.alibaba.simpleimage.render.ScaleParameter;
import com.alibaba.simpleimage.render.ScaleRender;
import com.alibaba.simpleimage.render.WriteRender;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * 图片工具
 * Created by yushunwei on 2016/11/19.
 */
public class ImageUtils {

    private static Logger looger = LoggerFactory.getLogger(ImageUtils.class);

    /**
     * 图片缩放
     *
     * @param in         输入流
     * @param scaleWidth 缩放后的宽度
     * @param scaleHeiht 缩放后的高度
     * @param outStream  输出
     */
    public static void scale(InputStream in, int scaleWidth, int scaleHeiht, FileOutputStream outStream) {
        ScaleParameter scaleParam = new ScaleParameter(scaleWidth, scaleHeiht);
        WriteRender wr = null;
        try {
            ImageRender rr = new ReadRender(in);
            ImageRender sr = new ScaleRender(rr, scaleParam);
            wr = new WriteRender(sr, outStream);
            wr.render();
        } catch (Exception e) {
            looger.error("图片缩放异常！", e);
        } finally {
            IOUtils.closeQuietly(in);
            if (wr != null) {
                try {
                    wr.dispose();
                } catch (SimpleImageException ignore) {
                    looger.error("图片缩放异常！", ignore);
                }
            }
        }
    }
}
