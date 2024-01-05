package com.cong.generator;

import com.cong.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * 核心生成器
 */
public class MainGenerator {

    /**
     * 生成
     *
     * @param model 数据模型
     * @throws TemplateException
     * @throws IOException
     */
    public static void doGenerate(Object model) throws TemplateException, IOException {
        String inputRootPath = "D:\\develop\\wanwu-generator\\wanwu-generator-demo-project\\acm-template-pro";
        String outputRootPath = "D:\\develop\\wanwu-generator\\wanwu-generator-basic";

        String inputPath;
        String outputPath;
        inputPath = new File(inputRootPath,"/src/com/cong/acm/MainTemplate.java.ftl").getAbsolutePath();
        outputPath = new File(inputRootPath,"/src/com/cong/acm/MainTemplate.java").getAbsolutePath();

        DynamicGenerator.doGenerate(inputPath, outputPath, model);

        inputPath = new File(inputRootPath,".gitignore").getAbsolutePath();
        outputPath = new File(inputRootPath,".gitignore").getAbsolutePath();
        StaticGenerator.copyFilesByRecursive(inputPath, outputPath);

        inputPath = new File(inputRootPath,"README.md").getAbsolutePath();
        outputPath = new File(inputRootPath,"README.md").getAbsolutePath();
        StaticGenerator.copyFilesByRecursive(inputPath, outputPath);

    }

    public static void main(String[] args) throws TemplateException, IOException {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("cong");
        mainTemplateConfig.setLoop(true);
        mainTemplateConfig.setOutputText("运算求和结果：");
        doGenerate(mainTemplateConfig);
    }
}