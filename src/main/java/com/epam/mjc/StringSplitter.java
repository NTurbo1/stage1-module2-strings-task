package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> ds = new ArrayList<>();
        delimiters.forEach(d -> {
            ds.add(d + "|");
        });

        String x = ds.get(ds.size()-1);
        String l = x.substring(0, x.length()-1);

        ds.remove(ds.size()-1);
        ds.add(l);

        String regex = "";

        for (int i = 0; i < ds.size(); i++) {
            regex += ds.get(i);
        }

        List<String> result = List.of(source.split(regex));

        return result.stream().filter(s -> s.length() > 0).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> s = new StringSplitter().splitByDelimiters("qw3e1rt4yu2i3opa1sd1fg2hj4kl", List.of("1", "2", "3"));
        System.out.println(s);
    }
}
