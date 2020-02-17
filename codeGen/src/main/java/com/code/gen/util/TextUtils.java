package com.code.gen.util;

import org.apache.commons.text.WordUtils;

public class TextUtils {
    private static final Character UNDERSCORE = '_';
    private static final String COLUMN_DELIMITER = "_";
    private static final String EMPTY = "";

    public static String toInstanceName(String column) {
        String instanceName = WordUtils.capitalize(column, UNDERSCORE)
                .replaceAll(COLUMN_DELIMITER, EMPTY);
        return WordUtils.uncapitalize(instanceName);
    }
}
