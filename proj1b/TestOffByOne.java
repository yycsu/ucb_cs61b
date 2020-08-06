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

        assertEquals(actual_1, true);
        assertEquals(actual_2, false);
        assertEquals(actual_3,false);
        assertEquals(actual_4,false);
        assertEquals(actual_5, true);
    }
} //Uncomment this class once you've created your CharacterComparator interface and OffByOne class.