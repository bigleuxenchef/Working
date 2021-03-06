# Groovy Hello World

This section is dedicated at exploring various use cases around Groovy usage

## [Program JavaScriptAPIHelloWorld](https://github.com/bigleuxenchef/Working/tree/master/GroovyHelloWorld/src/JavaScriptAPIHelloWorld.java)

Demonstrate the use of java script API with groovy/python as engine

## [package javainterop](https://github.com/bigleuxenchef/Working/tree/master/GroovyHelloWorld/src/javainterop)
  
This package highlights the inter-operability between java and groovy


## [package groovyscript](https://github.com/bigleuxenchef/Working/tree/master/GroovyHelloWorld/src/groovyscript)

- Using groovy as scripting language by using the dynamic evaluation of script (either stored in file or string)
- Using groovy from java 
  * Case 1 : using groovy java api
  * Case 2 : using Java Script API (for both Python and Groovy)
  * Case 3 : [ULTIMATE BINDING] wrapping up groovy script in a java class to alleviate bindings limitations of single parameter binding.
  

##### Prerequisite 
In Eclipse Groovy plug-in comes with everything including GroovyConsole
Install Python plugin and [jython.jar](http://www.jython.org/downloads.html).

- - -
### Java Script API calling Groovy

```java	
		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("groovy");
		Integer sum = (Integer) engine.eval("(1..10).sum()");
		assertEquals(new Integer(55), sum);
		
		engine.put("first", "HELLO");
		engine.put("second", "world");
		String result = (String) engine.eval("first.toLowerCase() + ' ' + second.toUpperCase()");
```

- - -

#### Java Script API calling python

```java
// Java Script API with Python
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine pyEngine = mgr.getEngineByName("python");

		// eval a string representing python string
		pyEngine.put("varbound", 67);

		pyEngine.eval("print \"Python - Hello, world! {}\".format(varbound)");

		// eval a python script file with bindings features

		pyEngine.eval(new String(Files.readAllBytes(Paths.get("src/pyhelloworld.py"))));
```

## [Groovy Closure](https://github.com/bigleuxenchef/Working/tree/master/GroovyHelloWorld/src/Closure)

After the inter-operability between java and groovy (and to some extend python), we are ready now for exploring the power of closure, which allow returning function from groovy to java and injecting function into a groovy script. This is going to create a lot of good options for the one who wants to play with scripting at runtime.


****



