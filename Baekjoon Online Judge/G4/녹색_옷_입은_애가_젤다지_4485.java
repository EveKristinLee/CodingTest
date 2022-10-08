package solving.solve_1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 녹색_옷_입은_애가_젤다지_4485 {

	static int N;
	static int[][] map;
	static int[][] dp;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	private static boolean isInside(int x, int y) {
		if(x<0 || y<0 || x>=N || y>=N) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int idx = 1;
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N == 0) {
				break;
			}
			map = new int[N][N];
			dp = new int[N][N];
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					dp[i][j] = Integer.MAX_VALUE;
				}
				

			}
			
			dp[0][0] = map[0][0];
			go(0, 0);
			
			sb.append("Problem "+ idx + ": "+ dp[N-1][N-1] + "\n");
			idx++;
		}
		System.out.println(sb);
	}

	private static void go(int x, int y) {
		Queue<Integer[]> q = new ArrayDeque<Integer[]>();
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
