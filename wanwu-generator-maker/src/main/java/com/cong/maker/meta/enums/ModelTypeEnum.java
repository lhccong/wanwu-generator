package com.cong.maker.meta.enums;

/**
 * 模型类型
 *
 * @author Cong
 * @date 2024/01/05
 */
public enum ModelTypeEnum {
    /**
     * 模型类型
     */
    STRING("字符串", "String"),
    BOOLEAN("布尔", "boolean");
    private final String text;

    private final String value;

    ModelTypeEnum(String text, String value) {
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
