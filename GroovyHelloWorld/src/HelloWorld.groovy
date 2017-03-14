
class test {
	int a,b;
	String sum;
	test()
	{
		a=0;b=0;
	}
	
	int applyfunc = {
		return sum();
	}
}


class HelloWorld {
	
	static main(args) {
		def mytest = new test();
		test.a = 5;
		test.b = 6;
		test.sum = "{ a + b};"
		def mysum = "The sum of 2 and 3 equals ${test.a + test.b}"
		System.out.println("result %d\n",5);
		System.out.println("Hello World");
	
	}

}
