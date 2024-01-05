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
public class MainGenerator extends GenerateTemplate {
    @Override
    protected void buildDist(String outputPath, String sourceCopyDestPath, String jarPath, String shellOutputFilePath) {
        System.out.println("不生成 dist精简版");
    }


}
