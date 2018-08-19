package com.itxiaoer.dimg.validate;

import com.itxiaoer.dimg.plugin.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * @author : liuyk
 */
@Component
public class ImgValidate {

    private FileSizeValidate fileSizeValidate;

    private FileTypeValidate fileTypeValidate;


    public Response<Boolean> validate(InputStream inputStream) {
        Response<Boolean> sizeValidate = fileSizeValidate.validate(inputStream);
        Response<Boolean> typeValidate = fileTypeValidate.validate(inputStream);
        if (!sizeValidate.isSuccess()) {
            return sizeValidate;
        }
        if (!typeValidate.isSuccess()) {
            return typeValidate;
        }
        return Response.ok(sizeValidate.isSuccess() && typeValidate.isSuccess());
    }


    @Autowired
    public void setFileSizeValidate(FileSizeValidate fileSizeValidate) {
        this.fileSizeValidate = fileSizeValidate;
    }

    @Autowired
    public void setFileTypeValidate(FileTypeValidate fileTypeValidate) {
        this.fileTypeValidate = fileTypeValidate;
    }
}
