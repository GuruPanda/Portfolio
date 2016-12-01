import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
public class IntManTester{
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test1() {
	assertEquals(120, IntMan.factorial(5));
    }
    @Test
    public void test2() {
	assertEquals(0, IntMan.factorial(-5));
    }
    @Test
    public void test3() {
	assertEquals(1, IntMan.factorial(0));
    }
    @Test
    public void test4() {
	assertEquals(false, IntMan.isPrime(6));
    }
    @Test
    public void test5() {
	assertEquals(false, IntMan.isPrime(-5));
    }
    @Test
    public void test6() {
	assertEquals(true, IntMan.isPrime(7));
    }
    @Test
    public void test7() {
	assertEquals(3, IntMan.largest(1,2,3));
    }
    @Test
    public void test8() {
	assertEquals(-1, IntMan.largest(-1,-2,-3));
    }
    @Test
    public void test9() {
	assertEquals(3, IntMan.largest(3,3,3));
    }
    @After
    public void tearDown() throws Exception {
    }
}
