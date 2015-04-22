package js.com.MST;

/**
 * 加权连通图的最小生成树的算法 Created by Administrator on 2015/4/21.
 * 面试中不常见
 */
public class Prim {
	/**
	 * 初始化图
	 */
	public static PrimeEdge[] e = { new PrimeEdge('a', 'b', 2),
			new PrimeEdge('b', 'c', 1), new PrimeEdge('c', 'd', 2),
			new PrimeEdge('d', 'e', 9), new PrimeEdge('e', 'f', 4),
			new PrimeEdge('f', 'g', 1), new PrimeEdge('g', 'h', 9),
			new PrimeEdge('h', 'a', 1), new PrimeEdge('a', 'i', 8),
			new PrimeEdge('b', 'i', 6), new PrimeEdge('c', 'j', 3),
			new PrimeEdge('d', 'k', 7), new PrimeEdge('e', 'k', 2),
			new PrimeEdge('f', 'k', 1), new PrimeEdge('g', 'j', 4),
			new PrimeEdge('h', 'i', 7), new PrimeEdge('i', 'j', 1),
			new PrimeEdge('j', 'k', 6) };

	/**
	 * w函数
	 * 
	 * @param x
	 *            起点序号
	 * @param y
	 *            终点序号
	 * @return 返回起点序号为x，终点序号为y的边的权植。如果没有这条边就返回无穷大
	 */
	public static int w(int x, int y) {
		char from = (char) (x + 97);
		char to = (char) (y + 97);
		for (int i = 0; i < 18; i++) {
			if (e[i].vexa == from && e[i].vexb == to) {
				return e[i].weight;
			}
			if (e[i].vexa == to && e[i].vexb == from) {
				return e[i].weight;
			}
		}
		return 1000; // 用1000代表无穷大，如果图中没有这条边就返回无穷大
	}

	public static void main(String[] args) {
		
		// 如果vex_mst[i]=0，表示对应结点没有加入到mst
		// 如果vex_mst[i]=1，表示对应结点已经加入到mst
		int[] vex_mst = new int[11];
		for (int i = 0; i < 11; i++){
			// 初始化
			vex_mst[i] = 0;
		}
			
		vex_mst[0] = 1; // 设置初始结点为a // 将10条边加入到最小生成树
		for (int i = 0; i < 10; i++) {
			// 加入一条边。
			// 这条边的两个结点一个在mst中，而另一个不在mst中而且具有最小权植
			int add_vex = 0; // 选中的结点
			int min_weight = 1000; // 最小权植,初始值为1000
			PrimeEdge adde = new PrimeEdge(' ', ' ', 0);
			for (int j = 0; j < 11; j++)
				if (vex_mst[j] == 1) { // j是mst中的结点
					for (int k = 0; k < 11; k++) {
						if (vex_mst[k] == 0 && w(j, k) < min_weight) {
							add_vex = k;
							min_weight = w(j, k);
							adde.vexa = (char) (j + 97);
							adde.vexb = (char) (k + 97);
							adde.weight = min_weight;
						}
					}
				}
			vex_mst[add_vex] = 1; // 将选择的结点加入mst
			char avex = (char) (add_vex + 97); // 将选择的边加入mst
			System.out.println("addvex:" + avex);
			// 输出加入mst的顶点，边，以及边的权植
			System.out.println("addedge:" + adde.vexa + "-" + adde.vexb + " w:"
					+ adde.weight);
		}
	}

}

class PrimeEdge {
	/**
	 * 边的起点
	 */
	char vexa;
	/**
	 * 边的终点
	 */
	char vexb;
	/**
	 * 边的权植
	 */
	int weight;

	PrimeEdge(char vexa, char vexb, int weight) {
		this.vexa = vexa;
		this.vexb = vexb;
		this.weight = weight;
	}
}