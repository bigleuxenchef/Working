/**
 * 
 */
/**
 * @author rumi march 2017
 *
 */
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

myscript = new File("src/groovyscript/myrule.txt").text

script1 = shell.parse(myscript)

def timeStart = new Date()
//shell.setVariable("c",i)

for(i = 0; i< 100;i++)
{
	shell.setVariable("c",i)
	k+=script1.run()
}

def timeStop = new Date()

TimeDuration duration = TimeCategory.minus(timeStop, timeStart)

println("Using Parse and Run Iteration # ${i}\nGroovy Script Results duration ${duration} millis result ${k}\n")

timeStart = new Date()
k = 0
for(i = 0; i< 100;i++)
{
	shell.setVariable("c",i)
	k+=shell.evaluate(myscript)
}

timeStop = new Date()

duration = TimeCategory.minus(timeStop, timeStart)

println("Using Evaluate Iteration # ${i}\nGroovy Script Results duration ${duration} millis result ${k}\n")
