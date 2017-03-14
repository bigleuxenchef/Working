# Groovy 'Hello World' samples

This section is dedicated to explore Groovy in the context of integration of scripting language in solution.


# Mini rules engine

## 100% Groovy

let's look first at the following Groovy script

```
package rules;
import groovy.time.*

long a = 8
long b = 9
long i = 0
long k = 0

Binding binding = new Binding();
binding.setVariable("a", a);
binding.setVariable("b", b);

GroovyShell shell = new GroovyShell(binding);

myscript = new File("src/rules/myrule.txt").text

script1 = shell.parse(myscript)

def timeStart = new Date()
//shell.setVariable("c",i)

for(i = 0; i< 100000;i++)
{
	shell.setVariable("c",i)
	k+=script1.run()
}

def timeStop = new Date()

TimeDuration duration = TimeCategory.minus(timeStop, timeStart)

println("Groovy Script Results duration ${duration} millis result ${k}\n")
```
here is the file `myrule.txt`

```
 if (a > b)  a + c else b + c
```


* the goal here is to demonstrate how Groovy can dynamically consumed a piece of code and binds the parameters that are referenced in the piece of code that can come any time in the process (stored in file, prompted from user or part of hastable as building block for 'mini rules engine').

* here the piece of code does not know anything about `a`, `b` or `c` but the program using the code does.

* Groovy can evaluate the script in two steps as required in this case either running either

```
shell.evaluate(myscript)
```
or
```
shell.parse(myscript)
script1.run()
```

## Results
```
Using Parse and Run Iteration # 100
Groovy Script Results duration 0.021 seconds millis result 5850

Using Evaluate Iteration # 100
Groovy Script Results duration 0.899 seconds millis result 5850


```

This is clear that Parse and Run is much faster than evaluate, no big deal but worth mentioning this without affecting any of the feature like bindings.


## Groovy in Java

```

package rules;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

public class myJruleengine {
	static void myinlinescript() {
		long a = 8;
		long b = 9;
		long i = 0;
		long k = 0;
		long timeStart = System.currentTimeMillis();

		for (i = 0; i < 100000; i++) {
			
			//System.out.printf("*a : %d b : %d c : %d\n",a,b,k);

			k += (a > b) ? a + i : b + i;

		}

		long TimeDuration = System.currentTimeMillis() - timeStart;

		System.out.printf("\nInline Results ***** duration %d millis result %d\nBye world!", TimeDuration, k);

	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("hello world!");

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
			// script1.run();
		}

		long TimeDuration = System.currentTimeMillis() - timeStart;

		System.out.printf("\nJava/Groovy Script Results duration %d millis result %d\nBye world!", TimeDuration, k);

		myinlinescript();

	}

}

```


## Results

```
Java/Groovy Script Results Iteration # 100000 duration 121 millis result 5000850000
Java/Inline        Results Iteration # 100000 duration 4 millis result 5000850000

```
