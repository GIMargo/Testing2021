package hw1;

import com.epam.tat.module4.Calculator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;


public class AdditionTest 
{
   Calculator c=new Calculator();

   @DataProvider(name="Daddition")
   public static Object[][] AddDoubles() {
       return new Object[][] {{49.0, 51.0, 100.0},{0.0, 0.89, 0.89},{-5.6, -77.5, -83.1},
    	                      {-8.8, 0.0, -8.8},{0.0, 0.0, 0.0},{-77.7, 77.7, 0.0},
    	                      {123456789101.123, 110987654321.987, 234444443423.11}};
   }
   
   @DataProvider(name="Laddition")
   public static Object[][] AddLong() {
       return new Object[][] {{49L, 51L, 100L},{-40L, 40L, 0L},{-5L, -77L, -82L},
    	                      {-8L, 0L, -8L},{0L, 0L, 0L}};
   }

   @Test(dataProvider = "Daddition" )
   public void testAddDoubles(double a, double b, double res) {
       Assert.assertEquals(c.sum(a, b), res);
       Assert.assertEquals(c.sum(a, b), c.sum(b, a));
   }
   
   @Test(dataProvider = "Laddition" )
   public void testAddLong(long a, long b, long res) {
       Assert.assertEquals(c.sum(a, b), res);
       Assert.assertEquals(c.sum(a, b), c.sum(b, a));
   }

}
