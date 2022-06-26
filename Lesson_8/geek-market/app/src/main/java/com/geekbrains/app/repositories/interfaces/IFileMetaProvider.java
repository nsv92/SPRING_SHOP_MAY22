package com.geekbrains.app.repositories.interfaces;

import com.geekbrains.app.entites.FileMetaDTO;

import java.util.Collection;
import java.util.UUID;

public interface IFileMetaProvider {

    String checkFileExists(UUID fileHash);

    String checkFileExists(UUID fileHash, String filename);

    /**
     * Сохранить метаданные файла
     *
     */
    void saveFileMeta(UUID Hash, String fileName, int sybType);

    Collection<FileMetaDTO> getMetaFiles(int subtype);

    Collection<FileMetaDTO> getMetaFilesByHash(UUID fileHash);

    void deleteFileMeta(UUID hash, String filename);
}
