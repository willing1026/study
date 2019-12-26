package com.file.ftp;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.springframework.stereotype.Service;

import java.io.*;

@Slf4j
@Service
public class FtpService {
    private FTPClient ftpClient;

    public FtpService() {
        this.ftpClient = new FTPClient();
    }

    //접속여부 확인
    public boolean connect(String hostName) {
        try {
            ftpClient.connect(hostName);
        } catch (IOException e) {
            log.error(e.getMessage());
            return false;
        }

        return true;
    }

    //로그인
    public boolean login(String userName, String password) {
        try {
            ftpClient.login(userName, password);
        } catch (IOException e) {
            log.error("login fail : {}", e.getMessage());
            return false;
        }

        return true;
    }

    //파일 가져오기
    public boolean download(String sourcePath, String targetFile, String tempDir) throws IOException {
        InputStream inputStream = null;
        FileOutputStream fos = null;

        try {
            FTPFile[] ftpFiles = ftpClient.listFiles(sourcePath + "/" + targetFile);

            if (ftpFiles == null || ftpFiles.length == 0) {
                throw new NoSuchFieldException("해당 파일이 존재하지 않습니다.");
            }

            inputStream = ftpClient.retrieveFileStream(ftpFiles[0].getName());

            createTempDir(tempDir);
            fos = createTempFile(tempDir + "/" + targetFile);

            byte[] byteArray = new byte[4096];
            int bytesRead = -1;
            while((bytesRead = inputStream.read(byteArray)) != -1) {
                fos.write(byteArray, 0, bytesRead);
            }
        } catch (IOException | NoSuchFieldException e) {
            log.error(e.getMessage());
            return false;
        } finally {
            if (fos != null) {
                fos.close();
            }

            if (inputStream != null) {
                inputStream.close();
            }
        }

        return true;
    }

    private void createTempDir(String path) {
        File tempDir = new File(path);
        if (!tempDir.isDirectory()) {
            tempDir.mkdirs();
        }
    }

    private FileOutputStream createTempFile(String filePath) throws FileNotFoundException {
        File tempFile = new File(filePath);
        return new FileOutputStream(tempFile);
    }

    //연결끊기
    public boolean disConnect() throws IOException {
        try {
            ftpClient.disconnect();
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            if (ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
        }

        return true;
    }
}
