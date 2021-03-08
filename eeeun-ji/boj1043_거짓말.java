package Bucheon_solved_class4_gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//gold4_
public class boj1043_거짓말 {
	static int N, M;
	static boolean[] know;
	static boolean[][] visited = new boolean[M][];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 사람수 N, 파티의 수 M
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		know = new boolean[N + 1];
		// 이야기의 진실을 아는 사람의 수와 번호가 주어진다.
		st = new StringTokenizer(br.readLine());
		int knowCnt = Integer.parseInt(st.nextToken());
		for (int i = 0; i < knowCnt; i++) {
			know[Integer.parseInt(st.nextToken())] = true;
		}

		// M개의 줄에 각 파티마다 오는 사람의 수와 참석자 번호 입력
		int[][] party = new int[M][];
		visited = new boolean[N + 1][N + 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int comeCnt = Integer.parseInt(st.nextToken());
			
			party[i] = new int[comeCnt];
			party[i][0] = Integer.parseInt(st.nextToken());
			for (int j = 1; j < comeCnt; j++) {
				party[i][j] = Integer.parseInt(st.nextToken());
				visited[party[i][j - 1]][party[i][j]] = visited[party[i][j]][party[i][j - 1]] = true;
			}
		}
		
		//dfs
		for (int i = 1; i <= N; i++) {
			if(know[i])
				dfs(i);
		}
		
		int cnt = 0;
		for (int i = 0; i < M; i++) {
			if(!know[party[i][0]]) //파티[i]에 아는사람이 없다면
				cnt++;
		}
		System.out.println(cnt);
	}
	
	static void dfs(int n) {
		for (int i = 1; i <= N; i++) {
			if(visited[n][i] && !know[i]) {
				know[i] = true;
				dfs(i);
			}
		}
	}
}
