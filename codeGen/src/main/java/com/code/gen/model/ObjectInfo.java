package com.code.gen.model;

import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class ObjectInfo {
    private String packageLoc;
    private String className;
    private String localPath;
    private List<InstanceInfo> instances;

    /**
     * Excel 데이터를 받을때 사용
     * Map<Integer, Map> fields => rows 정보를 담고 있고, value인 Map은 cell들의 정보를 담고 있음
     */
    public ObjectInfo(Map<String, String> objInfos, Map<Integer, Map> fields) {
        this.packageLoc = objInfos.get("packageLoc");
        this.className = objInfos.get("className");
        this.localPath = objInfos.get("localPath");
        this.instances = extractInstances(fields);
    }

    public String resultFilePath() {
        return localPath + className + ".java";
    }

    private List<InstanceInfo> extractInstances(Map<Integer, Map> fields) {
        return fields.keySet().stream()
                .map(key -> {
                    Map<Integer, String> field = fields.get(key);
                    return new InstanceInfo(field);
                })
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("package " + packageLoc + "\n\n");
        sb.append("public class " + className + " {" + "\n");
        for (InstanceInfo instanceInfo : instances) {
            sb.append(instanceInfo.toString());
        }
        sb.append("}\n");

        return sb.toString();
    }
}
