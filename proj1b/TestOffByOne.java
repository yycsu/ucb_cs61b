import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void Test_equalChars(){
        boolean actual_1 = offByOne.equalChars('a', 'b');
        boolean actual_2 = offByOne.equalChars('B', 'a');
        boolean actual_3 = offByOne.equalChars('a', 'c');
        boolean actual_4 = offByOne.equalChars('a', '9');
        boolean actual_5 = offByOne.equalChars('&', '%');

        assertTrue(actual_1);
        assertFalse(actual_2);
        assertFalse(actual_3);
        assertFalse(actual_4);
        assertTrue(actual_5);
    }
} //Uncomment this class once you've created your CharacterComparator interface and OffByOne class.