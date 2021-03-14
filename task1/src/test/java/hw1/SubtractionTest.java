package hw1;

import com.epam.tat.module4.Calculator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;

public class SubtractionTest {

	Calculator c=new Calculator();

	   @DataProvider(name="Dsubtraction")
	   public static Object[][] SubDoubles() {
	       return new Object[][] {{49.0, 51.0, -2.0},{0.0, -0.89, 0.89},{-5.6, -77.5, 71.9},
	    	                      {-8.8, 0.0, -8.8},{0.0, 0.0, 0.0},{77.78, 77.78, 0.0},
	    	                      {0.0, 786.54, -786.54},{0.005675, 0.001235, 0.00444},
	    	                      {-123456789101.123, 110987654321.987, -234444443423.11}};
	   }
	   
	   @DataProvider(name="Lsubtraction")
	   public static Object[][] SubLong() {
		   return new Object[][] {{49L, 51L, -2L},{-40L, 40L, -80L},{5L, 5L, 0L},
               {-8L, 0L, -8L},{0L, 0L, 0L},{5L, -8L, 13L}};
	   }

	   @Test(dataProvider = "Dsubtraction" )
	   public void testSubDoubles(double a, double b, double res) {
	       Assert.assertEquals(c.sub(a, b), res);
	   }
	   
	   @Test(dataProvider = "Lsubtraction" )
	   public void testSubLong(long a, long b, long res) {
	       Assert.assertEquals(c.sub(a, b), res);
	   }
}
