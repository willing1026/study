package com.code.gen.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ObjectInfoTest {

    private Map<String, String> objInfos;
    private Map<Integer, Map> instances;

    @BeforeEach
    void setUp() {
        objInfos = new HashMap<>();
        objInfos.put("packageLoc", "com.code.gen");
        objInfos.put("className", "SampleDto");
        objInfos.put("localPath", "d:/");

        instances = new HashMap<>();
        Map<Integer, String> instance = new HashMap<>();
        //0 - name, 1 - type, 2 - accessModifier
        instance.put(0, "order_item_code");
        instance.put(1, "String");

        instances.put(0, instance);
    }

    @Test
    void createObjectInfo() {
        ObjectInfo objectInfo = new ObjectInfo(objInfos, instances);

        assertThat(objectInfo.getInstances().size()).isEqualTo(1);
    }
}