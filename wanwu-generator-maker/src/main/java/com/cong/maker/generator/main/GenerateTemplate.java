package com.cong.maker.generator.main;

import cn.hutool.core.io.FileUtil;
import com.cong.maker.generator.JarGenerator;
import com.cong.maker.generator.ScriptGenerator;
import com.cong.maker.generator.file.DynamicFileGenerator;
import com.cong.maker.meta.Meta;
import com.cong.maker.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * @author Cong
 */
public abstract class GenerateTemplate {
    public void doGenerate() throws TemplateException, IOException, InterruptedException {
        //获取Meta数据
        Meta meta = MetaManager.getMeta();
        //输出的根路径
        String propertyPath = System.getProperty("user.dir");
        String outputPath = propertyPath + File.separator + "generated" + File.separator + meta.getName();;

        if (!FileUtil.exist(outputPath)) {
            FileUtil.mkdir(outputPath);
        }
        //1、从原始模板文件路径复制到生成的代码包中
        String sourceCopyDestPath = copySource(meta, outputPath);

        // 2、生成代码
         generateCode(meta, propertyPath, outputPath);
        // 3、构建 jar 包
        String jarPath = buildJar(meta, outputPath);

        //4、 封装脚本
        String shellOutputFilePath = buildScript(outputPath, jarPath);

        //5、生成精简版的程序（产物包）
        buildDist(outputPath, sourceCopyDestPath, jarPath, shellOutputFilePath);

    }

    protected  void buildDist(String outputPath, String sourceCopyDestPath, String jarPath, String shellOutputFilePath) {
        String distOutPutPath = outputPath + "-dist";
        //拷贝jar包
        String targetAbsolutePath = distOutPutPath + File.separator + "target";
        FileUtil.mkdir(targetAbsolutePath);
        String jarAbsolutePath = outputPath + File.separator + jarPath;
        FileUtil.copy(jarAbsolutePath, targetAbsolutePath,true);
        //拷贝脚本文件
        FileUtil.copy(shellOutputFilePath, distOutPutPath,true);
        FileUtil.copy(shellOutputFilePath + ".bat", distOutPutPath,true);

        //拷贝源模板文件
        FileUtil.copy(sourceCopyDestPath, distOutPutPath,true);
    }

    protected  String  buildJar(Meta meta,String outputPath) throws InterruptedException, IOException {
        JarGenerator.doGenerate(outputPath);
        String jarName = String.format("%s-%s-jar-with-dependencies.jar", meta.getName(), meta.getVersion());
        String jarPath = "target/" + jarName;
        return jarPath;
    }
    /**
     * 封装脚本
     *
     * @param outputPath
     * @param jarPath
     * @return
     * @throws IOException
     */
    protected String buildScript(String outputPath, String jarPath) throws IOException {
        String shellOutputFilePath = outputPath + File.separator + "generator";
        ScriptGenerator.doGenerate(shellOutputFilePath, jarPath);
        return shellOutputFilePath;
    }
    protected  void generateCode(Meta meta, String propertyPath, String outputPath) throws IOException, TemplateException {
        //读取resources
        String inputResourcePath = Paths.get(propertyPath, "src", "main", "resources").toString();


        //java包的基础路径 com.cong=>com/cong
        String outputBasePackagePath = meta.getBasePackage().replace(".", File.separator);
        //generated/src/main/java/com/cong/
        String outputBaseJavaPackagePath = outputPath + File.separator + "src/main/java" + File.separator + outputBasePackagePath;

        String inputFilePath;
        String outputFilePath;
        //model.DataModel
        inputFilePath = inputResourcePath + File.separator + "templates/java/model/DataModel.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/model/DataModel.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // cli.command.ConfigCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/ConfigCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/ConfigCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // cli.command.GenerateCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/GenerateCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/GenerateCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // cli.command.ListCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/ListCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/command/ListCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // cli.CommandExecutor
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/CommandExecutor.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/cli/CommandExecutor.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // Main
        inputFilePath = inputResourcePath + File.separator + "templates/java/Main.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/Main.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // generator.DynamicGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/DynamicGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/DynamicGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // generator.MainGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/MainGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/MainGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // generator.StaticGenerator
        inputFilePath = inputResourcePath + File.separator + "templates/java/generator/StaticGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + "/generator/StaticGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // pom.xml
        inputFilePath = inputResourcePath + File.separator + "templates/pom.xml.ftl";
        outputFilePath = outputPath + File.separator + "pom.xml";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);
        // 生成README文件
        inputFilePath = inputResourcePath + File.separator + "templates/README.md.ftl";
        outputFilePath = outputPath + File.separator + "README.md";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);
    }

    protected  String copySource(Meta meta, String outputPath) {
        //从原始模板文件路径复制到生成的代码包中
        String sourceRootPath = meta.getFileConfig().getSourceRootPath();
        String sourceCopyDestPath = outputPath + File.separator + ".source";
        FileUtil.copy(sourceRootPath, sourceCopyDestPath, false);
        return sourceCopyDestPath;
    }
}
