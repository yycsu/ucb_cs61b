package bearmaps;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

import org.junit.Test;
import java.util.Collections;
import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{
    private ArrayList<PriorityNode> items;
    private HashMap<T , Integer> IndexMap;
    private int size;

    public ArrayHeapMinPQ() {
        items = new ArrayList<>();
        IndexMap = new HashMap<>();
        items.set(0,null);
        size = 0;
    }

    public int parent(int k){
        if (k == 1){
            return 1;
        }
        return k / 2;
    }

    private int leftchild(int k){
        return k * 2;
    }

    private boolean greater(int i , int j){
        return items.get(i).priority>items.get(j).priority;
    }

    public void swap(int a, int b){
        PriorityNode p = items.get(a);
        PriorityNode q = items.get(b);
        items.set(a, q);
        items.set(b, p);

        IndexMap.put(q.item, a);
        IndexMap.put(p.item, b);
    }

    public void swim(int k){
        if (k > 1 && greater(parent(k),k)){
            swap(k, parent(k));
            swim(parent(k));
        }
    }

    public void sink(int k){
        if (leftchild(k) <= size ){
            int j = leftchild(k);
            if (j < size && greater(j,j+1)) j++;
            if (greater(k, j)) return;
            swap(k, j);
            sink(j);
        }
    }

    @Override
    public void add(T item, double priority) {
        items.set(++size, new PriorityNode(item, priority));
        IndexMap.put(item, size -1);
        swim(size);

    }

    @Override
    public boolean contains(T item) {
        if (size==0){
            return false;
        }
        return IndexMap.containsKey(item);
    }

    @Override
    public T getSmallest() {
        if (size== 0) {
            throw new NoSuchElementException("PQ is empty");
        }
        return items.get(1).item;
    }

    @Override
    public T removeSmallest() {
        if (size == 0) {
            throw new NoSuchElementException("PQ is empty");
        }
        T p = items.get(1).item;
        IndexMap.remove(p);
        swap(1, size--);
        sink(1);
        items.set((size+1),null);
        return p;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void changePriority(T item, double priority) {

        if (contains(item) == false) {
            throw new NoSuchElementException("PQ does not contain " + item);
        }
        int index = IndexMap.get(item);
        double oldPriority = items.get(index).priority;
        items.set(index, new PriorityNode(item, priority));
        if (oldPriority > priority){
            sink(index);
        }
        swim(index);
    }

//    private class PriorityNode implements Comparable<PriorityNode> {
    private class PriorityNode {
        private T item;
        private double priority;

        PriorityNode(T e, double p) {
            this.item = e;
            this.priority = p;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }

//        @Override
//        public int compareTo(PriorityNode other) {
//            if (other == null) {
//                return -1;
//            }
//            return Double.compare(this.getPriority(), other.getPriority());
//        }
//
//        @Override
//        @SuppressWarnings("unchecked")
//        public boolean equals(Object o) {
//            if (o == null || o.getClass() != this.getClass()) {
//                return false;
//            } else {
//                return ((PriorityNode) o).getItem().equals(getItem());
//            }
//        }
//
//        @Override
//        public int hashCode() {
//            return item.hashCode();
//        }
    }
}
