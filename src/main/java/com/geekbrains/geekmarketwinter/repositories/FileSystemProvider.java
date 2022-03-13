package com.geekbrains.geekmarketwinter.repositories;


import com.geekbrains.geekmarketwinter.repositories.interfaces.IFileSystemProvider;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.stream.Stream;

@Component
public class FileSystemProvider implements IFileSystemProvider {
    @Value("${store.folder}")
    private String storeFolder;

    private Path storePath;

    @PostConstruct
    public void init() {
        String currentPath = Paths.get("").toAbsolutePath().toString();
        storePath = Paths.get(currentPath, storeFolder);
    }

    @Override
    public byte[] getFile(String fileHash) throws IOException {

        try (Stream<Path> walk = Files.walk(storePath)) {
            String fileName = walk.map(Path::toString)
                    .filter(f -> f.contains(fileHash))
                    .findFirst()
                    .orElseThrow(() -> new IOException("file not found"));

            File file = new File(fileName);
            return Files.readAllBytes(file.toPath());
        }
    }

    @Override
    public void storeFile(byte[] content, UUID md5, String fileName) throws IOException {
        FileUtils.writeByteArrayToFile(new File(getFullFileName(md5, fileName)), content);
    }


    @Override
    public void deleteFile(String fileHash, String filename) throws IOException {
        Path path = getPath(UUID.fromString(fileHash), filename);
        System.out.println(path.toString());
        if (Files.exists(path)) {
            Files.delete(path);
        }
    }

    @Override
    public Path getPath(UUID md5, String fileName) {
        String fileNameExtension = FilenameUtils.getExtension(fileName);
        fileName = String.format("%s.%s", md5, fileNameExtension);

        Path fullFileNamePath = Paths.get(storePath.toString(), fileName);
        return fullFileNamePath;
    }

    @Override
    public String getFullFileName(UUID md5, String fileName) {
        Path fullFileNamePath = getPath(md5, fileName);
        return fullFileNamePath.toString();
    }

    @Override
    public boolean isFileExist(UUID md5, String fileName){
        Path fullFileNamePath = getPath(md5, fileName);
        return Files.exists(fullFileNamePath);
    }
}