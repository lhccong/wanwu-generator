package com.cong.maker.meta;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Cong
 */
@NoArgsConstructor
@Data
public class Meta {

    /**
     * 名字
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 基础包
     */
    private String basePackage;
    /**
     * 版本
     */
    private String version;
    /**
     * 作者
     */
    private String author;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 文件配置
     */
    private FileConfig fileConfig;
    /**
     * 模型配置
     */
    private ModelConfig modelConfig;

    /**
     * 文件配置
     *
     * @author 86188
     * @date 2023/12/09
     */
    @NoArgsConstructor
    @Data
    public static class FileConfig {
        /**
         * 输入根路径
         */
        private String inputRootPath;
        /**
         *
         */
        private String outputRootPath;
        /**
         * 源根路径
         */
        private String sourceRootPath;
        /**
         * 类型
         */
        private String type;
        /**
         * 文件
         */
        private List<FileInfo> files;

        /**
         * 文件信息
         *
         * @author 86188
         * @date 2023/12/09
         */
        @NoArgsConstructor
        @Data
        public static class FileInfo {
            /**
             * 输入路径
             */
            private String inputPath;
            /**
             * 输出路径
             */
            private String outputPath;

            /**
             * 类型
             */
            private String type;
            /**
             * 生成类型
             */
            private String generateType;
        }
    }

    /**
     * 模型配置
     *
     * @author 86188
     * @date 2023/12/09
     */
    @NoArgsConstructor
    @Data
    public static class ModelConfig {
        /**
         * 模型
         */
        private List<ModelInfo> models;

        /**
         * 型号信息
         *
         * @author 86188
         * @date 2023/12/09
         */
        @NoArgsConstructor
        @Data
        public static class ModelInfo {
            /**
             * 字段名称
             */
            private String fieldName;
            /**
             * 类型
             */
            private String type;
            /**
             * 描述
             */
            private String description;
            /**
             * 默认值
             */
            private Object defaultValue;
            /**
             * ABBR公司
             */
            private String abbr;
        }
    }
}
