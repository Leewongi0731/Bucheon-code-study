package Bucheon_solved_class4_gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import Bucheon_solved_class4_gold.boj1504_Ư�����ִܰ��.Graph;

/*gold3_�����
Ʈ���� ������ ������ �̾��� ������ ������ �޴´�
�����̶�, Ʈ������ ������ ���� ������ �Ÿ��� ���� ����� ���Ѵ�.
������׷���
---> �׷� �ִܰŸ��� �ƴ϶�, ����Ÿ�!!
 
*dfs�� �����ذ�
*
*/
//
//gold3_
public class boj1167_Ʈ�������� {
	static ArrayList<Graph>[] list; 
	static boolean[] visited;
	static int max = 0, start = 0;

	static public class Graph {
		int v, w;

		public Graph(int v, int w) { // ������
			this.v = v;
			this.w = w;
		}
	}

	static public void dfs(int v, int sum) {
		visited[v] = true;
		if(sum > max) {
			max = sum;
			start = v;
		}
		
		for(Graph g : list[v]) {
			int m = g.v;
			int dist = g.w;
			
			if(!visited[m]) {
				dfs(m, sum + dist);
				visited[m] = false;
			}
		}
		

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[V + 1];
		for (int i = 0; i < V; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			list[u] = new ArrayList<Graph>();

			while (true) {
				int v = Integer.parseInt(st.nextToken());
				if (v == -1)
					break;
				list[u].add(new Graph(v, Integer.parseInt(st.nextToken())));
			}
		}
		
		visited = new boolean[V + 1];
		dfs(1,0);
		visited = new boolean[V + 1];
		dfs(start, 0);
		System.out.println(max);
	}

}
