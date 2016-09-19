package js.com.dynamic_planning;

import java.util.Random;

/**
 * 动态规划题型一：一个m维n列的二维数组，每个元素是一个非负数
 * 从左上角走到右下角，每次只能朝右或者下走，求总和最小
 * 思路来自
 * http://www.julyedu.com/video/play/id/19
 * Created by Administrator on 2015/4/20.
 */
public class MinPathSum {
    public static void main(String[] args) {
        int[][] grid = new int[5][5];
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        //init
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[i].length; j++){
                grid[i][j] = r.nextInt(10);
            }
        }


        //print grid[][]
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[i].length; j++){
                System.out.print(grid[i][j] + "\t");
                if(j == grid[i].length-1){
                    System.out.print("\n");
                }
            }
        }
        System.out.println(getMinPathSum(grid));
        System.out.println(getMinPathSum2(grid));
    }

    //递归求解版
    public static int getMinPathSum(int[][] grid){
        int[][] dp = new  int[grid.length][grid[0].length];
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[i].length; j++){
               if( i==0){
                   if(j == 0){
                       dp[i][j] = grid[i][j];
                   }else{
                       dp[i][j] = dp[i][j-1] + grid[i][j];
                   }
               }else if(j == 0){
                   dp[i][j] = dp[i-1][j] + grid[i][j];
               }else{
                   dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) +  grid[i][j];
               }
            }
        }
        return dp[grid.length-1][grid[0].length-1];
    }

    //在上面版本基础上，优化掉一个维度
    public static int getMinPathSum2(int[][] grid){
        int[] dp = new  int[grid.length];
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[i].length; j++){
                if( i==0){
                    if(j == 0){
                        dp[j] = grid[i][j];
                    }else{
                        dp[j] = dp[j-1] + grid[i][j];
                    }
                }else if(j == 0){
                    dp[j] = dp[j] + grid[i][j];
                }else{
                    dp[j] = Math.min(dp[j], dp[j-1]) +  grid[i][j];
                }
            }
        }
        return dp[grid.length-1];
    }


}
