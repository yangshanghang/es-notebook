package com.icinfo.lpsp.notebook.common.util;

import com.icinfo.plugin.hxpackincrement.HxPack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 汇信增量打包
 */
public class HxPackIncrement {

    private static Logger logger = LoggerFactory.getLogger(HxPackIncrement.class);

    public static void main(String[] args) {
        try {
            logger.info("增量打包开始........");
            HxPack.doPack("hxpack"); //运行打包程序
            logger.info("增量打包结束........");
        } catch (Exception e) {
            logger.error("增量打包出现异常:{}", e);
        }
    }
}