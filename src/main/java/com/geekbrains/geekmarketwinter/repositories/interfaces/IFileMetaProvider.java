package com.geekbrains.geekmarketwinter.repositories.interfaces;

import com.geekbrains.geekmarketwinter.entites.FileMetaDTO;

import java.util.Collection;

public interface IFileMetaProvider {

    String checkFileExists(String fileHash);

    /**
     * Сохранить метаданные файла
     *
     */
    void saveFileMeta(String Hash, String fileName, int sybType);

    Collection<FileMetaDTO> getMetaFiles(int subtype);

    void deleteFile(String filename);

    String getHash(String filename);
}
