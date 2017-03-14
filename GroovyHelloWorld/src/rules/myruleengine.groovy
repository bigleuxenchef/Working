/**
 * 
 */
/**
 * @author rumi
 *
 */
package rules;
import groovy.time.*
//def myclosure = {a, b -> if (a > b) ["a" a] else {"b" b}}

long a = 8
long b = 9
def long i = 0

Binding binding = new Binding();
  binding.setVariable("a", a);
  binding.setVariable("b", b);
  
GroovyShell shell = new GroovyShell(binding);
 

//shell.setVariable("c",45)
myscript = new File("src/rules/myrule.txt").text

script1 = shell.parse(myscript)
//shell.getVariable("c")

def long k = 0
def timeStart = new Date()
//shell.setVariable("c",i)

for(i = 0; i< 100000;i++)
{
	shell.setVariable("c",i)
	
k+=script1.run()

}

def timeStop = new Date()

TimeDuration duration = TimeCategory.minus(timeStop, timeStart)
println duration

println(k)


