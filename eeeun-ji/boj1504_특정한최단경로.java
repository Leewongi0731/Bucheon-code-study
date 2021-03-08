package Bucheon_solved_class4_gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//gold4_
//1번 정점에서 N번의 정점으로 이동할때에 최단경로 ---> 다익스트라 알고리즘
//중요한 조건은 !! 주어진 정점은 꼭 통과하고, 지나갔던 정점 또 통과 가능 !!
//무방향그래프
//v1->v2 or v2->v1
public class boj1504_특정한최단경로 {
	static final int INF = 200000000; //Integer.MAX_VALUE; <-오버플로우 발생
	static StringTokenizer st;
	
	static int N, E;
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
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //정점
		E = Integer.parseInt(st.nextToken()); //간선	// 인접리스트
		
		adj = new ArrayList<>();
		for (int i = 0; i <= N; i++)
			adj.add(new ArrayList<>());
		
		// 거리 배열
		dist = new int[N+1];
		visited = new boolean[N+1];
		Arrays.fill(dist, INF);

		
		// 간선 정보 입력
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()); // 출발지
			int v = Integer.parseInt(st.nextToken()); // 도착지
			int w = Integer.parseInt(st.nextToken()); // 비용
			
			//무방향이니까(쌍방향에 같은 가중치 설정)
			adj.get(u).add(new Graph(v, w));
			adj.get(v).add(new Graph(u, w));
		}

		//반드시 지나야하는 정점.
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken()); // 출발 정점
		int v2 = Integer.parseInt(st.nextToken()); // 도착 정점

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
		//초기화
		Arrays.fill(dist, INF); //필수!!!!!
		Arrays.fill(visited,false);
		
		// 우선순위 큐
		PriorityQueue<Graph> pq = new PriorityQueue<>();
		boolean[] check = new boolean[N+1];
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
