package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//SWEA D4
public class 키_순서_5643 {
	
	static int N;
	static int M;
	static int[][] map;
	final static int INF = Integer.MAX_VALUE >> 3;
	static int[] height;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			M =Integer.parseInt(br.readLine());
			map = new int[N+1][N+1];
			for(int i=0; i<M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				map[a][b] = 1; //a보다 b가 더 작다.
			}
			
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(i!=j && map[i][j] == 0) {
						map[i][j] = INF;
					}
				}
			}
			
			for(int k=1; k<=N; k++) {
				for(int i=1; i<=N; i++) {
					for(int j=1; j<=N; j++) {
						if(map[i][j] > map[i][k] + map[k][j]) {
							map[i][j] = map[i][k] + map[k][j];
						}
					}
				}
			}
			
			height = new int[N+1];
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(i!= j && map[i][j] != INF) {
						height[i]++;
						height[j]++;
					}
				}
			}
			
			int cnt = 0;
			for(int i=1; i<=N; i++) {
				if(height[i] == N-1) {
					cnt++;
				}
			}
			
			sb.append("#"+t+" "+cnt+"\n");
		}
		System.out.println(sb);
	}
}
