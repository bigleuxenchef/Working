
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
		Binding binding = new Binding();
		Trade T = new Trade(a,b);
		binding.setVariable("T", T);

		GroovyShell shell = new GroovyShell(binding);

		String myscript = new String(Files.readAllBytes(Paths.get("src/groovyscript/myrule3.groovy")));
		Script script1 = shell.parse(myscript);
		long timeStart = System.currentTimeMillis();

		//shell.evaluate(myscript);		
		for (i = 0; i < 100000; i++) {
			
			shell.setVariable("c", i);

		k += Integer.parseInt((script1.run()).toString());

		}
		
		long TimeDuration = System.currentTimeMillis() - timeStart;

		System.out.printf("\nJava/Groovy Script Class wrapper Results Iteration # %d duration %d millis result %d\n",i, TimeDuration, k);
	}
}
