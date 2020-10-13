//package bearmaps;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.NoSuchElementException;
//
//
//public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{
//    ArrayList<PriorityNode> items;
//    private HashMap<T , Integer> IndexMap;
//
//    public ArrayHeapMinPQ() {
//        items = new ArrayList<>();
//        IndexMap = new HashMap<>();
//        items.add(new PriorityNode(null, 0));
//    }
//
//    public int parent(int k){
//        if (k == 1){
//            return 1;
//        }
//        return k / 2;
//    }
//
//    private int leftChild(int k){
//        return k * 2;
//    }
//
//    private boolean greater(int i , int j){
//        return items.get(i).priority>items.get(j).priority;
//    }
//
//    public void swap(int a, int b){
//        PriorityNode temp = items.get(a);
//
//        items.set(a, items.get(b));
//        items.set(b, temp);
//
//        IndexMap.put(items.get(a).getItem(), a);
//        IndexMap.put(items.get(b).getItem(), b);
//    }
//
//    public void swim(int k){
//        if (k > 1 && greater(parent(k),k)){
//            swap(k, parent(k));
//            swim(parent(k));
//        }
//    }
//
//    public void sink(int k){
//        if (leftChild(k) <= size() ){
//            int j = leftChild(k);
//            if (j < size() && greater(j,j+1)) j++;
//            if (greater(j, k)) return;
//            swap(k, j);
//            sink(j);
//        }
//    }
//
//    @Override
//    public boolean contains(T item) {
//        if (size()==0){
//            return false;
//        }
//        return IndexMap.containsKey(item);
//    }
//
//    @Override
//    public void add(T item, double priority) {
//        if (contains(item)){
//            throw new IllegalArgumentException("item already exit");
//        }
//        items.add(new PriorityNode(item, priority));
//        IndexMap.put(item, size() -1);
//        swim(size() - 1);
//    }
//
//
//
//    @Override
//    public T getSmallest() {
//        if (size()== 0) {
//            throw new NoSuchElementException("PQ is empty");
//        }
//        return items.get(1).item;
//    }
//
//    @Override
//    public T removeSmallest() {
//        if (size() == 0) {
//            throw new NoSuchElementException("PQ is empty");
//        }
//        T p = items.get(1).getItem();
//        swap(1, size());
//        items.remove(size());
//        IndexMap.remove(p);
//        sink(1);
//        return p;
//    }
//
//    @Override
//    public int size() {
//        return items.size();
//    }
//
//    @Override
//    public void changePriority(T item, double priority) {
//
//        if (contains(item) == false) {
//            throw new NoSuchElementException("PQ does not contain " + item);
//        }
//        int index = IndexMap.get(item);
//        double oldPriority = items.get(index).priority;
//        items.set(index, new PriorityNode(item, priority));
//        if (oldPriority > priority){
//            sink(index);
//        }
//        swim(index);
//    }
//
//    private class PriorityNode {
//        private T item;
//        private double priority;
//
//        PriorityNode(T e, double p) {
//            this.item = e;
//            this.priority = p;
//        }
//
//        T getItem() {
//            return item;
//        }
//
//        double getPriority() {
//            return priority;
//        }
//
//        void setPriority(double priority) {
//            this.priority = priority;
//        }
//    }
//}



