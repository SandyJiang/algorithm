package js.com.binary_search;

/**
 * 二分查找
 * Created by Administrator on 2015/4/21.
 */
public class BinarySearch {

    public static void main(String[] args) {

        int[] b = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

        int index = binarySearch(b , 3);

        System.out.println(index);
    }

    public static int binarySearch(int[] array, int value) {
        int low = 0;
        int high = array.length - 1;
        int middle;

        while(low <= high) {
            middle = (low + high) / 2;

            if(array[middle] == value) {
                return middle;
            }

            if(value < array[middle]) {
                high = middle - 1;
            }
            if(value > array[middle]) {
                low = middle + 1;
            }
        }

        return -1;

    }


}
