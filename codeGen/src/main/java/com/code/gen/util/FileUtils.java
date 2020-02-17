package com.code.gen.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {
    public static void writeFile(String file, String value) throws IOException {
        File target = new File(file);
//        if (!target.exists()) {
//            target.mkdirs();
//        }

        try (FileOutputStream fos = new FileOutputStream(target)) {
            fos.write(value.getBytes());
        }
    }
}
