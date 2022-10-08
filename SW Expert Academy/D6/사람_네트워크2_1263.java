package solving.solve_1007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//SWEA D6
public class 사람_네트워크2_1263 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N;
		int[][] map;
		final int INF = Integer.MAX_VALUE >> 3;
		int[] C;
		
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			C = new int[N];
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(i!=j && map[i][j] == 0) {
						map[i][j] = INF;
					}
				}
			}
			
			for(int k=0; k<N; k++) {
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						map[i][j] = Math.min(map[i][k] + map[k][j], map[i][j]);
					}
				}
			}
			
			for(int i=0; i<N; i++) {
				int sum = 0;
				for(int j=0; j<N; j++) {
					sum += map[i][j];
				}
				C[i] = sum;
			}
			Arrays.sort(C);
			sb.append("#"+t+" "+C[0]+"\n");
			
		}
		System.out.println(sb);
	}
}
