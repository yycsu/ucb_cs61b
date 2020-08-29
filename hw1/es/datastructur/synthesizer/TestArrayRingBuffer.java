package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer <Double>arb = new ArrayRingBuffer<Double>(10);
        arb.enqueue(20.1);
        arb.enqueue(40.3);
        arb.enqueue(30.5);
        arb.enqueue(78.0);
        assertEquals(4, arb.fillCount(),1);
        assertEquals(10,arb.capacity(),1);
        double expected = arb.peek();
        assertEquals(expected, 20.1,0.01);
        arb.dequeue();
        expected = 40.3;
        assertEquals(expected,arb.peek(),0.01);
        assertEquals(false, arb.isEmpty());
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        assertEquals(true, arb.isEmpty());
    }
}
