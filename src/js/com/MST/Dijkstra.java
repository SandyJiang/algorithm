package js.com.MST;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 贪心算法,解决有向图中最短路径问题
 * 面试中不常见
 * Created by Administrator on 2015/4/21.
 */
public class Dijkstra {
    private static final int minDis = 0;
    private static final int maxDis = Integer.MAX_VALUE;
    // 图的邻接矩阵
    int[][] matrix;
    // 起始点
    int startIndex;
    // 用来存放起始点到其它点当前的距离
    HashMap<Integer, Integer> distanceMap = new HashMap<Integer, Integer>();
    // 用来存放已经找到最短路径的点的集合
    Set<Integer> findedSet = new HashSet<Integer>();

    public Dijkstra(int[][] matrix, int start) {
        this.matrix = matrix;
        this.startIndex = start;
        //findedSet.add(startIndex);
    }

    public void find() {
        // 用start相邻的点初始化distanceMap
        for (int i = 0; i < matrix.length; i++) {
            //if (matrix[startIndex][i] != maxDis)
            distanceMap.put(i, matrix[startIndex][i]);
        }

        while (findedSet.size() != matrix.length) {
            int currentMinIndex = currentMinIndex();
            // 用此结点更新其它结点的距离
            for (int i = 0; i < matrix.length; i++) {
                if (!findedSet.contains(i) && matrix[currentMinIndex][i] != maxDis
                        && matrix[currentMinIndex][i] + distanceMap.get(currentMinIndex) < distanceMap.get(i))
                    distanceMap.put(i, matrix[currentMinIndex][i] + distanceMap.get(currentMinIndex));
            }

            // 放入findedset
            findedSet.add(currentMinIndex);
        }
    }

    //打印从起始点到所有点的最短距离
    public void printDistance(){
        Iterator<Entry<Integer, Integer>> it = distanceMap.entrySet().iterator();
        int min = Integer.MIN_VALUE;
        int minIndex = -1;
        while (it.hasNext()) {
            Entry<Integer, Integer> entry = it.next();
            System.out.println(startIndex+"---->"+entry.getKey()+":"+entry.getValue());
        }
    }

    // 返回当前最小距离的点(必须不包含在findedSet中)
    private int currentMinIndex() {
        Iterator<Entry<Integer, Integer>> it = distanceMap.entrySet().iterator();
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        while (it.hasNext()) {
            Entry<Integer, Integer> entry = it.next();
            if (!findedSet.contains(entry.getKey()) && entry.getValue() < min) {
                min = entry.getValue();
                minIndex = entry.getKey();
            }
        }
        return minIndex;
    }

    public static void main(String[] args) {
        int[][] inputMatrix = new int[][] { { minDis, 2, maxDis, 1, maxDis, maxDis, maxDis }, { maxDis, minDis, maxDis, 3, 10, maxDis, maxDis },
                { 4, maxDis, minDis, maxDis, maxDis, 5, maxDis }, { maxDis, maxDis, 2, minDis, 2, 8, 4 },
                { maxDis, maxDis, maxDis, maxDis, minDis, maxDis, 6 }, { maxDis, maxDis, maxDis, maxDis, maxDis, minDis, maxDis },
                { maxDis, maxDis, maxDis, maxDis, maxDis, 1, minDis } };
        Dijkstra path = new Dijkstra(inputMatrix, 0);
        path.find();
        path.printDistance();

    }
}
