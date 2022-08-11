package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 촌수계산_2644 {
	
	static int n; //전체 사람 수
	static int m; //관계 수
	static int p1, p2; //관계 계산 사람 번호
	static int[][] rel;
	static boolean[] visit;
	static int result = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		p1 = Integer.parseInt(st.nextToken());
		p2 = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(br.readLine());
		rel = new int[n+1][n+1];
		visit = new boolean[n+1];
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			rel[a][b] = 1;
			rel[b][a] = 1;
		}
		
		dfs(p1, 0);
		System.out.println(result);
	}

	private static void dfs(int now, int cnt) {
		if(now == p2) {
			result = cnt;
			return;
		}
		visit[now] = true;
		
		for(int i=1; i<=n; i++) {
			if(rel[now][i] == 1 && !visit[i]) {
				dfs(i, cnt+1);
			}
		}
		
	}
}
