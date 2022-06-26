package com.geekbrains.app.repositories;

import com.geekbrains.app.repositories.interfaces.IFileMetaProvider;
import com.geekbrains.app.entites.FileMetaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Collection;
import java.util.UUID;


@Component
public class FileMetaProvider implements IFileMetaProvider {

    private static final String GET_FILES_META = "select hash, filename from winter_market_1.file_info_metadata where sub_type = :subtype";

    /**
     * Запрос на получение всех имен файлов с одинаковым хэшэм
     **/
    private static final String GET_FILES_META_BY_HASH = "select filename from winter_market_1.file_info_metadata where hash = :hash";

    private static final String GET_FILE_PATH_BY_HASH = "select filename from winter_market_1.file_info_metadata where hash = :hash";

    private static final String GET_FILE_PATH_BY_HASH_AND_FILENAME = "select filename from winter_market_1.file_info_metadata where hash = :hash  AND filename = :filename";


    private static final String SAVE_FILE_META_DATA = "insert into winter_market_1.file_info_metadata (hash, filename, sub_type)\n" +
            "values (:hash, :filename, :subtype)";

    /**Удаление строки из БД**/
    private static final String DELETE_FILE_META_DATA = "delete from winter_market_1.file_info_metadata where hash = :hash  AND filename = :filename";

    private final Sql2o sql2o;

    public FileMetaProvider(@Autowired Sql2o sql2o) {
        this.sql2o = sql2o;
    }


    @Override
    public String checkFileExists(UUID fileHash) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(GET_FILE_PATH_BY_HASH, false)
//                    !!!!!!!!!!!!!!!!!!!!!!!!!!!!! toString()
                    .addParameter("hash", fileHash.toString())
                    .executeScalar(String.class);
        }
    }


    /**
     * Проверка по хэшу и имени файла
     **/
    @Override
    public String checkFileExists(UUID fileHash, String filename) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(GET_FILE_PATH_BY_HASH_AND_FILENAME, false)
                    .addParameter("hash", fileHash.toString())
                    .addParameter("filename", filename)
                    .executeScalar(String.class);
        }
    }

    @Override
    public void saveFileMeta(UUID fileHash, String fileName, int sybType) {
        try (Connection connection = sql2o.open()) {
            connection.createQuery(SAVE_FILE_META_DATA)
                    //                    !!!!!!!!!!!!!!!!!!!!!!!!!!!!! toString()

                    .addParameter("hash", fileHash.toString())
                    .addParameter("filename", fileName)
                    .addParameter("subtype", sybType)
                    .executeUpdate();
        }
    }

    /**
     * Удаления файла из БД
     **/
    @Override
    public void deleteFileMeta(UUID hash, String filename) {
        try (Connection connection = sql2o.open()) {
            connection.createQuery(DELETE_FILE_META_DATA)
                    .addParameter("hash", hash.toString())
                    .addParameter("filename", filename)
                    .executeUpdate();

        }
    }


    @Override
    public Collection<FileMetaDTO> getMetaFiles(int subtype) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(GET_FILES_META, false)
                    .addParameter("subtype", subtype)
                    .executeAndFetch(FileMetaDTO.class);
        }
    }

    /**
     * Получаем коллекцию всех файлов с одним хэшем
     **/
    @Override
    public Collection<FileMetaDTO> getMetaFilesByHash(UUID fileHash) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(GET_FILES_META_BY_HASH, false)
                    .addParameter("hash", fileHash.toString())
                    .executeAndFetch(FileMetaDTO.class);
        }
    }
}