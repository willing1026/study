package com.code.gen;

import com.code.gen.model.ObjectInfo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ObjFactory {
    public static ObjectInfo build(Map<Integer, Map> rowMap) {
        Map<String, String> objInfos = extractObjInfos(rowMap);

        return new ObjectInfo(objInfos, rowMap);
    }

    private static Map<String, String> extractObjInfos(Map<Integer, Map> rowMap) {
        Map<String, String> objInfos = new HashMap<>();

        for (int i = 0; i < 3; i++) {
            Map<Integer, String> row = rowMap.remove(i);
            objInfos.put(row.get(0), row.get(1));
        }

        return objInfos;
    }
}
