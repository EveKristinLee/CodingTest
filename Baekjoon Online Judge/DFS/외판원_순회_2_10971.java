package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 외판원_순회_2_10971 {

	static int n;
	static int[][] map;
	static boolean[] visit;
	static int minCost = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		visit = new boolean[n];
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<n; i++) {
			dfs(i, i, 0, 0);
		}
		System.out.println(minCost);
	}
	
	private static void dfs(int start, int now, int cnt, int cost) {
		if(cnt==n && start == now) { //다 돌고 처음으로 돌아옴
			minCost = Math.min(minCost, cost);
			return;
		}
		
		if(cost >= minCost) {
			return;
		}
		
		for(int i=0; i<n; i++) {
			if(!visit[i] && map[now][i] > 0) {
				visit[i] = true;
				dfs(start, i, cnt+1, cost + map[now][i]);
				visit[i] = false;
			}
		}
	}
}
