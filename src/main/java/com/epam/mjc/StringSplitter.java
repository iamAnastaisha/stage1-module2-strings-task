package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        String delim = "";
        for (String delimiter : delimiters) {
            delim = delim.concat(delimiter);
        }
        StringTokenizer stringTokenizer = new StringTokenizer(source, delim);
        List<String> resultList = new ArrayList<>();
        while (stringTokenizer.hasMoreTokens()) {
            resultList.add(stringTokenizer.nextToken());
        }
        return resultList;
    }
}
