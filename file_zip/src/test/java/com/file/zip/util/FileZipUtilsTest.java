package com.file.zip.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by jsh.
 * User: jaeseon
 * Date: 2019/12/14
 * Time: 10:59 AM
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FileZipUtilsTest {

    @Autowired
    FileZipUtils fileZipUtils;

    @Test
    void zipTest() throws IOException {
        //given
        String path = "../../dev/temp_file";
        String targetDir = "../../dev/result";
        String zipFileName = "test.zip";

        //when
        fileZipUtils.compress(path, targetDir, zipFileName);

        //then
        File result = new File(targetDir + File.separator + zipFileName);
        assertThat(result.canRead()).isTrue();
    }
}