package js.com.dynamic_planning;

/**
 * 硬币找零问题：给定待换面额，换成给定零钱面额，使得硬币数目最小。
 * 硬币找零问题，对于部分给定零钱面额是可以使用贪心的，但不是任意面额，
 * 所以此类问题最好解决方法还是动态规划
 * Created by Administrator on 2015/4/21.
 */
public class CoinsChange {

    public static void main(String[] args) {
        int[] valueKinds = new int[]{25,21,10,5,1};
        int money=63;
        int a= makeChanges(money, valueKinds);
        System.out.println(a);
    }

    public static int makeChanges(int money, int[] valueKinds){
        int minCoins =money;
        for(int i=0;i< valueKinds.length;i++){
            if(money< valueKinds[i]){
                continue;
            }
            else{
                int temp= makeChanges(money - valueKinds[i], valueKinds)+1;
                if(minCoins >temp){
                    minCoins =temp;
                }
            }
        }
        return minCoins;

    }

}
