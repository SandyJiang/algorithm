package js.com.simple_question;

/**
 * 找出数组中唯一的重复元素
 * 方法很多，下面给出的还是异或法
 * 别的方法还有：位图法，计数排序法，取反法、转化为单链表是否存在环的法
 * 这个问题解法出处是《JAVA程序员面试笔试宝典》
 * Created by Administrator on 2015/4/22.
 */
public class FindOneDuplication {
    public static int findResult(int a[]){
        int n = a.length;
        int result = 0;
        int i;
        for(i=0; i<n; i++){
            result ^= a[i];
        }
        for(i=1;i<n;i++){
            result ^= i;
        }
        return result;
    }

    public static void main(String[] args) {
        int array[] = {1, 2, 3, 2, 4, 5};
        int num = findResult(array);
        System.out.println(num);
    }
}
