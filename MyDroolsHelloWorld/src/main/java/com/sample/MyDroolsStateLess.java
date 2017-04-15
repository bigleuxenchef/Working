

package com.sample;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

/**
 * @author rumi april 2017
 *
 */
public class MyDroolsStateLess {

	public static final void main(String[] args) {
		try {

			KieServices kieServices = KieServices.Factory.get();
			KieContainer kContainer = kieServices.getKieClasspathContainer();
			StatelessKieSession kSession = kContainer.newStatelessKieSession();

			MyFacts facts = new MyFacts();

			int i = 0;
			facts.setA(8);
			facts.setB(9);
			long timeStart = System.currentTimeMillis();

			for (i = 0; i < 100000; i++) {
				facts.swapAB(); // this will force the rule to alternate.
				facts.setC(i);

				kSession.execute(facts);

			//	System.out.printf("*a : %d b : %d k : %d\n",facts.a,facts.b,facts.k);
			}

			long TimeDuration = System.currentTimeMillis() - timeStart;

			System.out.printf("\nDrools Results Iteration # %d duration %d millis result %d\n", i, TimeDuration,
					facts.getK());

		} catch (Throwable t) {
			t.printStackTrace();
		}

	}

}
