package com.code.gen;

import com.code.gen.model.ObjectInfo;
import com.code.gen.util.ExcelReader;
import com.code.gen.util.FileUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class CodeGenService {
    public void generate() throws IOException {
        String excelFile = "d:/obj_info.xlsx";
        Map<Integer, Map> objInfoMap = ExcelReader.read(excelFile);
        ObjectInfo objectInfo = ObjFactory.build(objInfoMap);
        FileUtils.writeFile(objectInfo.resultFilePath(), objectInfo.toString());
    }
}
