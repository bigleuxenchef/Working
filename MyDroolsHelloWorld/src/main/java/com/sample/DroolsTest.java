package com.sample;


import java.util.Collection;
import java.util.Iterator;

import org.kie.api.KieServices;
import org.kie.api.definition.KiePackage;
import org.kie.api.definition.rule.Rule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

	public static final void main(String[] args) {
		try {
			// load up the knowledge base
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("My-ksession-stateful");

			// go !
			// create and initialize an object Message
			Message message1 = new Message();
			message1.setMessage("Hello World");
			message1.setStatus(Message.HELLO);
			message1.test = 1; // rules will not be fired if test == 1
			// insert the object in the container queue
			FactHandle handle1 = kSession.insert(message1); // save the handle

			System.out.println("**** Fire one : fire cascade ****");
			kSession.fireAllRules(); // fire the drools container

			Message message2 = new Message();
			message2.setMessage("Hello World");
			message2.setStatus(Message.BONJOUR);
			message2.test = 0; // show that a new rule testing a variable will
								// be fired
			FactHandle handle2 = kSession.insert(message2); // save the handle
															// for further usage
			System.out.println("**** Fire Two : fire different flavour ****");
			kSession.fireAllRules();

			Message message3 = new Message();
			message3.setX(5);
			message3.test = 1; // rules will not be fired if test == 1
			message3.setStatus(4);
			System.out.println("**** Fire Three : loop implemented through rule : fire once ****");
			// save the handle of the message inserted
			FactHandle handle3 = kSession.insert(message3);
			kSession.fireAllRules();

			// here we add one flavor, as we have a stateful session
			// same facts can be re-submitted when being changed!
			message3.setX(3);
			kSession.update(handle3, message3);
			System.out.println("**** Fire Three : loop implemented through rule: fire twice ****");
			kSession.fireAllRules();

			// here under we will resubmitted previous facts to the rules engine
			// as we are not changing the facts. the rules will start with
			// previous status then the outcome may be different than the first
			// time the rule was fired.

			kSession.update(handle1, message1);
			message3.setX(5);
			kSession.update(handle3, message3);
			System.out.println("**** Fire Four : fire the rules engine again for previous facts ****");

			kSession.fireAllRules();

			System.out.println("**** Scanning KieBase to Retrieve Rules ****");

			Collection<KiePackage> pckgs;

			pckgs = kSession.getKieBase().getKiePackages();
			
			
			
			Iterator<KiePackage> pckgsiterator = (Iterator<KiePackage>) pckgs.iterator();

			while (pckgsiterator.hasNext()) { // loop using while
				KiePackage pckg = pckgsiterator.next();
				Collection<Rule> rules = pckg.getRules();
				String pckgname = pckg.getName();

				for (Rule r : rules) // loop using for each
					System.out.println("Collection Name" + pckgname + " - Rules Name " + r.getName());

			}

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public static class Message {

		public static final int HELLO = 0;
		public static final int GOODBYE = 1;
		public static final int BONJOUR = 2;

		public int test;
		private String message;

		private int status;
		private int x = 0;

		public String getMessage() {
			return this.message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public int getStatus() {
			return this.status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

	}

}
