import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome(){
        boolean p = palindrome.isPalindrome("ok");
        boolean q = palindrome.isPalindrome("noon");
        boolean m = palindrome.isPalindrome("ava");
        boolean n = palindrome.isPalindrome("horse");
        boolean w = palindrome.isPalindrome("a");
        boolean s = palindrome.isPalindrome("racecar");
        assertFalse(p);
        assertTrue(q);
        assertTrue(m);
        assertFalse(n);
        assertTrue(w);
        assertTrue(s);
    }

    @Test
    public void new_testisPalindrome(){
        boolean p = palindrome.isPalindrome("bda", offByOne);
        boolean q = palindrome.isPalindrome("flake", offByOne);
        boolean n = palindrome.isPalindrome("horse", offByOne);
        boolean s = palindrome.isPalindrome("racecar", offByOne);
        assertTrue(p);
        assertTrue(q);
        assertFalse(n);
        assertFalse(s);
    }
}     //Uncomment this class once you've created your Palindrome class.