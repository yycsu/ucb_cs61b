package bearmaps;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{

    private ArrayList<PriorityNode> items;
    private HashMap<T, Integer> IndexMap;

    public ArrayHeapMinPQ(){
        items = new ArrayList<PriorityNode>();
        IndexMap = new HashMap<>();
    }

    class PriorityNode {
        private T item;
        private double priority;

        public PriorityNode(T item, double priority){
            this.item = item;
            this.priority = priority;
        }

        private void setPriority(double priority){
            this.priority = priority;
        }
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    @Override
    public boolean contains(T item){
        if (isEmpty()){
            return false;
        }
        return IndexMap.containsKey(item);
    }

    @Override
    public T getSmallest(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return items.get(0).item;
    }

    @Override
    public int size(){
        return items.size();
    }

    private int parent(int i){
        return (i-1) / 2;
    }

    private int leftChild(int i){
        return 2 * i + 1;
    }

    private int rightChild(int i){
        return 2 * i + 2;
    }

    private void swap(int i, int j){
        PriorityNode temp = items.get(i);
        items.set(i, items.get(j));
        items.set(j, temp);

        IndexMap.put(items.get(i).item, i);
        IndexMap.put(items.get(j).item, j);
    }

    private boolean smaller(int i, int j){
        return items.get(i).priority < items.get(j).priority;
    }

    private void swim(int i){
        if (i > 0 && smaller(i, parent(i))){
            swap(i, parent(i));
            swim(parent(i));
        }
    }

    private void sink(int i){
        int smallest = i;

        if (leftChild(i) < size() - 1 && smaller(leftChild(i), i)){
            smallest = leftChild(i);
        }
        if (rightChild(i) < size() - 1 && smaller(rightChild(i), leftChild(i))){
            smallest = rightChild(i);
        }

        if (smallest != i){
            swap(i, smallest);
            sink(smallest);
        }
    }

    @Override
    public void add(T item, double priority){

        if (contains(item)){
            throw new IllegalArgumentException();
        }

        items.add(new PriorityNode(item, priority));
        IndexMap.put(item, size() -1);
        swim(size() - 1);
    }

    @Override
    public T removeSmallest(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }

        T smallest = items.get(0).item;
        swap(0, size() - 1);
        items.remove(size() - 1);
        IndexMap.remove(smallest);
        sink(0);
        return smallest;
    }

    @Override
    public void changePriority(T item, double priority){
        if (isEmpty() || !contains(item)){
            throw new NoSuchElementException();
        }

        int index = IndexMap.get(item);
        double oldPriority = items.get(index).priority;
        items.get(index).setPriority(priority);

        if (oldPriority < priority){
            sink(index);
        }
        swim(index);
    }

}












