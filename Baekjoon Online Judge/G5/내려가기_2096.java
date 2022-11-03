package solving.solve_1103;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ G5
public class 내려가기_2096 {

	static int N;
	static int[][] map;
	static int[][] dpMax;
	static int[][] dpMin;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N+1][3+1];
		dpMax = new int[N+1][3+1];
		dpMin = new int[N+1][3+1];
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=1; i<=3; i++) {
			dpMax[1][i] = map[1][i];
			dpMin[1][i] = map[1][i];
		}
		
		for(int i=2; i<=N; i++) {
			for(int j=1; j<=3; j++) {
				if(j == 1) {
					dpMax[i][j] = Math.max(dpMax[i-1][j], dpMax[i-1][j+1]) + map[i][j];
					dpMin[i][j] = Math.min(dpMin[i-1][j], dpMin[i-1][j+1]) + map[i][j];
				}
				else if (j==3) {
					dpMax[i][j] = Math.max(dpMax[i-1][j], dpMax[i-1][j-1]) + map[i][j];
					dpMin[i][j] = Math.min(dpMin[i-1][j], dpMin[i-1][j-1]) + map[i][j];
				}
				else {
					dpMax[i][j] = Math.max(dpMax[i-1][j-1], Math.max(dpMax[i-1][j], dpMax[i-1][j+1])) + map[i][j];
					dpMin[i][j] = Math.min(dpMin[i-1][j-1], Math.min(dpMin[i-1][j], dpMin[i-1][j+1])) + map[i][j];
				}
			}
		}
		
		int max = Math.max(dpMax[N][1], Math.max(dpMax[N][2], dpMax[N][3]));
		int min = Math.min(dpMin[N][1], Math.min(dpMin[N][2], dpMin[N][3]));
		System.out.println(max + " " + min);
	}

}