//package bearmaps;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.NoSuchElementException;
//
//public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
//
//    private ArrayList<PriorityNode> itemPQ;
//    private HashMap<T, Integer> itemMapIndex;
//
//    public ArrayHeapMinPQ() {
//        itemPQ =  new ArrayList<>();
//        itemMapIndex = new HashMap<>();
//    }
//
//    /* Adds an item with the given priority value. Throws an
//     * IllegalArgumentException if item is already present.
//     * You may assume that item is never null. */
//    @Override
//    public void add(T item, double priority) {
//        if (contains(item)) {
//            throw new IllegalArgumentException();
//        }
//        itemPQ.add(new PriorityNode(item, priority));
//        itemMapIndex.put(item, size() - 1);
//        climb(size() - 1);
//    }
//
//    /* Returns true if the PQ contains the given item. */
//    @Override
//    public boolean contains(T item) {
//        if (isEmpty()) {
//            return false;
//        }
//        return itemMapIndex.containsKey(item);
//    }
//
//    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
//    @Override
//    public T getSmallest() {
//        if (isEmpty()) {
//            throw new NoSuchElementException();
//        }
//        return itemPQ.get(0).getItem();
//    }
//
//    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
//    @Override
//    public T removeSmallest() {
//        if (isEmpty()) {
//            throw new NoSuchElementException();
//        }
//        T toRemove = itemPQ.get(0).getItem();
//        swap(0, size() - 1);
//        itemPQ.remove(size() - 1);
//        itemMapIndex.remove(toRemove);
//        sink(0);
//        return toRemove;
//    }
//
//    /* Returns the number of items in the PQ. */
//    @Override
//    public int size() {
//        return itemPQ.size();
//    }
//
//    /* Changes the priority of the given item. Throws NoSuchElementException if the item
//     * doesn't exist. */
//    @Override
//    public void changePriority(T item, double priority) {
//        if (isEmpty() || !contains(item)) {
//            throw new NoSuchElementException();
//        }
//        int index = itemMapIndex.get(item);
//        double oldPriority = itemPQ.get(index).getPriority();
//        itemPQ.get(index).setPriority(priority);
//        if (oldPriority < priority) {
//            sink(index);
//        } else {
//            climb(index);
//        }
//    }
//
//    private class PriorityNode {
//
//        private T item;
//        private double priority;
//
//        PriorityNode(T item, double priority) {
//            this.item = item;
//            this.priority = priority;
//        }
//
//        T getItem() {
//            return item;
//        }
//
//        double getPriority() {
//            return priority;
//        }
//
//        void setPriority(double priority) {
//            this.priority = priority;
//        }
//    }
//
//    private boolean isEmpty() {
//        return size() == 0;
//    }
//
//    // Return the index of parent of current node.
//    private int parent(int i) {
//        if (i == 0) {
//            return 0;
//        } else {
//            return (i - 1) / 2;
//        }
//    }
//
//    // Return the index of left child of current node.
//    private int leftChild(int i) {
//        return 2 * i + 1;
//    }
//
//    // Return the index of right child of current node.
//    private int rightChild(int i) {
//        return 2 * i + 2;
//    }
//
//    // Helper of add().
//    private void climb(int i) {
//        if (i > 0 && smaller(i, parent(i))) {
//            swap(i, parent(i));
//            climb(parent(i));
//        }
//    }
//
//    // Helper of removeSmallest().
//    private void sink(int i) {
//        int smallest = i;
//        if (leftChild(i) <= size() - 1 && smaller(leftChild(i), i)) {
//            smallest = leftChild(i);
//        }
//        if (rightChild(i) <= size() - 1 && smaller(rightChild(i), smallest)) {
//            smallest = rightChild(i);
//        }
//        if (smallest != i) {
//            swap(i, smallest);
//            sink(smallest);
//        }
//    }
//
//    // Swap two nodes.
//    private void swap(int i, int j) {
//        PriorityNode temp = itemPQ.get(i);
//        itemPQ.set(i, itemPQ.get(j));
//        itemPQ.set(j, temp);
//        itemMapIndex.put(itemPQ.get(i).getItem(), i);
//        itemMapIndex.put(itemPQ.get(j).getItem(), j);
//    }
//
//    // Return true if ith node has smaller priority than jth node.
//    private boolean smaller(int i, int j) {
//        return itemPQ.get(i).getPriority() < itemPQ.get(j).getPriority();
//    }
//
//}









package bearmaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{

    private ArrayList<PriorityNode> items;
    private HashMap<T, Integer> IndexMap;

    class PriorityNode{
        private T item;
        private double priority;

        public PriorityNode(T item, double priority){
            this.item = item;
            this.priority = priority;
        }

        private void setPriority(double priority) {
            this.priority = priority;
        }
    }

    public ArrayHeapMinPQ(){
        items = new ArrayList<>();
        IndexMap = new HashMap<>();
    }

    @Override
    public int size(){
        return items.size();
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    @Override
    public boolean contains(T item) {
        if (isEmpty()){
            return false;
        }
        return IndexMap.containsKey(item);
    }

    @Override
    public T getSmallest() {
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return items.get(0).item;
    }

    private int parent(int i){
        return (i -1)/2;
    }

    private int leftChild(int i){
        return 2 * i +1;
    }

    private int rightChild(int i){
        return 2 * i + 2;
    }

    private void swap(int i , int j){
        PriorityNode temp = items.get(i);
        items.set(i, items.get(j));
        items.set(j, temp);

        IndexMap.put(items.get(i).item, i);
        IndexMap.put(items.get(i).item, j);
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
        if (leftChild(i) < size() -1 && smaller(leftChild(i), i)){
            smallest = leftChild(i);
        }
        if (rightChild(i) < size() -1 && smaller(rightChild(i), leftChild(i))){
            smallest = rightChild(i);
        }

        if(smallest != i){
            swap(i, smallest);
            sink(smallest);
        }
    }

    @Override
    public void add(T item, double priority) {
        if (contains(item)){
            throw new IllegalArgumentException();
        }

        items.add(new PriorityNode(item, priority));
        swim(size() -1);
        IndexMap.put(item, size() -1);
    }

    @Override
    public T removeSmallest() {
        if (isEmpty()){
            throw new NoSuchElementException();
        }

        T p = getSmallest();
        swap(0, size() - 1);
        items.remove(size() -1);
        IndexMap.remove(p);
        sink(0);
        return p;
    }

    @Override
    public void changePriority(T item, double priority) {

        if (isEmpty() || !contains(item)){
            throw new NoSuchElementException();
        }

        int index = IndexMap.get(item);
        double OldPriority = items.get(index).priority;
        items.get(index).setPriority(priority);
        if (OldPriority < priority){
            sink(index);
        }
        swim(index);
    }

}




















