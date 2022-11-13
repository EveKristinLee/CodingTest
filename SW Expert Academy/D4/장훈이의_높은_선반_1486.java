package solving.solve_A형준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//SWEA D4
public class 장훈이의_높은_선반_1486 {
	static int N; //점원들의 수
	static int B; //선반의 높이
	static int[] height;
	static int res;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			height = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				height[i] = Integer.parseInt(st.nextToken());
			}
			res = Integer.MAX_VALUE;
			getHeight(0, 0);
			sb.append("#"+t+" "+(res-B)+"\n");
		}
		System.out.println(sb);
	}

	private static void getHeight(int idx, int sum) {
		if(sum >= B) {
			res = Math.min(res, sum);
			return;
		}
		if(idx >= N) {
			return;
		}
		
		getHeight(idx+1, sum+height[idx]);
		getHeight(idx+1, sum);
	}
}
