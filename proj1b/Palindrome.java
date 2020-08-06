public class Palindrome{

    public Deque<Character> wordToDeque(String word){
//        Deque<Character> p = new LinkedListDeque<Character>();
//        for (int i = 0; i < word.length(); i++){
//            p.addLast(word.charAt(i));
//        }
//        return p;
//    }
    Deque<Character> p = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++){
            p.addLast(word.charAt(i));
        }
        return p;
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        if (word.length() == 0 || word.length() == 1){
            return true;
        }
        int i = 0;
        int p = word.length() -1;
        while (i < p){
            if (!cc.equalChars(word.charAt(i), word.charAt(p))){
                return false;
            }
            i += 1;
            p -= 1;
        }
        return true;
    }

    private boolean test_equal(Deque q){
        if (q.size() > 1){
            if (q.removeLast() == q.removeFirst()){
                return test_equal(q);
            }
            return false;
        }
        return true;
    }

    public boolean isPalindrome(String word){
        if (word.length() == 0 || word.length() == 1){
            return true;
        }
        Deque<Character> p = wordToDeque(word);
        return test_equal(p);
    }


}
