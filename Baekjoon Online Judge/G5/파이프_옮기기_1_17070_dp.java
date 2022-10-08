package solving.solve_0930;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 파이프_옮기기_1_17070_dp {

	static int N;
	static int[][] map;

	static int[] dx = {0, 1, 1}; //옆, 대각선, 아래
	static int[] dy = {1, 1, 0};
	//0:가로, 1:대각선, 2:세로
	
	public static boolean isInside(int x, int y) {
		if(x<=0 || y<=0 || x>N || y>N) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][][] dp = new int[N+1][N+1][3];
		int[][] res = new int[N+1][N+1];
		for(int i=2; i<=N; i++) {
			if(map[1][i] != 1) {
				dp[1][i][0] = 1;
			}else {
				break;
			}
		}
		for(int i=2; i<=N; i++) {
			for(int j=3; j<=N; j++) {
				if(map[i][j] == 0) {
					dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][1];
					if(map[i-1][j] == 0 && map[i][j-1] == 0) {
						dp[i][j][1] = dp[i-1][j-1][1] + dp[i-1][j-1][0] + dp[i-1][j-1][2];
					}
					dp[i][j][2] = dp[i-1][j][1] + dp[i-1][j][2];					
				}
			}
		}
		System.out.println(dp[N][N][0] + dp[N][N][1] + dp[N][N][2]);
	}

}
