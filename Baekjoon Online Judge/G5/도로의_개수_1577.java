package solving.solve_1009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ G5
public class 도로의_개수_1577 {

	static int N; //가로
	static int M; //세로
	static int K; //공사중인 도로 개수
	static long[][] dp;
	static boolean[][][] map;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		dp = new long[M+1][N+1];
		map = new boolean[M+1][N+1][2];
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			if(a<c || b<d) {
				if(a<c) {
					map[b][a][0] = true; //아래 공사
				}
				else {
					map[b][a][1] = true; //오른쪽 공사
				}
			}else {
				if(a>c) {
					map[d][c][0] = true; // 아래 공사
				}
				else {
					map[d][c][1] = true; //오른쪽 공사
				}
			}
		}
		
		for(int i=1; i<=N; i++) {
			if(map[0][i-1][0]) {
				break;
			}
			dp[0][i] = 1;
		}
		for(int i=1; i<=M; i++) {
			if(map[i-1][0][1]) {
				break;
			}
			dp[i][0] = 1;
		}
		
		for(int i=1; i<=M; i++) {
			for(int j=1; j<=N; j++) {
				if(!map[i][j-1][0]) {
					dp[i][j] += dp[i][j-1];
				}
				if(!map[i-1][j][1]) {
					dp[i][j] += dp[i-1][j];
				}
			}
		}
		System.out.println(dp[M][N]);
	}
}
