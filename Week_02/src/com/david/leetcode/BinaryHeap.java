package com.david.leetcode;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * 找最大O(1)
 * 插入 删除O(logn)
 */
public class BinaryHeap {
    private static final int d = 2;
    private int[] heap;
    private int heapSize;

    /**
     * This will initialize our heap with default size.
     */

    public BinaryHeap(int capacity) {
        heapSize = 0;
        heap = new int[capacity + 1];
        Arrays.fill(heap, -1);
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public boolean isFull() {
        return heapSize == heap.length;
    }

    private int parent(int i) {
        return (i - 1) / d;
    }

    private int kthChild(int i, int k) {
        return d * i + k;
    }

    /**
     * Inserts new element in to heap     * Complexity: O(log N)     * As worst case scenario, we need to traverse till the root
     */

    public void insert(int x) {
        if (isFull()) {
            throw new NoSuchElementException("Heap is full, No space to insert new element");
        }
        heap[heapSize] = x;
        heapSize++;
        heapifyUp(heapSize - 1);
    }

    /**
     * Deletes element at index x     * Complexity: O(log N)
     */

    public int delete(int x) {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty, No element to delete");
        }
        int maxElement = heap[x];
        heap[x] = heap[heapSize - 1];
        heapSize--;
        heapifyDown(x);
        return maxElement;
    }

    /**
     * Maintains the heap property while inserting an element.
     */

    private void heapifyUp(int i) {
        int insertValue = heap[i];//取出插入value
        while (i > 0 && insertValue > heap[parent(i)]) {//value>父亲value向上提升
            heap[i] = heap[parent(i)];//先把父亲值付给自己
            i = parent(i);//存储要替换位置的下标
        }
        heap[i] = insertValue;//最后一次性替换
    }

    /**
     * Maintains the heap property while deleting an element.
     */

    private void heapifyDown(int i) {
        int child;
        int temp = heap[i];//当前value
        while (kthChild(i, 1) < heapSize) {
            child = maxChild(i);//取出value最大孩子 提升
            if (temp >= heap[child]) {
                break;
            }
            heap[i] = heap[child];
            i = child;
        }
        heap[i] = temp;
    }

    private int maxChild(int i) {
        int leftChild = kthChild(i, 1);
        int rightChild = kthChild(i, 2);
        return heap[leftChild] > heap[rightChild] ? leftChild : rightChild;
    }

    /**
     * Prints all elements of the heap
     */

    public void printHeap() {
        System.out.print("nHeap = ");
        for (int i = 0; i < heapSize; i++)
            System.out.print(heap[i] + " ");
        System.out.println();
    }

    /**
     * This method returns the max element of the heap.     * complexity: O(1)
     */

    public int findMax() {
        if (isEmpty()) throw new NoSuchElementException("Heap is empty.");
        return heap[0];
    }

    public static void main(String[] args) {
        BinaryHeap maxHeap = new BinaryHeap(10);
        maxHeap.insert(10);
        maxHeap.insert(4);
        maxHeap.insert(9);
        maxHeap.insert(1);
        maxHeap.insert(7);
        maxHeap.insert(5);
        maxHeap.insert(3);
        maxHeap.printHeap();
        maxHeap.delete(5);
        maxHeap.printHeap();
        maxHeap.delete(2);
        maxHeap.printHeap();
    }
}
