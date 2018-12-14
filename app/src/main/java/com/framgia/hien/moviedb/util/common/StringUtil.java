package com.framgia.hien.moviedb.util.common;

import com.framgia.hien.moviedb.util.Constants;

public class StringUtil {

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty() || str.trim().length() == 0;
    }

    public static String genUrlImage(String path) {
        return new StringBuilder()
                .append(Constants.END_POINT_IMAGE_URL)
                .append(path)
                .toString();
    }

    public static String genString(String... strings) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            builder.append(strings[i]);
        }
        return builder.toString();
    }
}
