package bearmaps;
import org.junit.Test;
import static org.junit.Assert.*;
import edu.princeton.cs.algs4.Stopwatch;

public class ArrayHeapMinPQTest {

    ArrayHeapMinPQ<Integer> APQ = new ArrayHeapMinPQ<>();
    NaiveMinPQ<Integer> NPQ = new NaiveMinPQ<>();
    @Test
    public void testContains(){

        APQ.add(11, 0.5);
        APQ.add(22, 1.0);
        APQ.add(33, 1.5);
        APQ.add(44,0.3);
        APQ.add(55,4.3);
        APQ.add(66,5.3);
        APQ.removeSmallest();
        APQ.removeSmallest();
        APQ.changePriority(66,0.1);
        APQ.removeSmallest();
        assertTrue(APQ.contains(22));
        assertFalse(APQ.contains(11));

    }

    @Test
    public void testRemoveSmallest(){

        APQ.add(11, 0.5);
        APQ.add(22, 1.0);
        APQ.add(33, 1.5);
        APQ.add(44,0.3);
        APQ.add(55,4.3);
        APQ.add(66,5.3);
        APQ.removeSmallest();

        assertEquals((int)APQ.getSmallest(), 11);
    }

    @Test
    public void testSize(){

        APQ.add(11, 0.5);
        APQ.add(22, 1.0);
        APQ.add(33, 1.5);
        APQ.add(44,0.3);
        APQ.add(55,4.3);
        APQ.add(66,5.3);
        APQ.removeSmallest();
        APQ.removeSmallest();
        APQ.removeSmallest();

        assertEquals(APQ.size(), 3);
    }

    @Test
    public void testGetSmallest(){
        APQ.add(11, 0.5);
        APQ.add(22, 1.0);
        APQ.add(33, 1.5);
        APQ.add(44,0.3);
        APQ.changePriority(33,0.1);
        assertTrue(APQ.getSmallest() == 33);
    }

    @Test
    public void testAdd(){
        APQ.add(11, 0.5);
        APQ.add(22, 1.0);
        APQ.add(33, 1.5);
        APQ.add(44,0.3);
        APQ.add(55,4.3);
        APQ.add(66,0.03);
        assertEquals((int)APQ.getSmallest(), 66);
        assertTrue(APQ.size() == 6);
    }

    @Test
    public void testChangePriority(){
        APQ.add(11, 0.5);
        APQ.add(22, 1.0);
        APQ.add(33, 1.5);
        APQ.add(44,0.3);
        APQ.add(55,4.3);
        APQ.add(66,5.3);
        APQ.removeSmallest();
        APQ.removeSmallest();
        APQ.changePriority(66,0.1);
        assertTrue(APQ.getSmallest() == 66);
    }

    @Test
    public void testTime(){
        Stopwatch sw = new Stopwatch();
        for (int i = 0; i < 100000; i++){
            APQ.add(i, i);
        }
        for (int j = 100000; j > 0; j--){
            APQ.removeSmallest();
        }
        System.out.println("APQ Add and Remove: Total time elapsed: " + sw.elapsedTime() + " Seconds");

        sw = new Stopwatch();
        for (int i = 0; i < 100000; i++){
            NPQ.add(i, i);
        }
        for (int j = 100000; j > 0; j--){
            NPQ.removeSmallest();
        }
        System.out.println("NPQ Add and Remove: Total time elapsed: " + sw.elapsedTime() + " Seconds");


        for (int i = 0; i < 100000; i++){
            APQ.add(i, i);
            NPQ.add(i, i);
        }

        sw = new Stopwatch();
        for (int i = 0; i < 100000; i++){
            APQ.changePriority(i, 100000 - i);
        }
        System.out.println("APQ changePriority: Total time elapsed: " + sw.elapsedTime() + " Seconds");

        sw = new Stopwatch();
        for (int i = 0; i < 100000; i++){
            NPQ.changePriority(i, 100000 - i);
        }
        System.out.println("NPQ changePriority: Total time elapsed: " + sw.elapsedTime() + " Seconds");

        sw = new Stopwatch();
        for (int i = 0; i < 100000; i++){
            APQ.contains(i);
        }
        System.out.println("APQ contains: Total time elapsed: " + sw.elapsedTime() + " Seconds");

        sw = new Stopwatch();
        for (int i = 0; i < 100000; i++){
            NPQ.contains(i);
        }
        System.out.println("NPQ contains: Total time elapsed: " + sw.elapsedTime() + " Seconds");
    }
}
