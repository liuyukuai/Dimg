package com.itxiaoer.dimg.validate;

import com.itxiaoer.dimg.config.ImgConfig;
import com.itxiaoer.dimg.constant.FileUnitCalculation;
import com.itxiaoer.dimg.plugin.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author : liuyk
 */
@Component
public class FileSizeValidate implements Validate {

    private ImgConfig imgConfig;

    @Override
    public Response<Boolean> validate(InputStream inputStream) {

        try {
            int available = inputStream.available();
            long analyze = FileUnitCalculation.analyze(imgConfig.getMaxSize());
            if (available > analyze) {
                return Response.error("the file too big!");
            }
            return Response.ok();
        } catch (IOException e) {
            return Response.error(e.getMessage());
        }
    }

    @Autowired
    public void setImgConfig(ImgConfig imgConfig) {
        this.imgConfig = imgConfig;
    }
}
