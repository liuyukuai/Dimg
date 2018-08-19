package com.itxiaoer.dimg.listener;

import com.itxiaoer.dimg.config.ImgConfig;
import com.itxiaoer.dimg.constant.FileUnitCalculation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 启动参数校验类
 *
 * @author : liuyk
 */
@Slf4j
@Component
public class ArgsValidateCommandRunner implements CommandLineRunner {


    private ImgConfig imgConfig;

    @Override
    public void run(String... args) throws Exception {
        if (imgConfig.getPath() == null || imgConfig.getPath().isEmpty()) {
            log.error("img.path argument error");
            System.exit(0);
        }

        if (FileUnitCalculation.unit(imgConfig.getMaxSize()) == null) {
            log.error("img.maxSize argument unit error,it must be B|K|M|G|T");
            System.exit(0);
        }
        // 参数范围验证

    }

    @Autowired
    public void setImgConfig(ImgConfig imgConfig) {
        this.imgConfig = imgConfig;
    }
}
