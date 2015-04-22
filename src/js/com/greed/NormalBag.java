package js.com.greed;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * 普通背包问题：给定n种物品和一个背包。物品i的重量是Wi，
 * 其价值为Vi，背包的容量为C。应如何选择装入
 * 背包的物品，使得装入背包中物品的总价值最大，物品可以拆分
 * 注意：普通背包，也叫不完全背包，可以用贪心法求解，而01背包问题只能动态规划求解
 * Created by Administrator on 2015/4/21.
 */
public class NormalBag {
    public static void main(String[] args) {
        //初始化队列//
        PriorityQueue<Goods> pq= initPq();
        //定义暂存结果数组//
        ArrayList<Goods> place=new ArrayList<Goods>();
        //初始化背包值//
        int c=10;//背包当前容量
        int v=0;//背包当前价值
        //开始放入物品//
        Goods t;//设定暂存记录变脸
        while(true){
            //设定借宿条件//
            if(c==0)break;
            if(pq.isEmpty())break;
            //取出替换元素//
            t=pq.poll();
            //开始比较//
            if(t.getW()<=c){
                v+=t.getV();
                t.setUse(t.getW());
                c-=t.getW();
            }
            else{
                v+=c*t.getVW();
                t.setUse(c);
                c=0;
            }
            place.add(t);
        }
        //输出结果//
        System.out.println(v);
        System.out.println(place);
    }

    //创建队列元素
    private static PriorityQueue<Goods> initPq() {
        PriorityQueue<Goods>pq=new PriorityQueue<Goods>();
        pq.offer(new Goods(2,6));
        pq.offer(new Goods(2,3));
        pq.offer(new Goods(6,5));
        pq.offer(new Goods(5,4));
        pq.offer(new Goods(4,6));
        return pq;
    }

}

class Goods implements Comparable<Goods>{
    private static int ids=1;
    private int id;
    private int weight;
    private int value;
    private int use;

    //初始化对象//
    public Goods(int w, int v){
        super();
        id=ids++;
        weight=w;
        value=v;
        use=0;
    }
    //获取输出值//
    public float getVW(){
        return this.value/this.weight;
    }
    public int getW(){
        return this.weight;
    }
    public int getV(){
        return this.value;
    }
    public int getUse(){
        return this.use;
    }
    //输出设置//
    public void setUse(int u){
        this.use=u;
    }
    //方法//
    public int compareTo(Goods o){
        if(this.value*o.weight>o.value*this.weight) return -1;//使用交叉相乘的方法避免除法，a/b?c/d=ad?bc
        if(this.value*o.weight<o.value*this.weight) return 1;
        return 0;
    }
    public String toString(){
        return "物品编号"+this.id+" 物品重量"+this.weight+" 物品价值"+this.value+" 物品使用情况"+this.use;
    }

}
