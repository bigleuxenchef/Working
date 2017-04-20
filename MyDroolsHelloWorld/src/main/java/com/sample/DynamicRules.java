package com.sample;

import static org.junit.Assert.assertFalse;

import java.util.Collection;
import java.util.function.Function;

import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

/**
 * @author rumi April 2017
 *
 */
@SuppressWarnings({ "deprecation", "restriction" })
public class DynamicRules {
	public static void main(String[] args) throws Exception {

		Object[] objects = new Object[] { wrap(3), wrap(1), wrap(4), wrap(1), wrap(5) };

		new RuleRunner().runRulesFromFile(new String[] { "Example1.drl", "Example2.drl" }, objects);

		String rule1 = "rule \"Rule 03\"\n" + " when \n" + "   $number : Number( )\n"
				+ "   not Number( intValue < $number.intValue )\n" + "then\n"
				+ "   System.out.println(\"Rule 03 : Number found with value: \" + $number.intValue() ); \n"
				+ "   retract( $number );\n" + "end\n";

		String rule2 = "rule \"Rule 02\"\n" + "when \n" + "Number( $intValue : intValue )\n " + "then\n "
				+ "System.out.println(\"Rule 02 : Number found with value: \"+$intValue); \n" + "end\n";

		String rule3 = "rule \"Rule 01\"\n" + "when\n" + " eval( 1==1 )\n" + "then\n"
				+ "System.out.println( \"Rule 01 Works\" );\n" + "end;\n";

		new RuleRunner().runRulesFromString(new String[] { rule1, rule2, rule3 }, objects);

	}

	private static Integer wrap(int i) {
		return new Integer(i);
	}

	public static class RuleRunner {

		public RuleRunner() {
		}

		Function<String, Resource> FromString = x -> ResourceFactory.newByteArrayResource(x.getBytes());
		Function<String, Resource> FromFile = x -> {
			System.out.println("Loading file: " + x);
			return ResourceFactory.newClassPathResource(x, this.getClass());
		};

		public void runRulesFromString(String[] rules, Object[] facts) throws Exception {
			runRulesFromFunction(rules, facts, FromString);

		}

		public void runRulesFromFile(String[] rules, Object[] facts) throws Exception {
			runRulesFromFunction(rules, facts, FromFile);

		}

		public void runRulesFromFunction(String[] rules, Object[] facts, Function<String, Resource> resourcefactory)
				throws Exception {

			KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
			KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

			for (int i = 0; i < rules.length; i++) {
				kbuilder.add(resourcefactory.apply(rules[i]), ResourceType.DRL);

				assertFalse(kbuilder.getErrors().toString(), kbuilder.hasErrors());
			}

			Collection<KnowledgePackage> pkgs = kbuilder.getKnowledgePackages();
			kbase.addKnowledgePackages(pkgs);
			StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
			for (int i = 0; i < facts.length; i++)
			{
				System.out.println("Inserting fact: " + facts[i]);

				ksession.insert(facts[i]);
			}
			ksession.fireAllRules();

		}
	}
}