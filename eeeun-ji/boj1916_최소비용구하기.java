package Bucheon_solved_class4_gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//boj1753번 풀면 간단하게 해결될 문제이다 !!
//gold5_

//시작점과 끝점 정해져있는경우 !!!!!!!!!
public class boj1916_최소비용구하기 {
// A도시에서 B도시까지 가는데에 드는 최소 비용
// --> 다익스트라 알고리즘
	final static int INF = Integer.MAX_VALUE;
	static StringTokenizer st;

	static int N, M, start, end;
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

		N = Integer.parseInt(br.readLine()); // 도시개수(정점)
		M = Integer.parseInt(br.readLine()); // 버스개수(간선)

		// 인접리스트
		adj = new ArrayList<>();
		for (int i = 0; i <= N; i++)
			adj.add(new ArrayList<>());
		
		// 거리 배열
		dist = new int[N+1];
		visited = new boolean[N+1];
		Arrays.fill(dist, INF);

		// 간선 정보 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()); // 출발지
			int v = Integer.parseInt(st.nextToken()); // 도착지
			int w = Integer.parseInt(st.nextToken()); // 비용
			adj.get(u).add(new Graph(v, w));
		}

		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken()); // 출발 정점
		end = Integer.parseInt(st.nextToken()); // 도착 정점
		dijkstra(start, end);
		
		dist[start] = 0; // 시작점 0으로 변경
		System.out.println(dist[end]);
	}
	
	public static int dijkstra(int start, int end) {
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