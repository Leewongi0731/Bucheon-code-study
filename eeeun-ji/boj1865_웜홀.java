package Bucheon_solved_class4_gold;
//gold4_�ȵ� ^^^^^^^^!!!!

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//N���� ����, N���� ���� ���̿� M���� ����, W���� ��Ȧ
//���δ� ������, ��Ȧ�� ����
//��Ȧ�� ������ġ���� ������ġ�� ���� �ϳ��� ���.
//-->�����ϰ� �Ǹ� �������������� �ð��� �ڷ� ����!!

///******�߿��Ѱ�
//�������� �˰��� !! 
//�� ������ ���ͽ�Ʈ�� �˰������ �޸� ����ġ�� ������ ��쿡�� ���밡���ϴ�!!!
//but, ��������ġ�� ����Ŭ�� �̷�� �ִ� ��쿡�� �۵����� �ʴ´�.
class Graph2 {
	int v, w;

	Graph2(int v, int w) { // ������
		this.v = v;
		this.w = w;
	}
}

public class boj1865_��Ȧ {
	static int N, M, W;
	static int[] dist; // ���������� �� �������� ���� �ִܰŸ�
	static ArrayList<ArrayList<Graph2>> adj; // ��������Ʈ
	static final int INF = 987654321;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int TC = Integer.parseInt(br.readLine()); // �׽�Ʈ���̽� ����
		StringBuilder sb = new StringBuilder();
		while (TC-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // ������ ��
			M = Integer.parseInt(st.nextToken()); // ������ ����
			W = Integer.parseInt(st.nextToken()); // ��Ȧ�� ����

			// ����
			dist = new int[N + 1];
			adj = new ArrayList<>();
			for (int i = 0; i <= N; i++) {
				adj.add(new ArrayList<>());
			}

			for (int i = 0; i < M + W; i++) { // ������ ���� �Է� �ޱ�
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken()); // �����
				int E = Integer.parseInt(st.nextToken()); // ������
				int T = Integer.parseInt(st.nextToken()); // ���

				// �������̴ϱ� ���ʿ��� ó��������Ѵ�.
				if (i < M) {
					adj.get(S).add(new Graph2(E, T));
					adj.get(E).add(new Graph2(S, T));
				} else {
					adj.get(S).add(new Graph2(E, -T));
				}
			}

			// ���� ����Ŭ üũ
			boolean isMinusCycle = false;
			for (int i = 1; i <= N; i++) {
				if (bellmanFord(i)) { // ���� �Ǽ� �������� !!!
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
		// �ʱ�ȭ
		Arrays.fill(dist, INF); // �ʼ�!!!!!
		dist[start] = 0;
		boolean update = false;

		// (������ ���� -1)�� ���� �ִܰŸ� �ʱ�ȭ
		for (int i = 1; i < N; i++) {
			update = false;
			// �ִܰŸ� �ʱ�ȭ
			for (int j = 1; j <= N; j++) {
				for (Graph2 g : adj.get(j)) { // ���� ��������............
					if (dist[j] != INF && dist[g.v] > dist[j] + g.w) {
						dist[g.v] = dist[j] + g.w;
						update = true;
					}
				}
			}

			// �� �ʱ�ȭ ������ ����
			if (!update) {
				break;
			}
		}

		// (���� ���� -1)������ ��� ������Ʈ �߻��Ұ��
		// (���� ����)���� ������Ʈ�� �߻��ϸ�, ��������Ŭ �߻�
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