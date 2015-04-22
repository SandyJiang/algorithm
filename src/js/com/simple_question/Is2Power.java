package js.com.simple_question;

/**
 * 判断一个数是否是2的N次方
 * 位运算问题
 * Created by Administrator on 2015/4/22.
 */
public class Is2Power {
    public static boolean isPower(int n){
        if(n<1){
            return false;
        }
        int i = 1;
        while(i<=n){
            if(i == n){
                return true;
            }
            i <<= 1;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println((int)Math.pow(2,10));
        System.out.println(isPower((int)Math.pow(2,10)));
    }
}
