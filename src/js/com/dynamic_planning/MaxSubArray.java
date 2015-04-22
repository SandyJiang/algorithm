package js.com.dynamic_planning;

import java.util.Random;

/**
 * 动态规划题型二，最大子数组和
 * 出处同题一
 * Created by Administrator on 2015/4/20.
 */
public class MaxSubArray {
    public static void main(String[] args) {
        int[] A = new int[10];
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        //init 生成-10 到 10 的随机数
        for(int i=0; i<A.length; i++){
                A[i] = r.nextInt(21) - 10 ;
        }
        //print
        for(int i=0; i<A.length; i++){
            System.out.print(A[i] + " ");
        }
        System.out.println("");
        System.out.println(getMaxSubArray(A, A.length));
        System.out.println(getMaxSubArray2(A, A.length));
        System.out.println(getMaxSubArray3(A, A.length));
        System.out.println(getMaxSubArray4(A, A.length));
    }



    //时间复杂度O(nLogn)的分治法
    public static int getMaxSubArray(int A[],int n){
        if(n == 1){
            return A[0];
        }
        int mid = A.length >> 1;
        int B[] = new int[mid];
        int C[] = new int[n-mid];
        System.arraycopy(A,0,B,0,mid);
        System.arraycopy(A,mid,C,0,n-mid);
        int answer = Math.max(getMaxSubArray(B, mid),getMaxSubArray(C, n - mid));
        int now = A[mid -1];
        int may = now;
        for(int i = mid-2; i>=0; i--){
            may =  Math.max(now, now +=A[i]);
        }
        now = may;
        for(int i= mid; i<n;i++){
            may =  Math.max(now, now +=A[i]);
        }
        return Math.max(answer, may);
    }

    //公式递推
    public static int getMaxSubArray2(int A[],int n){
        int[] dp = new int[n];
        dp[0] = A[0];
        int answer = dp[0];
        for(int i=1; i<n; i++){
            dp[i] = Math.max(dp[i-1]+A[i], A[i]);
            answer = Math.max(answer, dp[i]);
        }
        return answer;
    }

    //公式递推,优化空间复杂度
    public static int getMaxSubArray3(int A[],int n){
        int endHere = A[0];
        int answer = A[0];
        for(int i=1; i<n; i++){
            endHere = Math.max(endHere+A[i], A[i]);
            answer = Math.max(answer, endHere);
        }
        return answer;
    }

    //最小和方法
    public static int getMaxSubArray4(int A[],int n){
        int sum = A[0];
        int minSum = Math.min(0, sum);
        int answer = A[0];
        for(int i=1; i<n; i++){
            sum += A[i];
            answer = Math.max(answer, sum-minSum);
            minSum = Math.min(minSum, sum);
        }
        return answer;
    }
}
