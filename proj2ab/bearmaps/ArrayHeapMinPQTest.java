package bearmaps;
import org.junit.Test;
import static org.junit.Assert.*;


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
        assertEquals((int)APQ.getSmallest(), 33);
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
        assertEquals((int)APQ.getSmallest(), 66);
    }

    @Test
    public void testTime(){
        ArrayHeapMinPQ<Integer> temp = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> temp2 = new NaiveMinPQ<>();

        long start = System.currentTimeMillis();
        for (int i = 100000; i > 0; i--) {
            temp.add(i, i);
        }
        for (int i = 1; i <= 100000; i++) {
            temp.removeSmallest();
        }
        long end = System.currentTimeMillis();
        System.out.println("Add and Remove: Total time elapsed: " + (end-start) / 1000.0 + " seconds.");

//        long start1 = System.currentTimeMillis();
//        for (int i = 100000; i > 0; i--) {
//            temp2.add(i, i);
//        }
//        for (int i = 1; i <= 100000; i++) {
//            temp2.removeSmallest();
//        }
//        long end1 = System.currentTimeMillis();
//        System.out.println("Add and Remove: Total time elapsed: " + (end1-start1) / 1000.0 + " seconds.");



//        for (int i = 0; i < 10000000; i++){
//            APQ.add(i,0.1*i);
//        }
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < 10000000; i++){
//            APQ.changePriority(11,0.1);
//        }
//        long end = System.currentTimeMillis();
//        System.out.println("APQ Total time elapsed: " + (end-start) / 1000.0 + "Seconds");
//
//        for (int i = 0; i < 10000000; i++){
//            NPQ.add(i,0.1*i);
//        }
//        long start1 = System.currentTimeMillis();
//        for (int i = 0; i < 10000000; i++){
//            NPQ.changePriority(11,0.1);
//        }
//        long end1 = System.currentTimeMillis();
//        System.out.println("NPQ Total time elapsed: " + (end1-start1) / 1000.0 + "Seconds");


    }

}
