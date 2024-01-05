package ${basePackage}.generator;

import cn.hutool.core.io.FileUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.Locale;

/**
 * 动态文件生成
 *
 * @author ${author}
 */
public class DynamicGenerator {

    /**
     * 生成文件
     *
     * @param inputPath 模板文件输入路径
     * @param outputPath 输出路径
     * @param model 数据模型
     * @throws IOException
     * @throws TemplateException
     */
    public static void doGenerate(String inputPath, String outputPath, Object model) throws IOException, TemplateException {
        // new 出 Configuration 对象，参数为 FreeMarker 版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        // 指定模板文件所在的路径
        File templateDir = new File(inputPath).getParentFile();
        configuration.setDirectoryForTemplateLoading(templateDir);

        // 设置模板文件使用的字符集
        configuration.setDefaultEncoding("UTF-8");
        configuration.setNumberFormat("0.######");
        configuration.setEncoding(new Locale("zh_CN"),"utf-8");

        // 创建模板对象，加载指定模板
        String templateName = new File(inputPath).getName();
        Template template = configuration.getTemplate(templateName);
        //如果文件不存在创建目录
         if (!FileUtil.exist(outputPath)){
             FileUtil.touch(outputPath);
         }
        // 生成（修复中文乱码问题）
        Writer out = new OutputStreamWriter(new FileOutputStream(outputPath), "UTF-8");
        template.process(model, out);

        // 生成文件后别忘了关闭哦
        out.close();
    }

}