package js.com.sort;

import java.util.Arrays;

/**
 * 并归排序
 * Created by Administrator on 2015/4/22.
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] a = new int[]{2, 3, 4, 7, 6, 10, 9, 8, 5, 1};
        int[] b = new int[a.length];

        System.out.println(Arrays.toString(a));

        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(a, b, 0, 9);

        System.out.println(Arrays.toString(a));

    }

    private void sort(int[] a, int[] b, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = (low + high)/2;
        sort(a, b, low, mid);
        sort(a, b, mid + 1, high);
        merge(a, b, low, mid, high);
    }

    private void merge(int[] a, int[] b, int low, int mid, int high) {
        int i = low;
        int j = mid + 1;
        int k = low;
        while (i <= mid && j <=high) {
            if (a[i] < a[j]) {
                b[k++] = a[i++];
            }
            else {
                b[k++] = a[j++];
            }
        }
        while (i <= mid) {
            b[k++] = a[i++];
        }
        while (j <= high) {
            b[k++] = a[j++];
        }
        for(i = low; i <= high; ++ i) {
            a[i] = b[i];
        }
    }
}
