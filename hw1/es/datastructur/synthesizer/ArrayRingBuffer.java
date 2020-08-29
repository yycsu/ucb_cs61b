package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[])new Object[capacity];
    }

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if (isFull()){
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last = (last + 1) % capacity();
        fillCount += 1;
        return;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if (isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        T currentItem = rb[first];
        rb[first] = null;
        first = (first + 1) % capacity();
        fillCount -= 1;
        return currentItem;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        if (isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }

        return rb[first];
    }

    @Override
    public Iterator<T> iterator() {
        return (Iterator<T>) new arrayIterator();
    }

    private class arrayIterator{
        private int wizPose;
        public arrayIterator(){
            int wizPose = 0;
        }

        public boolean hasNext(){
            return wizPose < fillCount();
        }

        public T next(){
            T current = rb[wizPose];
            wizPose += 1;
            return current;
        }
    }

    @Override
    public boolean equals(Object other){
        if (other == null){
            return false;
        }
        if (other == this){
            return true;
        }
        ArrayRingBuffer<T> o = (ArrayRingBuffer<T>) other;

        Iterator<T> fir = o.iterator();
        Iterator<T> sec = this.iterator();
        while (fir.hasNext() && sec.hasNext()){
            if (fir.next() != sec.next()){
                return false;
            }
        }
        return true;
    }
    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
}
    // TODO: Remove all comments that say TODO when you're done.
