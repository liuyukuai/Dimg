package com.itxiaoer.dimg.service;

import com.itxiaoer.dimg.config.ImgConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author : liuyk
 */
@Component
public class FileStorageFinder {

    private ImgConfig imgConfig;


    public String dir() {
        return imgConfig.getPath();
    }


    public String find(String md5) {
        return this.dir() + File.separator + md5;
    }

    @Autowired
    public void setImgConfig(ImgConfig imgConfig) {
        this.imgConfig = imgConfig;
    }
}
