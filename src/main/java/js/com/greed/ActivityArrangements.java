package js.com.greed;

/**
 * 活动选择问题，贪心算法实现
 * 给定活动开始时间和结束时间，在给定的一间教室中如何安排能使教室利用最充分
 * 活动安排次数最多
 * Created by Administrator on 2015/4/21.
 */
public class ActivityArrangements {
    public static void main(String[] args) {
        //初始化//
        int s[]={1,3,0,5,3,5,6,8,8,2,12};//开始时间数组，已排序
        int f[]={4,5,6,7,8,9,10,11,12,13,14};//结束时间数组，已排序
        int a[]=new int[s.length];//定义标记选择过的活动数组
        int count=0;//活动数量计数器
        //开始选择//
        greedySelector(s, f, a);
        //输出//
        for(int i=0;i<s.length;i++){//输出活动序号
            if(a[i]==1){
                System.out.print("活动"+(i+1)+" ");
                count++;
            }
        }
        System.out.println();
        System.out.print("活动总数量为："+count);//输出总活动数量
    }

    private static void greedySelector(int[] s, int[] f, int[] a) {
        //贪心选择为，先结束的互动优先，这样剩余的时间达到最大，安排活动最多//
        int n=s.length-1;
        a[0]=1;//最先的那个最优
        int j=0;
        for(int i=1;i<=n;i++){
            if(s[i]>=f[j]){
                a[i]=1;
                j=i;
            }
            else{
                a[i]=0;
            }
        }
    }

}
