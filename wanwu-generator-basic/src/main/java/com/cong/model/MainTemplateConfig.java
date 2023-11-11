package com.cong.model;

import lombok.Data;

/**
 * 动态模版配置
 *
 * @author 86188
 * @date 2023/11/11
 */
@Data
public class MainTemplateConfig {

    /**
     * 是否生成循环
     */
    private boolean loop;

    /**
     * 作者注释
     */
    private String author = "cong";

    /**
     * 输出信息
     */
    private String outputText = "sum = ";
}