package Bucheon_solved_class4_gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//13:05~13:23
//gold3_
/*N개의 숫자로 구분된 마을에 한명씩 살고 있음 
* N명의 학생 X번 마을에 모여서 파티 벌이기
* M개의 단방향의 도로들 i번째 길을 지날때 Ti의 시간 소비
* 
* 
* 오고가는 길이 다를수도 있다.
* N명의 학생들중 오고가는데 가장 많은 시간을 소비하는 학생*/
public class boj1238_파티 {
	static final int INF = 987654321; // Integer.MAX_VALUE; <-오버플로우 발생
	static StringTokenizer st;

	static int N, M, X; // N명의 학생들, M개의 도로
	static ArrayList<ArrayList<Graph>> adj; // 인접리스트
	static int[] dist; // 시작점에서 각 정점으로 가는 최단거리
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
		N = Integer.parseInt(st.nextToken()); // N명의 학생(동시에 마을번호), 정점
		M = Integer.parseInt(st.nextToken()); // M명의 도로, 간선
		X = Integer.parseInt(st.nextToken()); // X번째의 마을에서 파티

		// 마을이 1~ N번까지 있다. 이중에서 N==X인경우는 움직이지 않는다!

		adj = new ArrayList<>();
		for (int i = 0; i <= N; i++)
			adj.add(new ArrayList<>());

		// 거리 배열
		dist = new int[N + 1];
		visited = new boolean[N + 1];
		Arrays.fill(dist, INF);

		// 간선 정보 입력
		for (int i = 0; i < M; i++) { //도로의 개수만큼
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()); // 출발지
			int v = Integer.parseInt(st.nextToken()); // 도착지
			int w = Integer.parseInt(st.nextToken()); // 비용

			// 단방향
			adj.get(u).add(new Graph(v, w));
		}

		// 왕복계산
		int[] rslt = new int[N+1];
		Arrays.fill(rslt, 0);
		
		for (int i = 1; i < rslt.length; i++) {
			rslt[i] += dijkstra(i, X);
			rslt[i] += dijkstra(X, i);
		}
		
		Arrays.sort(rslt);
		System.out.println(rslt[N]);
	}

	public static int dijkstra(int start, int end) {
		// 초기화
		Arrays.fill(dist, INF); // 필수!!!!!
		Arrays.fill(visited, false);

		// 우선순위 큐
		PriorityQueue<Graph> pq = new PriorityQueue<>();
		pq.offer(new Graph(start, 0));
		dist[start] = 0;

		// 다이젝스트라
		while (!pq.isEmpty()) {
			Graph current = pq.poll();
			int cur = current.v;

			if (visited[cur]) // 중복제거
				continue;
			else
				visited[cur] = true;

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
