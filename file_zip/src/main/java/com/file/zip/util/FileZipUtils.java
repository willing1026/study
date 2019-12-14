package com.file.zip.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * Created by jsh.
 * User: jaeseon
 * Date: 2019/12/14
 * Time: 10:38 AM
 */

@Slf4j
@Service
public class FileZipUtils {
    private static final String DIR_SEPARATOR = "/";

    public void compress(String srcDir, String targetDir, String outZipNm) throws IOException {
        checkTargetDir(targetDir);

        ZipArchiveOutputStream zos = new ZipArchiveOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(targetDir + DIR_SEPARATOR + outZipNm)
                )
        );
        zos.setEncoding("UTF-8");

        addFolder(srcDir, "", srcDir, zos);

        zos.close();
    }

    private void checkTargetDir(String targetDir) {
        File dir = new File(targetDir);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    private void addFolder(String srcRootDir, String path, String srcDir, ZipArchiveOutputStream zos) throws IOException {
        File folder = new File(srcDir);

        for (String fileName : folder.list()) {
            String nextPath = "".equals(path) ? folder.getName() : path + DIR_SEPARATOR + folder.getName();
            String entity = srcDir + DIR_SEPARATOR + fileName;
            addFile(srcRootDir, nextPath, entity, zos);
        }
    }

    private void addFile(String srcRootDir, String path, String srcFile, ZipArchiveOutputStream zos) throws IOException {
        File entity = new File(srcFile);

        if (entity.isDirectory()) {
            addFolder(srcRootDir, path, srcFile, zos);
            return;
        }

        //상위 dir 제외
        srcFile = srcFile.replace(srcRootDir + DIR_SEPARATOR, "");

        putArchiveEntity(entity, srcFile, zos);
    }

    private void putArchiveEntity(File entity, String srcFile, ZipArchiveOutputStream zos) throws IOException {
        FileInputStream fis = null;
        BufferedInputStream bis = null;

        try {
            int size = 1024;
            byte[] buf = new byte[size];

            fis = new FileInputStream(entity);
            bis = new BufferedInputStream(fis, size);

            zos.putArchiveEntry(new ZipArchiveEntry(srcFile));

            int len;
            while ((len = bis.read(buf, 0, size)) != -1) {
                zos.write(buf, 0, len);
            }

            bis.close();
            fis.close();
            zos.closeArchiveEntry();

        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (bis != null) {
                bis.close();
            }
        }
    }
}
