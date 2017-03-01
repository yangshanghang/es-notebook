package com.icinfo.lpsp.notebook.common.util;

import java.io.File;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 文件工具类
 * Created by yushunwei on 2016/9/28.
 */
public class FileUtils extends org.apache.commons.io.FileUtils {

    /**
     * 生成临时文件
     *
     * @return 生成的临时文件
     */
    public static File createTempFile() {
        File tempDir = new File(System.getProperty("java.io.tmpdir"));
        String tempFileName = String.format("upload_%s_%s.tmp", new Object[]{UUID.randomUUID().toString().replace('-', '_'), getUniqueId()});
        return new File(tempDir, tempFileName);
    }

    /**
     * 获取递增数
     * @return 100000000以内递增数
     */
    private static String getUniqueId() {
        int current = new AtomicInteger(0).getAndIncrement();
        String id = Integer.toString(current);
        if (current < 100000000) {
            id = ("00000000" + id).substring(id.length());
        }

        return id;
    }

    /**
     * 删除文件
     * @param file 需要删除的文件
     */
    public static void deleteFile(File file) {
        if (file != null && file.exists()) {
            file.delete();
        }
    }
}
