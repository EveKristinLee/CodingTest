package algo_0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 징검다리_건너기_21317 {

	static int n;
	static int[][] rock;
	static int k;
	static int energy;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		rock = new int[n+1][2];
		energy = Integer.MAX_VALUE;
		for(int i=1; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			rock[i][0] = a;
			rock[i][1] = b;
		}
		k = Integer.parseInt(br.readLine());
		
		jump(1, true, 0);
		System.out.println(energy);
	}

	private static void jump(int now, boolean flag, int e) {
		if(now == n) {
			energy = Math.min(energy, e);
			return;
		}
		
		if(now > n) {
			return;
		}
		
		if(flag) {
			jump(now + 3, false, e + k);
		}
		jump(now+1, flag, e+rock[now][0]);			
		jump(now+2, flag, e+rock[now][1]);			
	}
	
	

}
