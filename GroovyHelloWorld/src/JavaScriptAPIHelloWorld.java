
import static org.junit.Assert.assertEquals;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class JavaScriptAPIHelloWorld {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
	
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
