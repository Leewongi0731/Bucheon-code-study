package Bucheon_solved_class4_gold;
import java.util.*;

import java.io.*;

//gold5_ 
//�������� ������ �������� �ƴ϶� �� ����ϴ� ���!!!!!!!!
public class boj1753_�ִܰ�� {
	/*
	 * ���� : ����׷����� �־�����, ���������� �ٸ� ��� ���������� �ִܰ�� -> ��������Ʈ��(�� �������� �ٸ� ��� �������� �ּ� .. )
	 * => ������� vs ��������Ʈ => ���� ���: �޸� �ʰ� �߻� (������ ������ �ִ� 2��������)
	 * 
	 * ��� ������ ����ġ�� 10������ �ڿ���
	 * 
	 * �Է� : (1)������ ���� ������ ���� ������ 1���� V���� ��ȣ�� �Ű��� 
	 * (2) ���� ������ ��ȣ (3) E���� �ٿ� ���� �� ������ ��Ÿ���� ������ ����(u,v,w)
	 * 
	 * *�켱���� ť ��� ! ����ġ�� ���� ���� ������ �̱� ���� ~~
	 */
	
	final static int INF = Integer.MAX_VALUE;
	
	static int V, E, start, end;
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
			
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken()); //����
		E = Integer.parseInt(st.nextToken()); //����
		start = Integer.parseInt(br.readLine()); // ���� ����
		
		// ��������Ʈ
		adj = new ArrayList<ArrayList<Graph>>();
		for (int i = 0; i <= V; i++)
			adj.add(new ArrayList<>());
		
		// �Ÿ� �迭
		dist = new int[V+1];
		visited = new boolean[V+1];
		Arrays.fill(dist, INF);

		// ���� ���� �Է�
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()); // �����
			int v = Integer.parseInt(st.nextToken()); // ������
			int w = Integer.parseInt(st.nextToken()); // ���
			adj.get(u).add(new Graph(v, w));
		}
		
		dijkstra(adj, dist, start);
		for(int i = 1; i <= V; i++) {
			if(dist[i] == INF) System.out.println("INF");
			else
				System.out.println(dist[i]);
		}
	}
	
	public static int dijkstra(ArrayList<ArrayList<Graph>> list, int[] dist, int start) {
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