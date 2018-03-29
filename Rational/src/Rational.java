
public class Rational {

	private double rationalNumber;
	
	private int numerator;
	private int denominator;
	
	private int resultNum;
	private int resultDenom;

	public Rational(int numerator, int denominator) {

		this.denominator = denominator == 0 ? 1 : denominator; 
		this.numerator=numerator;
		
		
	this.rationalNumber = numerator / this.denominator;
	}

	public Rational(int numerator) {
		 this.rationalNumber = numerator;
		 this.denominator = 1;
	}

	public Rational add(Rational givenRational) {
		resultNum = (numerator* givenRational.denominator) + (denominator*givenRational.numerator);
		resultDenom = (denominator * givenRational.denominator);
		return this;
	}

	public Rational subtract(Rational givenRational) {
		resultNum = (numerator* givenRational.denominator) - (denominator*givenRational.numerator);
		resultDenom = (denominator * givenRational.denominator);
		return this;
	}

	public Rational multiply(Rational givenRational) {
		resultNum= numerator * givenRational.numerator;
		resultDenom = denominator * givenRational.denominator;
		return this;
	}

	public Rational divide(Rational givenRational) {
		resultNum = numerator * givenRational.denominator;
		resultDenom = denominator * givenRational.numerator;		
		return this;
	}

	public boolean equals(Rational givenRational) {
		if (((double) numerator / (double)denominator)==((double)givenRational.numerator / (double)givenRational.denominator))
			return true;
		else
			return false;
	}

	public boolean isLessThan(Rational givenRational) {
		if (((double)numerator / (double)denominator) < ((double)givenRational.numerator / (double)givenRational.denominator))
			return true;
		else
			return false;
	}


	public Rational simplify() {

		int divisor =1;
		int GCD=1;
		for (divisor=1; divisor <= resultNum; divisor++){
			if((resultNum % divisor ==0) && (resultDenom % divisor ==0 ))
				GCD = divisor;
		}
		
		resultNum = resultNum / GCD;
		resultDenom = resultDenom / GCD;
		return this;
	}

	public String toString() {
		String rationalString = (resultNum + "/" + resultDenom);
		return rationalString;
	}

}
