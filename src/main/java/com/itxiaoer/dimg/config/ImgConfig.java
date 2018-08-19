package com.itxiaoer.dimg.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author : liuyk
 */
@Data
@Component
public class ImgConfig {

    /**
     * 默认图片格式
     */

    private static final String DEFAULT_SUFFIX = "PNG,JPEG,JPG,BMP";

    /**
     * 默认图片格式
     */

    private static final String DEFAULT_MAX_SIZE = "5M";

    @Value("${img.path}")
    private String path;

    @Value("${img.suffix:" + DEFAULT_SUFFIX + "}")
    private String suffix;

    @Value("${img.maxSize:" + DEFAULT_MAX_SIZE + "}")
    private String maxSize;

    /**
     * 节点数量
     */
    private int node = 1;
}
