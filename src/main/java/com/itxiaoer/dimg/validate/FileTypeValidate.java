package com.itxiaoer.dimg.validate;

import com.itxiaoer.dimg.plugin.Response;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * @author : liuyk
 */
@Component
public class FileTypeValidate implements Validate {


    @Override
    public Response<Boolean> validate(InputStream inputStream) {
        return Response.ok();
    }
}
