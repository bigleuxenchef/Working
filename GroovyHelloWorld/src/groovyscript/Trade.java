/**
 * @author rumi
 *
 */


package groovyscript;

public class Trade {
 public long amount1, amount2,ret;

 
public long getAmount1() {
	return amount1;
}

public void setAmount1(long amount1) {
	this.amount1 = amount1;
}

public long getAmount2() {
	return amount2;
}

public void setAmount2(long amount2) {
	this.amount2 = amount2;
}

/**
 * @param a
 * @param b
 */
public Trade(long a, long b) {
	super();
	this.amount1 = a;
	this.amount2 = b;
	ret=0;
}

}
