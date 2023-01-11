package solving_2.solve_01.solve_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ S2
public class 안녕_1535 {

	static int N;
	static int[] L; //체력
	static int[] J; //기쁨
	static int[][] dp;
	static int res = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		L = new int[N+1];
		J = new int[N+1];
		dp = new int[N+1][101];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			L[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			J[i] = Integer.parseInt(st.nextToken());
		}
		
		calc(1, 0, 0);
		System.out.println(res);
	}

	private static void calc(int idx, int sum, int life) {
		if(life >= 100) { //목숨 끝
			return;
		}
		if(idx == N+1) {
			res = Math.max(res, sum);
			return;
		}
		
		//인사하는 경우
		calc(idx+1, sum+J[idx], life+L[idx]);
		
		//안하는 경우
		calc(idx+1, sum, life);
	}

}
