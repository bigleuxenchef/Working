
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
			System.out.printf("*a : %d b : %d c : %d\n",a,b,k);
			// script1.run();
		}

		long TimeDuration = System.currentTimeMillis() - timeStart;

		System.out.printf("\nJava/Groovy Script Results Iteration # %d duration %d millis result %d\n",i, TimeDuration, k);

		myinlinescript();
	}
}
