# Drools samples

## Files to know before running Java Samples

### kmodule.xml [:eyes:](../../../resources/META-INF/kmodule.xml)

this file will setup 2 types of connections to Drools : Stateful and Statless, with the stateless being the default cofiguration if nothing is specified

```xml
<?xml version="1.0" encoding="UTF-8"?>
<kmodule xmlns="http://jboss.org/kie/6.0.0/kmodule">
    <kbase name="MyRulesBase" default="true" packages="rules">
    <ksession name="My-ksession-stateful" type="stateful" default="false" />
    <ksession name="My-kession-stateless" type="stateless" default="true" />
  </kbase>
</kmodule>

```
### Drools files `drl` [:eyes:](../../../resources/META-INF/kmodule.xml)

If you have created your project with eclipse, those `drl` files should be in `src/main/resources/rules`





## DroolsTest.java

This example shows different thing :

- fire drools from a stateful connection
- fire drools several times without recreating an object for each call
- Nested rules fired
- Rule setting condition that fires another rule
- Rule implementing a count down loop

[java program :eyes:](DroolsTest.java)

[Drools rules :eyes:](../../../resources/rules/Sample.drl)

### Create Stateful container

```java
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("My-ksession-stateful");
```

`My-ksession-stateful` has to be created in kmodule.xml as described above.

### Fire the first rule(s) 

```java
			// create and initialize an object Message
			Message message = new Message();
			message.setMessage("Hello World");
			message.setStatus(Message.HELLO);
			message.test = 1; // rules will not be fired if test == 1
			// insert the object in the container queue
			kSession.insert(message);
			System.out.println("**** Fire one : fire cascade ****");
			kSession.fireAllRules(); // fire the drools container

```

#### Output generated

```
**** Fire one : fire cascade ****
Hello Rule : Hello World
GoodBye Rule : Goodbye cruel world
Bonjour Rule Goodbye cruel world
```
What happens here ?

### Fire second set rule(s)

```java
			message = new Message();
			message.setMessage("Hello World");
			message.setStatus(Message.BONJOUR);
			message.test = 0; // show that a new rule testing a variable will be fired

			kSession.insert(message);
			System.out.println("**** Fire Two : fire different flavour ****");
			kSession.fireAllRules();
```
#### Output generated

```
**** Fire Two : fire different flavor ****
Bonjour Rule Hello World
Coucou rule fire -- test == 0 
```
### Fire third set rule(s)

```java
			message = new Message();
			message.setX(5);
			message.test = 1; // rules will not be fired if test == 1
			message.setStatus(4);
			System.out.println("**** Fire Three : loop implemented through rule : fire once ****");
			FactHandle handle1 = kSession.insert(message);
			kSession.fireAllRules();


```
#### Output generated

```
**** Fire Three : loop implemented through rule : fire once ****
Count Down Rule x : 4
Count Down Rule x : 3
Count Down Rule x : 2
Count Down Rule x : 1
Count Down Rule x : 0
```
### Fire fourth set rule(s)

```
			message.setX(3);
			kSession.update(handle1, message);
			System.out.println("**** Fire Three : loop implemented through rule: fire twice ****");
			kSession.fireAllRules();

```
#### Output generated

```
**** Fire Three : loop implemented through rule: fire twice ****
Count Down Rule x : 2
Count Down Rule x : 1
Count Down Rule x : 0
```


## MyDroolsStateFull.java
---

This example iterate a for loop on a stateful drools container. Here is the definition :

```
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("My-ksession-stateful");
```
The specific of a stateful container is that an object that needs to be re-submitted once again to the rules engine, needs to call ```update``` function.

```
			handle1 = kSession.insert(facts);
					
			for (i = 0; i < 100000; i++) {

				facts.swapAB(); // this will force the rule to alternate.
				facts.setC(i);

				kSession.update(handle1, facts); // refresh the engine to accept processing same object again
				kSession.fireAllRules();

				// System.out.printf("*a : %d b : %d k : %d\n", facts.a,
				// facts.b, facts.k);
			}
```
### Time to run 100000 iteration of the same rule

```
Drools Results Iteration # 100000 duration 527 millis result 5000850000
```


## MyDroolsStateLess.java
---

This example accomplished exactly same as stateful by using other stateless connection

```
			KieServices kieServices = KieServices.Factory.get();
			KieContainer kContainer = kieServices.getKieClasspathContainer();
			StatelessKieSession kSession = kContainer.newStatelessKieSession();
```

Notice the very difference here `execute()` assume `facts` have been submitted for the first time at each iteration. No state is kept in the container from each execution. There is no more `fireAllRules()`.


```
		for (i = 0; i < 100000; i++) {
				facts.swapAB(); // this will force the rule to alternate.
				facts.setC(i);

				kSession.execute(facts);

			//	System.out.printf("*a : %d b : %d k : %d\n",facts.a,facts.b,facts.k);
			}
```

### Time to run 100000 iteration of the same rule

```
Drools Results Iteration # 100000 duration 2258 millis result 5000850000
```
