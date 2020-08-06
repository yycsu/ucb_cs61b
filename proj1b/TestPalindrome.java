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
        assertEquals(p, false);
        assertEquals(q, true);
        assertEquals(m, true);
        assertEquals(n, false);
        assertEquals(w, true);
        assertEquals(s, true);
    }

    @Test
    public void new_testisPalindrome(){
        boolean p = palindrome.isPalindrome("bda", offByOne);
        boolean q = palindrome.isPalindrome("flake", offByOne);
        boolean n = palindrome.isPalindrome("horse", offByOne);
        boolean s = palindrome.isPalindrome("racecar", offByOne);
        assertEquals(p, true);
        assertEquals(q, true);
        assertEquals(n, false);
        assertEquals(s, false);
    }
}     //Uncomment this class once you've created your Palindrome class.