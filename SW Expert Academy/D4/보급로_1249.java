package solving.solve_0930;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 보급로_1249 {

	static int N;
	static int[][] map;
	static int[][] dp;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	private static boolean isInside(int x, int y) {
		if(x<=0||y<=0||x>N||y>N) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N+1][N+1];
			dp = new int[N+1][N+1];
			
			for(int i=1; i<=N; i++) {
				String s = br.readLine();
				for(int j=1; j<=s.length(); j++) {
					map[i][j] = s.charAt(j-1) - '0';
					dp[i][j] = 987654321;
				} 
			}
			
			dp[1][1] = 0;
			bfs(1, 1);
			
			sb.append("#"+t+" "+dp[N][N]+"\n");
		}
		System.out.println(sb);
	}
	
	private static void bfs(int x, int y) {
		Queue<Integer[]> q = new LinkedList<Integer[]>();
		
		q.offer(new Integer[] {x, y});
		
		while(!q.isEmpty()) {
			Integer[] top = q.poll();
			x = top[0];
			y = top[1];
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(isInside(nx, ny)) {
					if(dp[nx][ny] > dp[x][y] + map[nx][ny]) {
						dp[nx][ny] = dp[x][y] + map[nx][ny];
						q.offer(new Integer[] {nx, ny});
					}
				}
			}
		}
	}

}
