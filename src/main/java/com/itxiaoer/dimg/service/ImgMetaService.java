package com.itxiaoer.dimg.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itxiaoer.dimg.meta.ImgMeta;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author : liuyk
 */

@Slf4j
@Service
public class ImgMetaService {

    private FileStorageFinder fileStorageFinder;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void meta(String md5, ImgMeta meta) {
        try {
            Files.write(Paths.get(this.fileStorageFinder.dir() + File.separator + md5 + ".meta"), objectMapper.writeValueAsBytes(meta));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }


    public ImgMeta load(String md5) {
        try {
            List<String> strings = Files.readAllLines(Paths.get(this.fileStorageFinder.dir() + File.separator + md5 + ".meta"));
            return objectMapper.readValue(strings.get(0), ImgMeta.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ImgMeta();
        }
    }


    @Autowired
    public void setFileStorageFinder(FileStorageFinder fileStorageFinder) {
        this.fileStorageFinder = fileStorageFinder;
    }
}
