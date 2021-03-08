package Bucheon_solved_class4_gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//gold4_
//15:50~
public class boj2096_내려가기 {
	static int N;
	static int[][] arr;
	static int[][] minDp;
	static int[][] maxDp;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1][3];
		minDp = new int[N + 1][3];
		maxDp = new int[N + 1][3];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());
		}

		solve();
		
		int minNum = min(minDp[N][0], minDp[N][1], minDp[N][2]);
		int maxNum = max(maxDp[N][0], maxDp[N][1], maxDp[N][2]);
		
		System.out.println(maxNum + " " + minNum);

	}

	public static void solve() {

		for (int i = 1; i < arr.length; i++) {

			minDp[i][0] = Math.min(minDp[i - 1][0], minDp[i - 1][1]);
			minDp[i][0] += arr[i][0]; //이부분이 있어야 했다.
			
			maxDp[i][0] = Math.max(maxDp[i - 1][0], maxDp[i - 1][1]);
			maxDp[i][0] += arr[i][0];
			
			minDp[i][1] = min(minDp[i - 1][0], minDp[i - 1][1], minDp[i - 1][2]);
			minDp[i][1] += arr[i][1];
			
			maxDp[i][1] = max(maxDp[i - 1][0], maxDp[i - 1][1], maxDp[i - 1][2]);
			maxDp[i][1] += arr[i][1];
			
			minDp[i][2] = Math.min(minDp[i - 1][1], minDp[i - 1][2]);
			minDp[i][2] += arr[i][2];

			maxDp[i][2] = Math.max(maxDp[i - 1][1], maxDp[i - 1][2]);
			maxDp[i][2] += arr[i][2];
		}
	}

	static int min(int n1, int n2, int n3) {
		return Math.min(n1, Math.min(n2, n3));
	}

	static int max(int n1, int n2, int n3) {
		return Math.max(n1, Math.max(n2, n3));
	}
}
