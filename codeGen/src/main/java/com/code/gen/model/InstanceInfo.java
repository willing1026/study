package com.code.gen.model;

import com.code.gen.util.TextUtils;

import java.util.Map;

public class InstanceInfo {
    private String name;
    private String type;
    private String accessModifier;

    //생성할때 정보 받으면, wordUtils 써서 반영.
    public InstanceInfo(String name, String type, String accessModifier) {
        this.name = TextUtils.toInstanceName(name);
        this.type = type;
        this.accessModifier = accessModifier == null ? "private" : accessModifier;
    }

    public InstanceInfo(Map<Integer, String> instance) {
        this.name = TextUtils.toInstanceName(instance.get(0));
        this.type = instance.get(1);
        this.accessModifier = instance.getOrDefault(2, "private");
    }

    @Override
    public String toString() {
        return accessModifier + " " + type + " " + name + ";" + "\n";
    }
}
