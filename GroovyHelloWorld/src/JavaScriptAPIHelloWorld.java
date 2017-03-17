
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
		try{Path p;
		p = Paths.get("myscript.txt");
		String myscript = new String(Files.readAllBytes(p));
		engine.eval(myscript);
		} catch (Exception ex) {
            ex.printStackTrace();
        }   
		// Java Script API with Python
		 ScriptEngineManager mgr = new ScriptEngineManager();
	        ScriptEngine pyEngine = mgr.getEngineByName("python");
	        try {
	            pyEngine.eval("print \"Python - Hello, world!\"");
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }   
		
		System.out.println("done!");
	}

}
