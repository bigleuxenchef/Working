package javainterop2;

import groovy.lang.GroovyObject;
import groovy.lang.GroovyShell;

import java.io.File;
import java.io.IOException;

import org.codehaus.groovy.control.CompilationFailedException;

public class Interop {

    public static void main(String[] args) {

        try {
            File file = new File("src/javainterop2/test1.groovy");
            GroovyShell shell = new GroovyShell();
            Class<?> AnotherClass = (Class<?>) shell.parse(file).invokeMethod(
                    "getDynamicClass", GroovyShell.class.getClassLoader());
            System.out.println(AnotherClass);
            try {
                GroovyObject o = (GroovyObject) AnotherClass.newInstance();
                o.setProperty("field1", 1);
                o.setProperty("field2", 2);
                Object[] arguments = {};
                System.out.println(o.invokeMethod("sum", arguments));
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (CompilationFailedException | IOException e) {
            e.printStackTrace();
        }
    }
}