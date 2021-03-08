package Bucheon_solved_class4_gold;
import java.util.*;

import java.io.*;

//gold5_ 
//시작점과 끝점이 정해진게 아니라 다 출력하는 경우!!!!!!!!
public class boj1753_최단경로 {
	/*
	 * 문제 : 방향그래프가 주어지면, 시작점에서 다른 모든 정점으로의 최단경로 -> 다이젝스트라(한 정점에서 다른 모든 정점까지 최소 .. )
	 * => 인접행렬 vs 인접리스트 => 인접 행렬: 메모리 초과 발생 (점점의 갯수가 최대 2만개여서)
	 * 
	 * 모든 간선의 가중치는 10이하의 자연수
	 * 
	 * 입력 : (1)정점의 개수 간선의 개수 정점은 1부터 V까지 번호가 매겨짐 
	 * (2) 시작 정점의 번호 (3) E개의 줄에 걸쳐 각 간선을 나타내는 세개의 정수(u,v,w)
	 * 
	 * *우선순위 큐 사용 ! 가중치가 가장 작은 간선을 뽑기 위해 ~~
	 */
	
	final static int INF = Integer.MAX_VALUE;
	
	static int V, E, start, end;
	static ArrayList<ArrayList<Graph>> adj; //인접리스트
	static int[] dist; //시작점에서 각 정점으로 가는 최단거리
	static boolean[] visited;
	
	static class Graph implements Comparable<Graph> {
		int v, w;

		public Graph(int v, int w) { // 생성자
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Graph o) { // <, = , > 순으로 true일경우 -1, 0, 1의 결과가 나왔다.
			return w - o.w;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken()); //정점
		E = Integer.parseInt(st.nextToken()); //간선
		start = Integer.parseInt(br.readLine()); // 시작 정점
		
		// 인접리스트
		adj = new ArrayList<ArrayList<Graph>>();
		for (int i = 0; i <= V; i++)
			adj.add(new ArrayList<>());
		
		// 거리 배열
		dist = new int[V+1];
		visited = new boolean[V+1];
		Arrays.fill(dist, INF);

		// 간선 정보 입력
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()); // 출발지
			int v = Integer.parseInt(st.nextToken()); // 도착지
			int w = Integer.parseInt(st.nextToken()); // 비용
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
		//초기화
		Arrays.fill(dist, INF); //필수!!!!!
		Arrays.fill(visited,false);
		
		// 우선순위 큐
		PriorityQueue<Graph> pq = new PriorityQueue<>();
		pq.offer(new Graph(start, 0));
		dist[start] = 0;
		
		//다이젝스트라
		while (!pq.isEmpty()) {
			Graph current = pq.poll();
			int cur = current.v;
			
			if (visited[cur]) //중복제거
				continue;
			else visited[cur] = true;
			
			for (Graph next : adj.get(cur)) { // 모든 간선 검사
				if (!visited[next.v] && dist[next.v] > current.w + next.w) {
					dist[next.v] = current.w + next.w;
					pq.add(new Graph(next.v, dist[next.v]));
				}
			}
		}
		return dist[end];
	}
}