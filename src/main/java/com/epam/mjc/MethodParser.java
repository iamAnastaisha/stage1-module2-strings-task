package com.epam.mjc;

import com.sun.jdi.connect.Connector;

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
        StringTokenizer stringTokenizer =
                new StringTokenizer(signatureString.substring(0, signatureString.indexOf("(")), " ");
        List<String> arm = new ArrayList<>();
        while (stringTokenizer.hasMoreTokens()) {
            arm.add(stringTokenizer.nextToken());
        }
        stringTokenizer =
                new StringTokenizer(signatureString.substring(signatureString.indexOf("(") + 1,
                        signatureString.indexOf(")")), " ,");
        List<MethodSignature.Argument> args = new ArrayList<>();
        while (stringTokenizer.hasMoreTokens()) {
            String type = stringTokenizer.nextToken();
            String name = stringTokenizer.nextToken();
            args.add(new MethodSignature.Argument(type, name));
        }
        MethodSignature methodSignature;
        if (args.size() == 0 && arm.size() == 2) {
            methodSignature = new MethodSignature(arm.get(1));
            methodSignature.setReturnType(arm.get(0));
        } else if (args.size() == 0 && arm.size() == 3) {
            methodSignature = new MethodSignature(arm.get(2));
            methodSignature.setAccessModifier(arm.get(0));
            methodSignature.setReturnType(arm.get(1));
        } else if (args.size() > 0 && arm.size() == 2){
            methodSignature = new MethodSignature(arm.get(1), args);
            methodSignature.setReturnType(arm.get(0));
        } else {
            methodSignature = new MethodSignature(arm.get(2), args);
            methodSignature.setAccessModifier(arm.get(0));
            methodSignature.setReturnType(arm.get(1));
        }
        return methodSignature;
    }
}
