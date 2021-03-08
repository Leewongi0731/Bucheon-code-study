package Bucheon_solved_class4_gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//boj1753�� Ǯ�� �����ϰ� �ذ�� �����̴� !!
//gold5_

//�������� ���� �������ִ°�� !!!!!!!!!
public class boj1916_�ּҺ�뱸�ϱ� {
// A���ÿ��� B���ñ��� ���µ��� ��� �ּ� ���
// --> ���ͽ�Ʈ�� �˰���
	final static int INF = Integer.MAX_VALUE;
	static StringTokenizer st;

	static int N, M, start, end;
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

		N = Integer.parseInt(br.readLine()); // ���ð���(����)
		M = Integer.parseInt(br.readLine()); // ��������(����)

		// ��������Ʈ
		adj = new ArrayList<>();
		for (int i = 0; i <= N; i++)
			adj.add(new ArrayList<>());
		
		// �Ÿ� �迭
		dist = new int[N+1];
		visited = new boolean[N+1];
		Arrays.fill(dist, INF);

		// ���� ���� �Է�
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()); // �����
			int v = Integer.parseInt(st.nextToken()); // ������
			int w = Integer.parseInt(st.nextToken()); // ���
			adj.get(u).add(new Graph(v, w));
		}

		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken()); // ��� ����
		end = Integer.parseInt(st.nextToken()); // ���� ����
		dijkstra(start, end);
		
		dist[start] = 0; // ������ 0���� ����
		System.out.println(dist[end]);
	}
	
	public static int dijkstra(int start, int end) {
		//�ʱ�ȭ
		Arrays.fill(dist, INF); //�ʼ�!!!!!
		Arrays.fill(visited,false);
		
		// �켱���� ť
		PriorityQueue<Graph> pq = new PriorityQueue<>();
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