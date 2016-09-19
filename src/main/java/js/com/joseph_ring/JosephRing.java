package js.com.joseph_ring;

/**
 * 约瑟夫环的一个巧妙解法，一个循环搞定。
 * 原题：100个人的圆环，从1开始报数，报到14的这个人就要退出。然后其他人继续开始，原先15号报1,以此类推。问最后留下谁。
 * f(i) = (f(i - 1) + 14) % i ;
 * f(1)=0;
 * 出自《具体数学》
 */
public class JosephRing {
    public static void main(String[] args) {
        int humanSize = 100;  //人数
        int p_i=0;           //p(1)=0
        int quitNum =14;     //第14个退出
        for (int i = 1;i<=humanSize ;i++ ) {
            p_i=(p_i+quitNum)%i;
        }
        System.out.println(p_i+1);
    }

}