package com.aws.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by jsh.
 * User: jaeseon
 * Date: 2019/12/15
 * Time: 12:28 AM
 */

@Slf4j
@RequiredArgsConstructor
@Component
public class S3ClientUtil {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public void download(String key, String tgtDir) {
        try {
            S3Object s3Object = amazonS3.getObject(bucket, key);
            S3ObjectInputStream s3ObjInputStream = s3Object.getObjectContent();

            File dir = new File(tgtDir);
            if (!dir.isDirectory()) {
                dir.mkdirs(); //해당 디렉토리 생성(부모디렉토리 포함)
            }

            File tgtFile = new File(tgtDir + "/" + key.substring(key.lastIndexOf("/") + 1));
            FileOutputStream fos = new FileOutputStream(tgtFile);

            byte[] bytes = IOUtils.toByteArray(s3ObjInputStream);
            fos.write(Objects.requireNonNull(bytes));
            fos.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
