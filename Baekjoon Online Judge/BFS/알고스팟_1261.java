package solving.solve_A형보충;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 알고스팟_1261 {

	static int N; //세로
	static int M; //가로
	static int[][] map;
	static int[][] dp;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	private static boolean isInside(int x, int y) {
		if(x<=0 || y<=0 || x>N || y>M) {
			return false;
		}
		return true;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N+1][M+1];
		dp = new int[N+1][M+1];
		for(int i=1; i<=N; i++) {
			String s = br.readLine();
			for(int j=1; j<=M; j++) {
				map[i][j] = s.charAt(j-1) - '0';
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
		dp[1][1] = 0;
		bfs(1, 1);
		System.out.println(dp[N][M]);
	}
	private static void bfs(int x, int y) {
		Queue<Integer[]> q = new ArrayDeque<Integer[]>();
		q.offer(new Integer[] {x, y, 0});
		
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
