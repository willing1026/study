package com.file.ftp;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FtpServiceTest {

    @Value("${ftp.test.hostname}")
    private String hostName;

    @Value("${ftp.test.username}")
    private String userName;

    @Value("${ftp.test.password}")
    private String password;

    @Value("${ftp.test.path}")
    private String path;

    @Value("${ftp.test.temp_dir}")
    private String tempDir;

    @Value("${ftp.test.target_file}")
    private String targetFile;

    @Autowired
    private FtpService ftpService;

    @Test
    @Order(1)
    void connect() {
        assertThat(ftpService.connect(hostName)).isTrue();
    }

    @Test
    @Order(2)
    void login() {
        ftpService.login(userName, password);
    }

    @Test
    @Order(3)
    void getFileList() throws Exception {
        ftpService.download(path, targetFile, tempDir);
    }

    @Test
    @Order(4)
    void disConnect() throws Exception {
        ftpService.disConnect();
    }
}