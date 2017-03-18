
/**
 * @author rumi march 2017
 * This program illustrate the usage of GroovyShell invoked from java
 * in this example a java object is bound to a inner object in the script
 * 
 *
 */
package groovyscript;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

public class myJruleengine4 {

	public static void main(String[] args) throws IOException {

		long a = 8;
		long b = 9;
		long k = 0;
		int i;
				
		boolean displayon = false;
		Binding binding = new Binding();
		Trade T = new Trade(a, b);
		binding.setVariable("T", T);
		binding.setVariable("displayon", displayon);
		GroovyShell shell = new GroovyShell(binding);

		String myscript = new String(Files.readAllBytes(Paths.get("src/groovyscript/myrule3.groovy")));
		Script script1 = shell.parse(myscript);
		long timeStart = System.currentTimeMillis();

		for (i = 0; i < 100000; i++) {

			shell.setVariable("c", i);

			k += Integer.parseInt((script1.run()).toString());

		}

		long TimeDuration = System.currentTimeMillis() - timeStart;

		// Test

		k = Integer.parseInt((script1.run()).toString());

		System.out.printf("\nJava/Groovy Script Class wrapper Results Iteration # %d duration %d millis result %d\n", i,
				TimeDuration, k);
		
		
		// Deomonstrate the bindings being dynamic for the object but not for the variable.
		binding.setVariable("displayon", true); //allow to print from inside the script the value of the passing parameters
 
		T.amount1 = 100;
		T.amount2 = 200;
		i =  5 ; // show that Java script API Binding is not dynamic for simple variable while it is for object.
		k = Integer.parseInt((script1.run()).toString());
		System.out.println(k);

		i = 10;
		T.amount1 = 200;
		T.amount2 = 500;
		k = Integer.parseInt((script1.run()).toString());
		System.out.println(k);
		// illustrate how the script can return and change the state of the java object.
		System.out.println(T.ret);

	}
}
