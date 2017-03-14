package rules;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import groovy.lang.Binding;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import groovy.time.*;


public class myJruleengine {
    static void myinlinescript()
    {
    	long a = 8;
		long	b = 9;
		long i = 0;
		long k=0;
		long	 timeStart = System.currentTimeMillis();

				for(i = 0; i< 100000;i++)
				{
					;
					
				k+= (a > b)?a + i:b + i;
			
				}
				
		long TimeDuration  = System.currentTimeMillis() - timeStart;
		
		System.out.printf("\nInline Results ***** duration %d millis result %d\nBye world!",TimeDuration,k);
	
    	
    }
    
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("hello world!");
		
		long a = 8;
		long	b = 9;
		long i = 0;

				Binding binding = new Binding();
				  binding.setVariable("a", a);
				  binding.setVariable("b", b);
				  
				GroovyShell shell = new GroovyShell(binding);
				
				String myscript = new String (Files.readAllBytes(Paths.get("src/rules/myrule.txt")));

				Script		script1 = shell.parse(myscript);
				long k=0;
				long	 timeStart = System.currentTimeMillis();
						//shell.setVariable("c",i);

						for(i = 0; i< 100000;i++)
						{
							shell.setVariable("c",i);
							
						k+= Integer.parseInt((script1.run()).toString());
						//	script1.run();
						}
						
				long TimeDuration  = System.currentTimeMillis() - timeStart;
				
				System.out.printf("\nJava/Groovy Script Results duration %d millis result %d\nBye world!",TimeDuration,k);
				
				myinlinescript();
		
	}

}
