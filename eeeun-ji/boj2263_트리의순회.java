package Bucheon_solved_class4_gold;

import java.io.IOException;
import java.util.Scanner;

//��������_gold3_
public class boj2263_Ʈ���Ǽ�ȸ {
	/*
	 * n���� ������ ���� ����Ʈ�� �������� 1~n���� ��ȣ�� �ߺ�X ����Ʈ���� �ο���(���� ��ȸLVR)�� ����Ʈ����(���� ��ȸLRV)��
	 * �־������� ��������(���� ��ȸVLR) ���ϴ� ���α׷�
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

		for (int i = 0; i < N; i++) { // ������ȸ
			in[sc.nextInt()] = i;
		}

		for (int i = 0; i < N; i++) { // ������ȸ
			post[i] = sc.nextInt();
		}

		pre(0, N - 1, 0, N - 1);
		sc.close();

	}
}
