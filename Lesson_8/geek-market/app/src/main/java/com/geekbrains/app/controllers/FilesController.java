package com.geekbrains.app.controllers;

import com.geekbrains.app.services.interfaces.IFileStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@RestController
@CrossOrigin
public class FilesController {

    @Autowired
    IFileStoreService fileStoreService;

    @PostMapping("/storefile")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("subtype") int subType
    ) throws IOException, NoSuchAlgorithmException, InterruptedException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }
        String fileHash = fileStoreService.storeFile(file.getBytes(), file.getOriginalFilename(), subType);
        return ResponseEntity.ok(fileHash);
    }

    @GetMapping("/getfile")
    public ResponseEntity<Resource> downloadFile(@RequestParam("hash") UUID hash,
                                                 @RequestParam("filename") String filename) throws IOException {
        byte[] array = fileStoreService.getFile(hash, filename);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                Передаем имя файла в кастом хедере
                .header("filename", filename)
                .body(new ByteArrayResource(array));
    }

    @GetMapping("/getfiles")
    public ResponseEntity<?> downloadFile(@RequestParam("subtype") int subtype) throws IOException {
        return ResponseEntity.ok(fileStoreService.getMetaFiles(subtype));
    }

    /**Удаление файла по хэшу и имени**/
    @PostMapping("/deletefile")
    public ResponseEntity<String> deleteFile(@RequestParam("hash") UUID hash,
                                             @RequestParam("filename") String filename) throws Exception {
        String result = fileStoreService.deleteFile(hash, filename);
        return ResponseEntity.ok()
                .body(result);
    }

}
