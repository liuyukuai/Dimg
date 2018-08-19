package com.itxiaoer.dimg.constant;

/**
 * 文件单位计算类
 *
 * @author : liuyk
 */
public class FileUnitCalculation {

    /**
     * 将文件大小转换指定单位大小
     *
     * @param size 文件大小5M|5K
     * @return
     */
    public static long analyze(String size) {
        if (size == null) {
            throw new IllegalArgumentException("size arguments error");
        }
        FileUnit unit = unit(size);
        return Long.valueOf(size.replace(unit.getValue(), "")) * unit.getMultiple();
    }

    /**
     * 获取单位
     *
     * @param size
     * @return
     */
    public static FileUnit unit(String size) {
        FileUnit[] fileUnits = FileUnit.values();
        for (FileUnit fileUnit : fileUnits) {
            if (size.endsWith(fileUnit.getValue())) {
                return fileUnit;
            }
        }
        return FileUnit.B_UNIT;
    }
}
