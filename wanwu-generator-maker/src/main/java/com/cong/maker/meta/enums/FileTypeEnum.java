package com.cong.maker.meta.enums;

/**
 * 文件类型
 *
 * @author Cong
 * @date 2024/01/05
 */
public enum FileTypeEnum {
    /**
     * 文件类型
     */
    DIR("目录", "dir"),
    GROUP("文件组", "group"),
    FILE("文件", "file");
    private final String text;

    private final String value;

    FileTypeEnum(String text, String value) {
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
