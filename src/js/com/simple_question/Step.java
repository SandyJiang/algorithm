package js.com.simple_question;

/**
 * 楼梯有n阶台阶，上楼可以一步上1阶，也可以一步上2阶，编一程
 * 序计算共有多少种不同的走法
 * 递归问题
 * Created by Administrator on 2015/4/22.
 */
public class Step {
    public static void main(String[] args) {
        int n = 10;  //台阶数
        Step stem = new Step();
        System.out.println(stem.func(n));
    }

    public  int func(int n) {
        System.out.println(n);
        if(n==1) {
            return 1;
        }
        else if(n==2) {
            return 2;
        }
        else {
            return func(n - 1)+ func(n - 2);
        }
    }
}
