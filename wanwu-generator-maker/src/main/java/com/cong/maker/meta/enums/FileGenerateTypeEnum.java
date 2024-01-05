package com.cong.maker.meta.enums;

/**
 * 文件类型
 *
 * @author Cong
 * @date 2024/01/05
 */
public enum FileGenerateTypeEnum {
    /**
     * 文件生成类型
     */
    DYNAMIC("动态", "dynamic"),
    STATIC("静态", "static");
    private final String text;

    private final String value;

    FileGenerateTypeEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }
}
