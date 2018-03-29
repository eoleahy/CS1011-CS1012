import java.util.Scanner;

public class testRational {

	public static void main(String[] args) {

		System.out.println("Enter your first numerator");
		Scanner scanner = new Scanner(System.in);
		int numerator = scanner.nextInt();
		System.out.println("Enter your first denominator");
		int denominator = scanner.nextInt();

		System.out.println("Enter your second numerator");
		int secondNumerator = scanner.nextInt();

		System.out.println("Enter your second denominator");
		
		int secondDenominator = scanner.nextInt();
		secondDenominator =secondDenominator == 0 ? 1 : secondDenominator;

		Rational firstRational = new Rational(numerator, denominator);
		Rational secondRational = new Rational(secondNumerator, secondDenominator);
		
		
		if (firstRational.equals(secondRational)) 
			System.out.println("These numbers are equal");
		
		else if (firstRational.isLessThan(secondRational))
			System.out.println("Your first rational number is less than the second.");
		
		else 
			System.out.println("Your first rational number is greater than the first");
		
		String rationalString;
		
		firstRational.add(secondRational);
		firstRational.simplify();
		rationalString = firstRational.toString();
		System.out.println("Addition: " + rationalString);

		firstRational.subtract(secondRational);
		firstRational.simplify();
		rationalString = firstRational.toString();
		System.out.println("Subtraction: " + rationalString);
		
		firstRational.multiply(secondRational);
		firstRational.simplify();
		rationalString = firstRational.toString();
		System.out.println("Multiplication: " + rationalString);
		
		firstRational.divide(secondRational);
		firstRational.simplify();
		rationalString = firstRational.toString();
		System.out.println("Division: " + rationalString);
		
	}
}
