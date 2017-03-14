# Groovy 'Hello World' samples

This section is dedicated to explore Groovy in the context of integration of scripting language in solution.


# Mini rules engine

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
..* `shell.evaluate(myscript)`
or
..* shell.parse(myscript)
`script1.run()`


