package js.com.sort;

import java.util.Arrays;

/**
 * 堆排序，堆排序可以用来求K大，或者K小，效率要优于用快排求K大K小
 * 小根堆求K大，大根堆求K小
 * Created by Administrator on 2015/4/22.
 */
public class HeapSort {

    private int leftChild(int i) {
        return 2*i+1;
    }

    private int rightChild(int i) {
        return 2*i+2;
    }

    private void maxHeapify(int[] a, int i, int n) {
        int lc = leftChild(i);
        int rc = rightChild(i);
        int largest = i;
        if (lc < n && a[lc] > a[i]) {
            largest = lc;
        }
        if (rc < n && a[rc] > a[largest]) {
            largest = rc;
        }
        if (largest != i) {
            int tmp = a[i];
            a[i] = a[largest];
            a[largest] = tmp;
            maxHeapify(a, largest, n);
        }
    }

    private void buildMaxHeap(int[] a, int n) {
        for(int i = n/2; i >= 0; --i){
            maxHeapify(a, i, n);
            System.out.println(Arrays.toString(a) + ":" + n);
        }
    }

    private void sort(int[] a, int n) {
        buildMaxHeap(a, n);
        System.out.println(Arrays.toString(a)+":"+n);
        int heapSize = n;
        for(int i = n; i > 1; -- i) {
            int temp = a[i-1];
            a[i-1] = a[0];
            a[0] = temp;
            --heapSize;
            maxHeapify(a, 0, heapSize);
            System.out.println(Arrays.toString(a) + ":" + n);
        }
    }

    public static void main(String[] args) {
        int[] a =new int[]{1,2,5,3,4,6,9,7,8,10};
        System.out.println(Arrays.toString(a) + ":" + a.length);
        HeapSort heapSort = new HeapSort();
        heapSort.sort(a, a.length);
    }
}
