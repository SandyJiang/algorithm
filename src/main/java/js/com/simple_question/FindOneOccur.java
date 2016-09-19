package js.com.simple_question;

/**
 * 找出数组中只出现一次的数字，
 * 排序方法不说了，这里用的方法是异或运算
 * 异或自己是0，利用这一个特性，可以消除重复
 * Created by Administrator on 2015/4/22.
 */
public class FindOneOccur {
    public static int findResult(int a[]){
        int n = a.length;
        int result = a[0];
        int i;
        for(i=1; i<n; i++){
            result ^= a[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int array[] = {1, 2, 3, 2, 4, 3, 5, 4, 1};
        int num = findResult(array);
        System.out.println(num);
    }
}
