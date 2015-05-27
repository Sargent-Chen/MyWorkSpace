package com.example.administrator.myminotes.tools;

/**
 * Created by Administrator on 2015/5/27.
 */
public class DataUtils {

    public static String getFormattedSnippet(String snippet) {
        if (snippet != null) {
            snippet = snippet.trim();
            int index = snippet.indexOf('\n');
            if (index != -1) {
                snippet = snippet.substring(0, index);
            }
        }
        return snippet;
    }
}
