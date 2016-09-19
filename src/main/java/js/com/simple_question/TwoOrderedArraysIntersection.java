package js.com.simple_question;

import java.util.ArrayList;
import java.util.List;
/**
 * 两个有序数组，求交集
 * Created by Administrator on 2015/4/22.
 */
public class TwoOrderedArraysIntersection {
    public static List<Integer> getSameElements(int[] arr1, int[] arr2) {
        List<Integer> sameElements = new ArrayList<Integer>();
        int i = 0;
        int j = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] == arr2[j]) {
                sameElements.add(arr1[i]);
                i++;
                j++;
            } else if (arr1[i] > arr2[j]) {
                j++;
            } else {
                i++;
            }
        }
        return sameElements;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 8};
        int[] arr2 = {2, 3, 5, 9, 11};
        System.out.println(TwoOrderedArraysIntersection.getSameElements(arr1, arr2));
    }
}
