package js.com.dynamic_planning;


/**
 * 动态规划题三，给定两个字符串S和T，
 * 求把S变成T所需的最少操作数。
 * 出处同题一
 * Created by Administrator on 2015/4/20.
 */
public class MinDistance {
    public static void main(String[] args) {
        String word1 = "ABCFGHY";
        String word2 = "DBFGGHF";
        System.out.println(getMinDistance(word1, word2));
        System.out.println(getMinDistance2(word1, word2));
    }

    public static int getMinDistance(String word1,String word2){
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=0; i<=m; i++){
            for(int j=0; j<=n; j++){
                if(i == 0){
                    dp[i][j] = j;
                }else if(j == 0){
                    dp[i][j] = i;
                }else{
                    dp[i][j] = Math.min(dp[i-1][j-1]+((word1.charAt(i-1) == word2.charAt(j-1)?0:1)),
                            Math.min(dp[i][j-1] +1,dp[i-1][j]+1));
                }
            }
        }
        return dp[m][n];
    }

    public static int getMinDistance2(String word1,String word2){
        int m = word1.length();
        int n = word2.length();
        int[] dp = new int[n+1];
        for(int i=0; i<=m; i++){
            int last = 0;
            for(int j=0; j<=n; j++){
                if(i == 0){
                    dp[j] = j;
                }else if(j == 0){
                    last = dp[j];
                    dp[j] = i;
                }else{
                    int temp = dp[j];
                    dp[j] = Math.min(last+((word1.charAt(i-1) == word2.charAt(j-1)?0:1)),
                            Math.min(dp[j-1] +1,dp[j]+1));
                    last = temp;
                }
            }
        }
        return dp[n];
    }


}
