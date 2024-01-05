package com.cong.maker.generator.file;

import cn.hutool.core.io.FileUtil;

/**
 * 静态文件生成器
 *
 * @author 86188
 * @date 2023/11/11
 */
public class StaticFileGenerator {
    /**
     * 复制文件 由 hutool 提供(会将输入目录完成拷贝到输出目录下)
     *
     * @param inputPath 输入路径
     * @param outPath   输出路径
     */
    public static void copyFileByHutool(String inputPath, String outPath) {
        FileUtil.copy(inputPath, outPath, false);
    }



}
