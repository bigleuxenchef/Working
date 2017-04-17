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
### Drools files `drl`

If you have created your project with eclipse, those `drl` files should be in `src/main/resources/rules`

## DroolsTest.java

This example shows diffent thing :

- fire drools from a stateful connection
- fire drools several times without recreating an object for each call
- Nested rules fired
- Rule setting condition that fires another rule
- Rule implementing a count down loop

[java program :eyes:](DroolsTest.java)

[Drools rules :eyes:](../../../resources/rules/Sample.drl)

### Create Stateful container

```
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("My-ksession-stateful");``` :warning:
```
```




## MyDroolsStateFull.java

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


## MyDroolsStateLess.java