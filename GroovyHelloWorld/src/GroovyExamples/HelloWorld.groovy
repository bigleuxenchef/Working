package GroovyExamples;


class myBaseClass {
	int a,b;
	String sum;
	myBaseClass()
	{
		a=0;b=0;
	}
	
	int applyfunc = {
		return sum();
	}
}


class HelloWorld {
	
	static main(args) {
		def mytest = new myBaseClass();
		mytest.a = 5;
		mytest.b = 6;
		mytest.sum = "{ a + b};"
		def mysum = "The sum of 2 and 3 equals {mytest.a} + {mytest.b}"
		System.out.println("result %d\n",5);
		System.out.println("Hello World");
	
	}

}
