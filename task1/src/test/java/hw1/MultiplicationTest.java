package hw1;

import com.epam.tat.module4.Calculator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;

public class MultiplicationTest {

	Calculator c=new Calculator();

	   @DataProvider(name="Dmultiplication")
	   public static Object[][] MultiDoubles() {
	       return new Object[][] {{49.0, 2.5, 122.5},{0.0, 0.89, 0.0},{-5.6, 8.5, -5.6*8.5},
	    	                      {-8.8, -9.8,-8.8*-9.8},{0.0, 0.0, 0.0},
	    	                      {12345.123, 54321.987, 12345.123*54321.987}};
	   }
	   
	   @DataProvider(name="Lmultiplication")
	   public static Object[][] MultiLong() {
	       return new Object[][] {{49L, 2L, 98L},{0L, 5L, 0L},{-5L, 8L, -40L},
	    	                      {-8L, -9L, 72L},{0L, 0L, 0L}};
	   }

	   @Test(dataProvider = "Dmultiplication" )
	   public void testMultiDoubles(double a, double b, double res) {
	       Assert.assertEquals(c.mult(a, b), res);
	       Assert.assertEquals(c.mult(a, b), c.mult(b, a));
	   }
	   
	   @Test(dataProvider = "Lmultiplication" )
	   public void testMultiLong(long a, long b, long res) {
	       Assert.assertEquals(c.mult(a, b), res);
	       Assert.assertEquals(c.mult(a, b), c.mult(b, a));
	   }
}
