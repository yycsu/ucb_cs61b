import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {

    static OffByN offBy5 = new OffByN(5);

    @Test
    public void Test_equalChars(){
        boolean actual_1 = offBy5.equalChars('e', 'a');
        boolean actual_2 = offBy5.equalChars('E', 'a');
        boolean actual_3 = offBy5.equalChars('9', '5');
        boolean actual_4 = offBy5.equalChars('&', '%');
        boolean actual_5 = offBy5.equalChars('a', 'f');
        boolean actual_6 = offBy5.equalChars('f', 'a');
        boolean actual_7 = offBy5.equalChars('f', 'h');


        assertFalse(actual_1);
        assertFalse(actual_2);
        assertFalse(actual_3);
        assertFalse(actual_4);
        assertTrue(actual_5);
        assertTrue(actual_6);
        assertFalse(actual_7);


    }
}
