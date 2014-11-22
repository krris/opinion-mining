package io.github.krris.opinionminer.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by krris on 06.11.14.
 * Copyright (c) 2014 krris. All rights reserved.
 */
public class Utils {

    public static void saveToFile(String content, String directoryPath, String filename) {
        createDirIfDoesNotExist(directoryPath);
        try {
            Files.write(Paths.get(directoryPath + filename), content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createDirIfDoesNotExist(String websitesDirPath) {
        Path directoryPath = Paths.get(websitesDirPath);

        if (!Files.exists(directoryPath)) {
            try {
                Files.createDirectory(directoryPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
