package com.geekbrains.app.services;

import com.geekbrains.app.entites.FileMetaDTO;
import com.geekbrains.app.repositories.interfaces.IFileMetaProvider;
import com.geekbrains.app.repositories.interfaces.IFileSystemProvider;
import com.geekbrains.app.services.interfaces.IFileStoreService;
import com.geekbrains.app.utils.HashHelper;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Component
public class FileStoreService implements IFileStoreService {

    @Autowired
    IFileSystemProvider systemProvider;

    @Autowired
    IFileMetaProvider fileMetaProvider;

    @Override
    public String storeFile(byte[] content, String fileName, int subFileType) throws IOException, NoSuchAlgorithmException {
        final UUID md5 = HashHelper.getMd5Hash(content);

        String filename = fileMetaProvider.checkFileExists(md5);
        if (filename == null) {
            fileMetaProvider.saveFileMeta(md5, fileName, subFileType);
            filename = systemProvider.storeFile(content, md5, fileName);
        }

        return filename;
    }

    @Override
    public byte[] getFile(UUID md5) throws IOException {
        String filename = fileMetaProvider.checkFileExists(md5);
        String ext = FilenameUtils.getExtension(filename);
        String fullFileName = md5.toString() + "." + ext;
        return systemProvider.getFile(fullFileName);
    }

    /**
     * Для получения файла по хэшу и имени файла
     */
    @Override
    public byte[] getFile(UUID md5, String filename) throws IOException {
        String name = fileMetaProvider.checkFileExists(md5, filename);
        String ext = FilenameUtils.getExtension(name);
        String fullFileName = md5.toString() + "." + ext;
        return systemProvider.getFile(fullFileName);
    }

    @Override
    public Collection<FileMetaDTO> getMetaFiles(int subtype) {
        return fileMetaProvider.getMetaFiles(subtype);
    }

    @Override
    public String deleteFile(UUID hash, String filename) throws IOException {
//        ArrayList<FileMetaDTO> arr = (ArrayList<FileMetaDTO>) fileMetaProvider.getMetaFilesByHash(hash);
        Collection<FileMetaDTO> list = fileMetaProvider.getMetaFilesByHash(hash);
        if (list.size() == 1) {
//            String name = arr.get(0).toString();
            String ext = FilenameUtils.getExtension(filename);
            String fullFileName = hash.toString() + "." + ext;
            systemProvider.deleteFile(fullFileName);
            fileMetaProvider.deleteFileMeta(hash, filename);
            return filename + "has been deleted from disk and DB!";
        } else if (list.size() > 1) {
            fileMetaProvider.deleteFileMeta(hash, filename);
            return filename + "has been deleted from DB!";
        } else return "No such file!";
    }


}
