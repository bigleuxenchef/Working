# Drools samples

## Files to know before running Java Samples

### kmodule.xml[here](../../../resources/META-INF/kmodule.xml)

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

[java program here](DroolsTest.java)

[Drools rules here](../../../resources/rules/Sample.drl)




## MyDroolsStateFull.java

## MyDroolsStateLess.java