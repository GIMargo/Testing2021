package hw1;

import com.epam.tat.module4.Calculator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;


public class DivisionTest {

	Calculator c=new Calculator();

	   @DataProvider(name="Ddivision")
	   public static Object[][] DivDoubles() {
	       return new Object[][] {{49.0, 7.0, 7.0},{9.52, -5.6, 9.52/-5.6},{-5.6, -5.6, 1.0},
	    	                      {0.0, 8.8, 0.0/8.8},{0.0, 0.0, 0.0/0.0}, {-9.8, 0.0, -9.8/0.0},
	    	                      {0.123, 654.5, 0.123/654.5}};
	   }

	   @DataProvider(name="Ldivision")
	   public static Object[][] DivLong() {
	       return new Object[][] {{49L, 7L, 7L},{9L, -5L, 9L/-5L},{-5L, -5L, 1L},
	    	                      {0L, 8L, 0L/8L},{0L, 0L, 0L/0L}, {-9L, 0L, -9L/0L},
	    	                      {7L, 54L, 7L/54L}};
	   }

	   @Test(dataProvider = "Ddivision" )
	   public void testDivDoubles(double a, double b, double res) {
	       Assert.assertEquals(c.div(a, b), res);
	   }
	   
	   @Test(dataProvider = "Ldivision" )
	   public void testDivLong(long a, long b, long res) {
	       Assert.assertEquals(c.div(a, b), res);
	   }

}
