Disclaimer

I am interested in testing technologies and the best way is to do it yourself and experiment. Just do what others did already is a way to learn. This is why I posting what I have done by myself without any pretending to invent or teach anything to anyone.

Happy to share with anyone, happy anyone takes a copy, but I would recommend not to use any of this material as it is more for personal experiment than to achieve anything special other than learning.


# Groovy 'Hello World' samples

This section is dedicated to explore Groovy in the context of the integration of scripting language in solution.
The reason of this use case is to test and validate the usage of Groovy as a potential solution for Rules engine. At this very moment someone has come to me with a solution where scripting will be a json files where hierarchy will model operator and operands structure. The need was to allow 'IF ... THEN ... ELSE ..." simple rule (where the condition can be quite complex). I thought their should be a easier way as well as more practical of trying building the rule itself as json rather than a script

```json
{
"RuleID": <id>,
"RuleName" : <name>,
"Rule" : {
"LeftOperand" : <value>,
"RightOperand" : <value>,
"Operator": <operator>
},
"Operator":<operator>
...
...
...
}
```

I thought if we could do something more like her under, it would be not only easier to use for the end user but it will reduce the maintenance.

```json
{
"RuleID": <id>,
"RuleName" : <name>,
"ScriptTpye" : "Groovy"|"JavaScript"|...
"Script": "if (a != b && c == z) { <do something>} else result = a + z"
}
```



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

### Results
```
Using Parse and Run Iteration # 100
Groovy Script Results duration 0.021 seconds millis result 5850

Using Evaluate Iteration # 100
Groovy Script Results duration 0.899 seconds millis result 5850


```

This is clear that Parse and Run is much faster than evaluate, no big deal but worth mentioning this without affecting any of the feature like bindings.


## Groovy in Java

```java
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
		}

		long TimeDuration = System.currentTimeMillis() - timeStart;

		System.out.printf("\nJava/Groovy Script Results Iteration # %d duration %d millis result %d\n",i, TimeDuration, k);

		myinlinescript();

	}

}
```


### Results

```
Java/Groovy Script Results Iteration # 100000 duration 121 millis result 5000850000
Java/Inline        Results Iteration # 100000 duration 4 millis result 5000850000

```

## Groovy through Java Scripting API

this can be achieve using `javax.script.*` and most particularly the java object `ScriptEngineManager`

```java
package rules;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

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

		System.out.printf("\nJava Scripting API/Groovy  Results Iteration # %d duration %d millis result %d\n",i, TimeDuration, k);

	}

}
```

### Results

```
Java Scripting API/Groovy  Results Iteration # 100000 duration 1459 millis result 5000850000
```

## Conclusions

### Results

```
Java/Groovy Script Results Iteration # 100000 duration 121 millis result 5000850000
Java/Inline        Results Iteration # 100000 duration 4 millis result 5000850000
Java Scripting API/Groovy  Results Iteration # 100000 duration 1459 millis result 5000850000
```

The results speak for themselves. However let's try to see if we can decouple compilation from execution on JAX API.

interestingly by using the `Compile` interface of `ScriptEngine`, we can decoupled the compilation from the execution and result are better but still more than Groovy scripting API

here is the code to considered

```java
	// run with compiled script first to reduce overhead of parsing
		timeStart = System.currentTimeMillis();

		CompiledScript cs;

		cs = ((Compilable) engine).compile(myscript);
		k = 0;

		for (i = 0; i < 100000; i++) {
			engine.put("c", i);
			k += Long.parseLong(cs.eval().toString());
			// System.out.printf("*a : %d b : %d c : %d\n",a,b,k);

		}

		TimeDuration = System.currentTimeMillis() - timeStart;

		System.out.printf(
				"\nCompiled Script\n"
						+ "Java Scripting API/Groovy  Results Iteration # %d duration %d millis result %d\n",
				i, TimeDuration, k);
```


### Results

```
Java/Groovy Script Results Iteration # 100000 duration 121 millis result 5000850000

Java/Inline        Results Iteration # 100000 duration 4 millis result 5000850000

Eval script
Java Scripting API/Groovy  Results Iteration # 100000 duration 1545 millis result 5000850000

Compiled Script
Java Scripting API/Groovy  Results Iteration # 100000 duration 551 millis result 5000850000
```

# RUN Samples

```
//from eclipse
//Case 1

launch myJruleengine1.java

//Case 2
launch myJruleengine2.java

//Case 3

launch myJruleengine1.groovy
```

# Explore the power of scripting

#### File `myrule.txt'

Play with the file and launch again case 1-3
as example 
  * uncomment the line `println ...`
  * uncomment the line `if (a > b)  a + c else (2 *b) + c + a` and comment the last line


```
// Rumi march 2017
// this script will be consumed by GroovyShell
// you can remove the comment on println to display each time the rule is invoked
// println "a : ${a} b : ${b} c : ${c}"
// here under is the rule that will be consumed by GroovyShell,
// parameters a, b and c will be "binded"
//if (a > b)  a + c else (2 *b) + c + a 
 if (a > b)  a + c else b + c
```


### Interesting link

http://groovy-lang.org/dsls.html
http://groovy-lang.org/integrating.html#jsr223
http://www.programcreek.com/java-api-examples/groovy.lang.GroovyShell
http://www.programcreek.com/java-api-examples/index.php?api=javax.script.CompiledScript
http://www.mrhaki.com/






note

next assignment : inject closure into script ...
http://mrhaki.blogspot.ca/2010/08/groovy-goodness-store-closures-in.html


