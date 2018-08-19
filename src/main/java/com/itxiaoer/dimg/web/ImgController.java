package com.itxiaoer.dimg.web;

import com.itxiaoer.dimg.plugin.Response;
import com.itxiaoer.dimg.service.FileStorageFinder;
import com.itxiaoer.dimg.service.ImgMetaService;
import com.itxiaoer.dimg.service.ImgStorageService;
import com.itxiaoer.dimg.validate.ImgValidate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author : liuyk
 */
@Slf4j
@Controller
public class ImgController {
    private ImgValidate imgValidate;

    private ImgStorageService imgStorageService;

    private ImgMetaService imgMetaService;

    @Resource
    private FileStorageFinder fileStorageFinder;

    @Resource
    private ResourceLoader resourceLoader;

    @ResponseBody
    @PostMapping("/upload")
    public Response<String> upload(@RequestParam(value = "file") MultipartFile file) {
        try {
            Response<Boolean> imgValidate = this.imgValidate.validate(file.getInputStream());
            if (imgValidate.isSuccess()) {
                return imgStorageService.storage(file);
            }
            return Response.error(imgValidate.getMessage());
        } catch (IOException e) {
            log.error(e.getLocalizedMessage(), e);
            return Response.error(e.getMessage());
        }
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<?> download(@PathVariable String id, HttpServletResponse response) {
        try {
            response.setContentType(this.imgMetaService.load(id).getType());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(this.imgMetaService.load(id).getType()));
            headers.setContentDispositionFormData("attachment", this.imgMetaService.load(id).getFileName());
            return new ResponseEntity<>(Files.readAllBytes(Paths.get(this.fileStorageFinder.find(id))), headers, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Autowired
    public void setImgValidate(ImgValidate imgValidate) {
        this.imgValidate = imgValidate;
    }

    @Autowired
    public void setImgStorageService(ImgStorageService imgStorageService) {
        this.imgStorageService = imgStorageService;
    }

    @Autowired
    public void setImgMetaService(ImgMetaService imgMetaService) {
        this.imgMetaService = imgMetaService;
    }
}
