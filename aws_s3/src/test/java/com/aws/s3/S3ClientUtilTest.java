package com.aws.s3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by jsh.
 * User: jaeseon
 * Date: 2019/12/15
 * Time: 10:06 AM
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class S3ClientUtilTest {

    @Autowired
    private S3ClientUtil s3ClientUtil;

    @Test
    void test() {
        String srcFullPath = "test/KakaoTalk_Photo_2019-12-15-10-31-53.jpeg";
        String tgtDir = "temp_file/s3_download";

        s3ClientUtil.download(srcFullPath, tgtDir);

        //then
        File actual = new File(tgtDir + "/" + "KakaoTalk_Photo_2019-12-15-10-31-53.jpeg");
        assertThat(actual.canRead()).isTrue();
    }
}