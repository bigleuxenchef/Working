//package Closure
/**
 * @author rumi march 2017
 *
 */
package Closure;
import groovy.time.*

long a = 8
long b = 9
long i = 10
long k = 0

Binding binding = new Binding();
binding.setVariable("a", a);
binding.setVariable("b", b);

GroovyShell shell = new GroovyShell(binding);


myscript = new File("src/Closure/myrule.txt").text

script1 = shell.parse(myscript)
Closure cs = script1.run()
// closure as defined in groovy script will be used as a function in the loop
// in this example one parameter is the closure and the second calculated parameter

for(i = 0 ; i < 3; i++)
	cs({A -> A * 2 * i},10 - i)
	
	
myscript = new File("src/Closure/myrule2.txt").text
script1 = shell.parse(myscript)
cs = script1.run()
Closure c2
z = 5
c2 = {it-> cs(it)}

print "Closure in a Closure from GroovyScript :" + c2(3)

