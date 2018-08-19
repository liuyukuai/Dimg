package com.itxiaoer.dimg.service;

import com.itxiaoer.dimg.meta.ImgMeta;
import com.itxiaoer.dimg.plugin.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author : liuyk
 */

@Slf4j
@Service
public class ImgStorageService {
    private FileStorageFinder fileStorageFinder;
    private ImgMetaService imgMetaService;

    public Response<String> storage(MultipartFile file) {


        try {
            String md5 = DigestUtils.md5DigestAsHex(file.getInputStream());
            String storageFile = this.fileStorageFinder.find(md5);
            if (new File(storageFile).exists()) {
                return Response.ok(md5);
            }
            File destDir = new File(fileStorageFinder.dir());
            if (!destDir.exists()) {
                destDir.mkdirs();
            }
            file.transferTo(new File(destDir, md5));
            this.imgMetaService.meta(md5, new ImgMeta(file.getContentType(), file.getSize(), file.getOriginalFilename()));
            return Response.ok(md5);
        } catch (IOException e) {
            return Response.error(e.getMessage());
        }
    }


    @Autowired
    public void setFileStorageFinder(FileStorageFinder fileStorageFinder) {
        this.fileStorageFinder = fileStorageFinder;
    }

    @Autowired
    public void setImgMetaService(ImgMetaService imgMetaService) {
        this.imgMetaService = imgMetaService;
    }
}
