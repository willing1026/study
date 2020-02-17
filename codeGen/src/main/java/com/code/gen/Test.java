package com.code.gen;

import org.apache.commons.text.WordUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        String value = "order_item_code";
        value = WordUtils.uncapitalize(
                WordUtils.capitalize(value, '_')
                        .replaceAll("_", ""));
        System.out.println(value);

        Test test = new Test();
        String res = test.makeObjectString();

        test.createFile("d:/", "Sample", res);
    }

    //ObjectString 만들기
    //File로 쓰기

    public String makeObjectString() {
        String packageLoc = "com.code.gen";
        String accessModifier = "private";
        String instanceType = "String";

        StringBuilder sb = new StringBuilder();
        sb.append("package " + packageLoc + ";\n\n");
        sb.append("public class SampleDto {" + "\n");
        sb.append(accessModifier + " " + instanceType + " " + "orderItemCode" + ";\n");
        sb.append("}\n");

        return sb.toString();
    }

    public void createFile(String path, String name, String value) throws IOException {
        File file = new File(path + "/" + name + ".java");

        if (file.exists() && file.canWrite()) {
            throw new IOException("cannot write file");
        }
        try (FileOutputStream fos = new FileOutputStream(file);) {
            fos.write(value.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
