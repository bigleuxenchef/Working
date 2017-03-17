# Groovy Hello World

This section is dedicated at exploring various use cases around Groovy usage

#### package [groovyscript](https://github.com/bigleuxenchef/Working/tree/master/GroovyHelloWorld/src/groovyscript)

- Using groovy as scripting language by using the dynamic evaluation of script (either stored in file or string)
- Using groovy from java 
  * Case 1 : using groovy java api
  * Case 2 : using Java Script API
  
#### package [javainterop](https://github.com/bigleuxenchef/Working/tree/master/GroovyHelloWorld/src/javainterop)
  
This package highlights the interopeability between java and groovy

#### [JavaScriptAPIHelloWorld](https://github.com/bigleuxenchef/Working/tree/master/GroovyHelloWorld/src/JavaScriptAPIHelloWorld.java)
  

Demonstrate the use of java script API with groovy/python as engine

##### Prerequisite 
In Eclipse Groovy plug-in comes with everything including GroovyConsole
Install Python plugin and [jython.jar](http://www.jython.org/downloads.html).

```java
import static org.junit.Assert.assertEquals;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class JavaScriptAPIHelloWorld {

	public static void main(String[] args) throws Exception {	
		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("groovy");
		Integer sum = (Integer) engine.eval("(1..10).sum()");
		assertEquals(new Integer(55), sum);
		
		engine.put("first", "HELLO");
		engine.put("second", "world");
		String result = (String) engine.eval("first.toLowerCase() + ' ' + second.toUpperCase()");
		assertEquals("hello WORLD", result);
		System.out.println("done!");
	}

}
```
#### [Groovy Closure](https://github.com/bigleuxenchef/Working/tree/master/GroovyHelloWorld/src/Closure)

After the inter-operability between java and groovy, we are ready now for exploring the power of closure, which allow returning function from groovy to java and injecting function into a groovy script. This is going to create a lot of good options for the one who wants to play with scripting at runtime.



