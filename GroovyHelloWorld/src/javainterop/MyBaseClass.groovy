package javainterop

abstract class MyBaseClass extends Script {
    String name
    public void greet() { println "Hello, $name!" }
}
