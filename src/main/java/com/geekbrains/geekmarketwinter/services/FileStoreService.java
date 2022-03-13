package com.geekbrains.geekmarketwinter.services;

import com.geekbrains.geekmarketwinter.entites.FileMetaDTO;
import com.geekbrains.geekmarketwinter.repositories.interfaces.IFileMetaProvider;
import com.geekbrains.geekmarketwinter.repositories.interfaces.IFileSystemProvider;
import com.geekbrains.geekmarketwinter.services.interfaces.IFileStoreService;
import com.geekbrains.geekmarketwinter.utils.HashHelper;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
        String hashMD5 = md5.toString();
        hashMD5.replaceAll("-", "");
        fileMetaProvider.saveFileMeta(hashMD5, fileName, subFileType);
        if (!systemProvider.isFileExist(md5, fileName)) {
            systemProvider.storeFile(content, md5, fileName);
        }
        return systemProvider.getFullFileName(md5, fileName);
    }

    @Override
    public byte[] getFile(String md5) throws IOException {
        String filename = fileMetaProvider.checkFileExists(md5);
        String ext = FilenameUtils.getExtension(filename);
        String fullFileName = md5 + "." + ext;
        return  systemProvider.getFile(fullFileName);
    }

    @Override
    public Collection<FileMetaDTO> getMetaFiles(int subtype) {
        return fileMetaProvider.getMetaFiles(subtype);
    }

    @Override
    public void deleteFile(String filename) throws IOException {
        String hash = fileMetaProvider.getHash(filename);
        fileMetaProvider.deleteFile(filename);
        String s = fileMetaProvider.checkFileExists(hash);
        System.out.println(s);
        if (s == null)
            systemProvider.deleteFile(hash, filename);
    }

}
