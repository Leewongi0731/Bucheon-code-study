package Bucheon_solved_class4_gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//gold4_
//1�� �������� N���� �������� �̵��Ҷ��� �ִܰ�� ---> ���ͽ�Ʈ�� �˰���
//�߿��� ������ !! �־��� ������ �� ����ϰ�, �������� ���� �� ��� ���� !!
//������׷���
//v1->v2 or v2->v1
public class boj1504_Ư�����ִܰ�� {
	static final int INF = 200000000; //Integer.MAX_VALUE; <-�����÷ο� �߻�
	static StringTokenizer st;
	
	static int N, E;
	static ArrayList<ArrayList<Graph>> adj; //��������Ʈ
	static int[] dist; //���������� �� �������� ���� �ִܰŸ�
	static boolean[] visited;
	
	static class Graph implements Comparable<Graph> {
		int v, w;

		public Graph(int v, int w) { // ������
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Graph o) { // <, = , > ������ true�ϰ�� -1, 0, 1�� ����� ���Դ�.
			return w - o.w;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //����
		E = Integer.parseInt(st.nextToken()); //����	// ��������Ʈ
		
		adj = new ArrayList<>();
		for (int i = 0; i <= N; i++)
			adj.add(new ArrayList<>());
		
		// �Ÿ� �迭
		dist = new int[N+1];
		visited = new boolean[N+1];
		Arrays.fill(dist, INF);

		
		// ���� ���� �Է�
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()); // �����
			int v = Integer.parseInt(st.nextToken()); // ������
			int w = Integer.parseInt(st.nextToken()); // ���
			
			//�������̴ϱ�(�ֹ��⿡ ���� ����ġ ����)
			adj.get(u).add(new Graph(v, w));
			adj.get(v).add(new Graph(u, w));
		}

		//�ݵ�� �������ϴ� ����.
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken()); // ��� ����
		int v2 = Integer.parseInt(st.nextToken()); // ���� ����

		int rslt1 = 0;
		rslt1 += dijkstra(1, v1);
		rslt1 += dijkstra(v1, v2);
		rslt1 += dijkstra(v2, N);
		
		int rslt2 = 0;
		rslt2 += dijkstra(1, v2);
		rslt2 += dijkstra(v2, v1);
		rslt2 += dijkstra(v1, N);
		
		if(rslt1 >= INF && rslt2 >= INF) 
			System.out.println(-1);
		else
			System.out.println(Math.min(rslt1, rslt2));
	}
	
	public static int dijkstra(int start, int end) {
		//�ʱ�ȭ
		Arrays.fill(dist, INF); //�ʼ�!!!!!
		Arrays.fill(visited,false);
		
		// �켱���� ť
		PriorityQueue<Graph> pq = new PriorityQueue<>();
		boolean[] check = new boolean[N+1];
		pq.offer(new Graph(start, 0));
		dist[start] = 0;
		
		//��������Ʈ��
		while (!pq.isEmpty()) {
			Graph current = pq.poll();
			int cur = current.v;
			
			if (visited[cur]) //�ߺ�����
				continue;
			else visited[cur] = true;
			
			for (Graph next : adj.get(cur)) { // ��� ���� �˻�
				if (!visited[next.v] && dist[next.v] > current.w + next.w) {
					dist[next.v] = current.w + next.w;
					pq.add(new Graph(next.v, dist[next.v]));
				}
			}
		}
		return dist[end];
	}
}
