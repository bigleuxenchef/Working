package com.sample;

import org.kie.api.KieServices;
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
            Message message = new Message();
            message.setMessage("Hello World");
            message.setStatus(Message.HELLO);
            message.test = 1; // rules will not be fired if test == 1
            
            kSession.insert(message);
            System.out.println("**** Fire one : fire cascade ****");
            kSession.fireAllRules();
 
            message = new Message();
            message.setMessage("Hello World");
            message.setStatus(Message.BONJOUR);
            message.test = 0; // show that a new rule testing a variable will be fired
            
            kSession.insert(message);
            System.out.println("**** Fire Two : fire different flavour ****");
            kSession.fireAllRules();
            
            
            System.out.println("**** Fire Three : loop : fire once ****");

            message = new Message();
            message.setX(5);
            message.test = 1; // rules will not be fired if test == 1
            message.setStatus(4);

            FactHandle handle1  = kSession.insert(message);
            kSession.fireAllRules();
            
            System.out.println("**** Fire Three : loop : fire twice ****");
            
            message.setX(3);
            kSession.update(handle1, message);
			kSession.fireAllRules();

            
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
