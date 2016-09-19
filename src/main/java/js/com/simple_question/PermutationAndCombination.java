package js.com.simple_question;

import java.util.Arrays;

/**
 * 求n个给定自然数的排列
 * 求n个给定自然数的组合
 * Created by Administrator on 2015/4/22.
 */
public class PermutationAndCombination {
    public static void main(String[] args) {
        int[] a = new int[]{1,2,3,4,5};
        perm(a,0);
        System.out.println("==================================");
        printCombination(a, 5, 3);
    }

    public static void swap(int arr[], int a, int b)
    {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    //排列，k为起始位置，N为数组长度
    public static void perm(int a[], int k) {
        if(k == a.length-1){
            System.out.println(Arrays.toString(a));
        }else{
            for(int i=k; i<a.length; i++){
                swap(a, i, k);
                perm(a, k+1);
                swap(a, i, k);
            }
        }
    }



   //组合，出处如下
    //http://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/
    public static void printCombination(int arr[], int n, int r) {
        int[] data = new int[r];
        combinationUtil(arr, n, r, 0, data, 0);
    }


    public static void combinationUtil(int arr[], int n, int r, int index, int data[], int i) {
        if (index == r) {
            for (int j=0; j<r; j++){
                System.out.print(data[j]+" ");
            }
            System.out.println("");
            return;
        }

        if (i >= n){
            return;
        }

        data[index] = arr[i];
        combinationUtil(arr, n, r, index+1, data, i+1);
        combinationUtil(arr, n, r, index, data, i+1);
    }

}
