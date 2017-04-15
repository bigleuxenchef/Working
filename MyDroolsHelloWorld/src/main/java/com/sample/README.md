# Drools samples

## Files to know before running Java Samples

### [kmodule.xml](../../../resources/META-INF/kmodule.xml)

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


## DroolsTest.java

## MyDroolsStateFull.java

## MyDroolsStateLess.java