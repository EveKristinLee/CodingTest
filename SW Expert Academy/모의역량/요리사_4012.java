package solving.solve_1031;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//SWEA 모의
public class 요리사_4012 {
	
	static int N;
	static int[][] map;
	static int res;
	static boolean[] div;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			div = new boolean[N];
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			res = Integer.MAX_VALUE;
			comb(0, 0);
			sb.append("#"+t+" "+res+"\n");
		}
		System.out.println(sb);
	}

	private static void comb(int cnt, int start) {
		if(cnt == N/2) {
			//음식 만들어서 차이값 구하기
			res = Math.min(res, makeFood());
			return;
		}
			
		for(int i=start; i<N; i++) {
			div[i] = true;
			comb(cnt+1, i+1);
			div[i] = false;
		}
	}

	private static int makeFood() {
		int foodA = 0;
		int foodB = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i != j) {
					if(div[i] && div[j]) {
						foodA += map[i][j];
					}
					else if(!div[i] && !div[j]) {
						foodB += map[i][j];
					}
				}
			}
		}
		return Math.abs(foodA - foodB);
	}
}
