package com.geekbrains.geekmarketwinter.services.interfaces;

import com.geekbrains.geekmarketwinter.entites.FileMetaDTO;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.UUID;

public interface IFileStoreService {

    /**
     * Сохранить файл
     *
     * @param content     массив байтов файла
     * @param fileName    имя файла
     * @param subFileType тип файла для библиотеки, которая обрабатывает этот файл
     * @return md5-hash сохраненного файла
     */
    String storeFile(byte[] content, String fileName, int subFileType) throws IOException, NoSuchAlgorithmException;


    byte[] getFile(UUID md5) throws IOException;


    Collection<FileMetaDTO> getMetaFiles(int subtype);

}
