package rules;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.*;

public class myJruleengine2 {

	public static void main(String[] args) throws IOException, ScriptException {
		long a = 8;
		long b = 9;
		long i = 0;
		long k = 0;

		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("groovy");

		engine.put("a", a);
		engine.put("b", b);
		engine.put("c", i);

		long timeStart = System.currentTimeMillis();

		String myscript = new String(Files.readAllBytes(Paths.get("src/rules/myrule.txt")));
		
				
		for (i = 0; i < 100000; i++) {
			engine.put("c", i);
			k += Long.parseLong(engine.eval(myscript).toString());
			//System.out.printf("*a : %d b : %d c : %d\n",a,b,k);

		}

		long TimeDuration = System.currentTimeMillis() - timeStart;

		System.out.printf("\nEval script\n"
				+ "Java Scripting API/Groovy  Results Iteration # %d duration %d millis result %d\n",i, TimeDuration, k);

	// run with compiled script first to reduce overhead of parsing
		timeStart = System.currentTimeMillis();

		CompiledScript cs;
				
				cs = ((Compilable )engine).compile(myscript);
				k=0;
		
				for (i = 0; i < 100000; i++) {
					engine.put("c", i);
					k += Long.parseLong(cs.eval().toString());
					//System.out.printf("*a : %d b : %d c : %d\n",a,b,k);

				}

				TimeDuration = System.currentTimeMillis() - timeStart;

				System.out.printf("\nCompiled Script\n"
						+ "Java Scripting API/Groovy  Results Iteration # %d duration %d millis result %d\n",i, TimeDuration, k);

		
	}

}
