
/**
 * @author rumi march 2017
 * This program illustrate the usage of GroovyShell invoked from java
 * This way we can see how to make dynamic code/rule being known cmpile and run at run time
 * concept explored
 * external groovy script stored in a file and loaded
 * while script loaded, it is compiled only once
 * then a series of execution will be carried out leveraging the one time compilation of the script
 * Parameters exchange between java and groovy script using bindings
 * 
 * static void myinlinescript will served to compare execution time of inline java code and groovy script code
 * 
 */
package rules;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

public class myJruleengine1 {
	static void myinlinescript() {
		long a = 8;
		long b = 9;
		long i = 0;
		long k = 0;
		long timeStart = System.currentTimeMillis();

		for (i = 0; i < 100000; i++) {
			

			k += (a > b) ? a + i : b + i;
			//System.out.printf("*a : %d b : %d c : %d\n",a,b,k);
		}

		long TimeDuration = System.currentTimeMillis() - timeStart;

		System.out.printf("\nJava/Inline        Results Iteration # %d duration %d millis result %d\n", i, TimeDuration, k);

	}

	public static void main(String[] args) throws IOException {

		long a = 8;
		long b = 9;
		long i = 0;

		Binding binding = new Binding();
		binding.setVariable("a", a);
		binding.setVariable("b", b);

		GroovyShell shell = new GroovyShell(binding);

		String myscript = new String(Files.readAllBytes(Paths.get("src/rules/myrule.txt")));

		Script script1 = shell.parse(myscript);
		long k = 0;
		long timeStart = System.currentTimeMillis();
		// shell.setVariable("c",i);

		for (i = 0; i < 100000; i++) {
			shell.setVariable("c", i);

			k += Integer.parseInt((script1.run()).toString());
			//System.out.printf("*a : %d b : %d c : %d\n",a,b,k);
			// script1.run();
		}

		long TimeDuration = System.currentTimeMillis() - timeStart;

		System.out.printf("\nJava/Groovy Script Results Iteration # %d duration %d millis result %d\n",i, TimeDuration, k);

		myinlinescript();
	}
}
