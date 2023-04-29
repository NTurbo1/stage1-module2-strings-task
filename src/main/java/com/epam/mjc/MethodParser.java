package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        StringTokenizer s1 = new StringTokenizer(signatureString, "(");
        String[] s2 = s1.nextToken().split(" ");
        String s = s1.nextToken();
        String[] s3 = s.substring(0, s.length()-1).split(", ");

        List<MethodSignature.Argument> arguments = new ArrayList<>();

        for (String s4 : s3) {
            String[] s5 = s4.split(" ");
            if (s5.length == 2) {
                arguments.add(new MethodSignature.Argument(s5[0], s5[1]));
            }
        }

        MethodSignature m = new MethodSignature(s2[s2.length-1], arguments);

        if (s2.length < 3) {
            m.setAccessModifier(null);
            m.setReturnType(s2[0]);
        } else {
            m.setAccessModifier(s2[0]);
            m.setReturnType(s2[1]);
        }

        return m;

    }
}
