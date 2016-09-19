package js.com.sort;

/**
 * 冒泡排序
 * Created by Administrator on 2015/4/21.
 */
public class BubbleSort {
    public static void bubbleSort(int[] array) {
        for(int i = 0; i < array.length - 1; i++) {
            for(int j = 0; j < array.length - i - 1; j++) {
                if(array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }



    public static void main(String[] args) {
        int[] array = {4, 7, 8, 9, 3, 2};
        bubbleSort(array);
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
