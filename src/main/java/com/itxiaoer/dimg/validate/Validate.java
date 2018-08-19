package com.itxiaoer.dimg.validate;

import com.itxiaoer.dimg.plugin.Response;

import java.io.InputStream;

/**
 * @author : liuyk
 */
public interface Validate {

    Response<Boolean> validate(InputStream inputStream);
}
