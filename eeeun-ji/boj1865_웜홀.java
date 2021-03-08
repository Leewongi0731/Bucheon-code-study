package Bucheon_solved_class4_gold;
//gold4_안됨 ^^^^^^^^!!!!

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//N개의 지점, N개의 지점 사이에 M개의 도로, W개의 웜홀
//도로는 무방향, 웜홀은 방향
//웜홀은 시작위치에서 도착위치로 가능 하나의 경로.
//-->도착하게 되면 시작했을때보다 시간이 뒤로 간다!!

///******중요한거
//벨만포드 알고리즘 !! 
//그 이유는 다익스트라 알고리즘과는 달리 가중치가 음수인 경우에도 적용가능하다!!!
//but, 음수가중치가 사이클을 이루고 있는 경우에는 작동하지 않는다.
class Graph2 {
	int v, w;

	Graph2(int v, int w) { // 생성자
		this.v = v;
		this.w = w;
	}
}

public class boj1865_웜홀 {
	static int N, M, W;
	static int[] dist; // 시작점에서 각 정점으로 가는 최단거리
	static ArrayList<ArrayList<Graph2>> adj; // 인접리스트
	static final int INF = 987654321;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int TC = Integer.parseInt(br.readLine()); // 테스트케이스 개수
		StringBuilder sb = new StringBuilder();
		while (TC-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 지점의 수
			M = Integer.parseInt(st.nextToken()); // 도로의 개수
			W = Integer.parseInt(st.nextToken()); // 웜홀의 개수

			// 정의
			dist = new int[N + 1];
			adj = new ArrayList<>();
			for (int i = 0; i <= N; i++) {
				adj.add(new ArrayList<>());
			}

			for (int i = 0; i < M + W; i++) { // 도로의 정보 입력 받기
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken()); // 출발지
				int E = Integer.parseInt(st.nextToken()); // 도착지
				int T = Integer.parseInt(st.nextToken()); // 비용

				// 무방향이니까 양쪽에서 처리해줘야한다.
				if (i < M) {
					adj.get(S).add(new Graph2(E, T));
					adj.get(E).add(new Graph2(S, T));
				} else {
					adj.get(S).add(new Graph2(E, -T));
				}
			}

			// 음수 사이클 체크
			boolean isMinusCycle = false;
			for (int i = 1; i <= N; i++) {
				if (bellmanFord(i)) { // 변수 실수 조심하자 !!!
					isMinusCycle = true;
					sb.append("YES\n");
					break;
				}
			}

			if (!isMinusCycle) {
				sb.append("NO\n");
			}
		}

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
	}

	public static boolean bellmanFord(int start) {
		// 초기화
		Arrays.fill(dist, INF); // 필수!!!!!
		dist[start] = 0;
		boolean update = false;

		// (정점의 개수 -1)번 동안 최단거리 초기화
		for (int i = 1; i < N; i++) {
			update = false;
			// 최단거리 초기화
			for (int j = 1; j <= N; j++) {
				for (Graph2 g : adj.get(j)) { // 변수 조심하자............
					if (dist[j] != INF && dist[g.v] > dist[j] + g.w) {
						dist[g.v] = dist[j] + g.w;
						update = true;
					}
				}
			}

			// 다 초기화 했을때 종료
			if (!update) {
				break;
			}
		}

		// (정점 개수 -1)번까지 계속 업데이트 발생할경우
		// (정점 개수)번도 업데이트가 발생하면, 음수사이클 발생
		if (update) {
			for (int i = 1; i <= N; i++) {
				for (Graph2 g : adj.get(i)) {
					if (dist[i] != INF && dist[g.v] > dist[i] + g.w) {
						return true;
					}
				}
			}
		}
		return false;
	}
}