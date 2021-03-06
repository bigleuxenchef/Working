
/**
 * @author rumi march 2017
 * this program illustrate the usage of Java Script API with Groovy, trying to compare the equivalent with Groovy Shell
 */

package groovyscript;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.script.*;

public class myJruleengine3 {

	public static void main(String[] args) throws IOException, ScriptException {
		long a = 8;
		long b = 9;
		long i = 0;
		long k = 0;

		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("python");

		engine.put("a", a);
		engine.put("b", b);
		engine.put("c", i);

		long timeStart = System.currentTimeMillis();

		String myscript = new String(Files.readAllBytes(Paths.get("src/groovyscript/myrule.py")));
		
		for (i = 0; i < 100000; i++) {
			engine.put("c", i);
			//k += Long.parseLong(engine.eval(myscript).toString());
			engine.eval(myscript);
			k+=Long.parseLong(engine.get("z").toString());
			// System.out.printf("*a : %d b : %d c : %d\n",a,b,k);

		}

		long TimeDuration = System.currentTimeMillis() - timeStart;

		System.out.printf(
				"\nEval script\n" + "Java Scripting API/python  Results Iteration # %d duration %d millis result %d\n",
				i, TimeDuration, k);

		// run with compiled script first to reduce overhead of parsing
		timeStart = System.currentTimeMillis();

		CompiledScript cs;

		cs = ((Compilable) engine).compile(myscript);
		
		k = 0;

		for (i = 0; i < 100000; i++) {
			engine.put("c", i);
			cs.eval();
			k+=Long.parseLong(engine.get("z").toString());
			// System.out.printf("*a : %d b : %d c : %d\n",a,b,k);

		}

		TimeDuration = System.currentTimeMillis() - timeStart;

		System.out.printf(
				"\nCompiled Script\n"
						+ "Java Scripting API/Python  Results Iteration # %d duration %d millis result %d\n",
				i, TimeDuration, k);

	}

}
