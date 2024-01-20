package com.cong.maker.meta;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.cong.maker.meta.enums.FileGenerateTypeEnum;
import com.cong.maker.meta.enums.FileTypeEnum;
import com.cong.maker.meta.enums.ModelTypeEnum;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Cong
 */
public class MetaValidator {
    public static void doValidateAndFill(Meta metaData) {
        //基础信息校验和默认值
        validAndFillMetaRoot(metaData);
        //fileConfig 校验和默认值
        validAndFillFileConfig(metaData);
        //modelConfig 校验和默认值
        validAndFillModelConfig(metaData);
    }

    private static void validAndFillModelConfig(Meta metaData) {
        //modelConfig 校验和默认值
        Meta.ModelConfig modelConfig = metaData.getModelConfig();
        if (modelConfig == null) {
            return;
        }
        List<Meta.ModelConfig.ModelInfo> modelInfoList = modelConfig.getModels();
        if (CollUtil.isEmpty(modelInfoList)) {
            return;
        }
        for (Meta.ModelConfig.ModelInfo modelInfo : modelInfoList) {
            //为group 不校验
            String groupKey = modelInfo.getGroupKey();
            if (StrUtil.isNotBlank(groupKey)){
                //生成中间参数 "--author"
                List<Meta.ModelConfig.ModelInfo> subModelInfoList = modelInfo.getModels();
                String allArgsStr = subModelInfoList.stream().map(item -> String.format("\"--%s\"", item.getFieldName())).collect(Collectors.joining(","));
                modelInfo.setAllArgsStr(allArgsStr);
                continue;
            }
            String fieldName = modelInfo.getFieldName();
            if (StrUtil.isBlank(fieldName)) {
                throw new RuntimeException("未填写 fieldName");
            }
            String modelInfoType = modelInfo.getType();
            if (StrUtil.isEmpty(modelInfoType)) {
                modelInfo.setType(ModelTypeEnum.STRING.getValue());
            }
        }
    }

    private static void validAndFillFileConfig(Meta metaData) {
        //fileConfig 校验和默认值
        Meta.FileConfig fileConfig = metaData.getFileConfig();
        if (fileConfig == null) {
            return;
        }
        //默认generated
        String outputRootPath = fileConfig.getOutputRootPath();
        // 默认值
        if (StrUtil.isEmpty(outputRootPath)) {
            outputRootPath = "generated";
            fileConfig.setOutputRootPath(outputRootPath);
        }

        //必填
        String sourceRootPath = fileConfig.getSourceRootPath();
        if (StrUtil.isBlank(sourceRootPath)) {
            throw new MetaException("未填写sourceRootPath");
        }
        // inputRootPath：.source + sourceRootPath 的最后一个层级路径
        String inputRootPath = fileConfig.getInputRootPath();
        String defaultInputRootPath = ".source/" + FileUtil.getLastPathEle(Paths.get(sourceRootPath)).getFileName().toString();
        // 默认值
        if (StrUtil.isEmpty(inputRootPath)) {
            fileConfig.setInputRootPath(defaultInputRootPath);
        }
        String fileConfigType = fileConfig.getType();
        String defaultType = FileTypeEnum.DIR.getValue();
        if (StrUtil.isEmpty(fileConfigType)) {
            fileConfig.setType(defaultType);
        }

        List<Meta.FileConfig.FileInfo> fileInfoList = fileConfig.getFiles();
        if (CollUtil.isEmpty(fileInfoList)) {
            return;
        }
        for (Meta.FileConfig.FileInfo fileInfo : fileInfoList) {
            //type:默认inputPath  有文件后缀 默认为file 否则就是dir
            String type = fileInfo.getType();
            if (FileTypeEnum.GROUP.getValue().equals(type)){
                continue;
            }
            //必填
            String inputPath = fileInfo.getInputPath();
            if (StrUtil.isBlank(inputPath)) {
                throw new MetaException("未填写inputPath");
            }
            //默认等于inputPath
            String outputPath = fileInfo.getOutputPath();
            if (StrUtil.isBlank(outputPath)) {
                fileInfo.setOutputPath(inputPath);
            }

            if (StrUtil.isBlank(type)) {
                //无文件后缀
                if (StrUtil.isBlank(FileUtil.getSuffix(inputPath))) {
                    fileInfo.setType(FileTypeEnum.DIR.getValue());
                } else {
                    fileInfo.setType(FileTypeEnum.FILE.getValue());
                }
            }
            // 文件结尾 不为ftl generateType 为static 否则为dynamic
            String generateType = fileInfo.getGenerateType();
            if (StrUtil.isBlank(generateType)) {
                if (inputPath.endsWith(".ftl")) {
                    fileInfo.setGenerateType(FileGenerateTypeEnum.DYNAMIC.getValue());
                } else {
                    fileInfo.setGenerateType(FileGenerateTypeEnum.STATIC.getValue());
                }
            }

        }

    }

    private static void validAndFillMetaRoot(Meta metaData) {
        //基础信息校验和默认值
        String name = StrUtil.blankToDefault(metaData.getName(), "my-generator");
        String description = StrUtil.emptyToDefault(metaData.getDescription(), "我的模板代码生成器");
        String author = StrUtil.emptyToDefault(metaData.getAuthor(), "wanwu");
        String basePackage = StrUtil.blankToDefault(metaData.getBasePackage(), "com.wanwu");
        String version = StrUtil.emptyToDefault(metaData.getVersion(), "1.0");
        String createTime = StrUtil.emptyToDefault(metaData.getCreateTime(), DateUtil.now());
        metaData.setName(name);
        metaData.setDescription(description);
        metaData.setAuthor(author);
        metaData.setBasePackage(basePackage);
        metaData.setVersion(version);
        metaData.setCreateTime(createTime);
    }
}