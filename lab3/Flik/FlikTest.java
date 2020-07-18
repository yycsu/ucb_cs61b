import static org.junit.Assert.*;
import org.junit.Test;

public class FlikTest
{
    @Test(timeout = 1000)
    public void testFlik()
    {
        boolean p = Flik.isSameNumber(128, 128);
        assertTrue(true == p );
    }
}
