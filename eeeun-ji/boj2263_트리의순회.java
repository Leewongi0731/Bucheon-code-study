package Bucheon_solved_class4_gold;

import java.io.IOException;
import java.util.Scanner;

//분할정복_gold3_
public class boj2263_트리의순회 {
	/*
	 * n개의 정점을 갖는 이진트리 정점에서 1~n까지 번호가 중복X 이진트리의 인오더(중위 순회LVR)와 포스트오더(후위 순회LRV)가
	 * 주어졌을때 프리오더(전위 순회VLR) 구하는 프로그램
	 */
	static int[] post;
	static int[] in;

	static void pre(int is, int ie, int ps, int pe) {
		if (is > ie || ps > pe)
			return;

		int root = post[pe];
		System.out.print(root + " ");

		pre(is, in[root] - 1, ps, ps + in[root] - is - 1);
		pre(in[root] + 1, ie, ps + in[root] - is, pe - 1);
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		post = new int[N];
		in = new int[N + 1];

		for (int i = 0; i < N; i++) { // 중위순회
			in[sc.nextInt()] = i;
		}

		for (int i = 0; i < N; i++) { // 후위순회
			post[i] = sc.nextInt();
		}

		pre(0, N - 1, 0, N - 1);
		sc.close();

	}
}
