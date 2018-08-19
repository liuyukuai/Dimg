package com.itxiaoer.dimg.meta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : liuyk
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImgMeta {
    private String type;
    private long fileSize;
    private String fileName;

}
