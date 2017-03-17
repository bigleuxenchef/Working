
import static org.junit.Assert.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class JavaScriptAPIHelloWorld {

	public static void main(String[] args) throws Exception {

		// Java Script API with Groovy
		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("groovy");
		Integer sum = (Integer) engine.eval("(1..10).sum()");
		assertEquals(new Integer(55), sum);
		// Binding example

		engine.put("first", "HELLO");
		engine.put("second", "world");

		String result = (String) engine.eval("first.toLowerCase() + ' ' + second.toUpperCase()");
		assertEquals("hello WORLD", result);

		try {
			engine.eval(new String(Files.readAllBytes(Paths.get("src/myscript.txt"))));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// Java Script API with Python
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine pyEngine = mgr.getEngineByName("python");

		// eval a string representing python string
		pyEngine.put("varbound", 67);

		pyEngine.eval("print \"Python - Hello, world! {}\".format(varbound)");

		// eval a python script file with bindings features

		pyEngine.eval(new String(Files.readAllBytes(Paths.get("src/pyhelloworld.py"))));

		System.out.println("done!");
	}

}
