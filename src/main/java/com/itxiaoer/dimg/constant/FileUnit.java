package com.itxiaoer.dimg.constant;

/**
 * @author : liuyk
 */
public enum FileUnit {

    /**
     * B
     */
    B_UNIT("B", 1),

    /**
     * KB
     */
    KB_UNIT("K", 1024),

    /**
     * MB
     */
    MB_UNIT("M", 1024 * 1024),

    /**
     * GB
     */
    GB_UNIT("G", 1024 * 1024 * 1024);


    private String value;

    private long multiple;

    FileUnit(String value, int multiple) {
        this.value = value;
        this.multiple = multiple;
    }

    public String getValue() {
        return value;
    }

    public long getMultiple() {
        return multiple;
    }
}
