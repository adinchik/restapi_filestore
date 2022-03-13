package com.geekbrains.geekmarketwinter.repositories.interfaces;


import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

public interface IFileSystemProvider {

    byte[] getFile(String fileHash) throws IOException;

    void storeFile(byte[] content, UUID md5, String fileName) throws IOException;

    void deleteFile(String fileHash, String filename)throws IOException;

    Path getPath(UUID md5, String fileName);

    String getFullFileName(UUID md5, String fileName);

    boolean isFileExist(UUID md5, String fileName);

}
