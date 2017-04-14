package com.sample;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

/**
 * @author rumi april 2017
 *
 */
public class MyDroolsStateFull {

	public static final void main(String[] args) {
		try {
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("ksession-rules");

			MyFacts facts = new MyFacts();
			FactHandle handle1;

			int i;

			facts.setA(8);
			facts.setB(9);
			handle1 = kSession.insert(facts);

			long timeStart = System.currentTimeMillis();

			for (i = 0; i < 100000; i++) {

				facts.swapAB(); // this will force the rule to alternate.
				facts.setC(i);

				kSession.update(handle1, facts);
				kSession.fireAllRules();

				// System.out.printf("*a : %d b : %d k : %d\n", facts.a,
				// facts.b, facts.k);
			}

			long TimeDuration = System.currentTimeMillis() - timeStart;

			System.out.printf("\nDrools Results Iteration # %d duration %d millis result %d\n", i, TimeDuration,
					facts.k);

		} catch (Throwable t) {
			t.printStackTrace();
		}

	}

}
