public class TestBody
{
	public static void main(String[] args)
	{
		CheckTestBody();
	}

	private static void checkEquals(double expected, double actual, String label, double eps)
	{
		if (Double.isNaN(expected) || Double.isInfinite(expected))
		{
			System.out.println("FAIL: " + label + "Expected " + expected + "and get " + actual);
		}
		else if (Math.abs(actual - expected) <= eps * Math.max(expected, actual))
		{
			System.out.println("PASS: " + label + "Expected " + expected + "and get " + actual);
		}
		else
		{
			System.out.println("FAIL: " + label + "Expected " + expected + "and get " + actual);
		}
	}

	public static void CheckTestBody()
	{
		System.out.println("Checking TestBody...");
		Body b1 = new Body(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Body b2 = new Body(2.0, 1.0, 3.0, 4.0, 4e11, "jupiter.gif");
        Body b3 = new Body(4.0, 5.0, 3.0, 4.0, 5.0, "jupiter.gif");

        checkEquals(b1.calcForceExertedBy(b2), 133.4, "calcForceExertedBy()", 0.01);
        checkEquals(b1.calcForceExertedBy(b3), 6.67e-11, "calcForceExertedBy()", 0.01);
	}
}