package solving_2.solve_01.solve_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ G5
public class 개똥벌레_3020 {

	static int N;
	static int H;
	static int[] top;
	static int[] bot;
	static int min;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		top = new int[H+1];
		bot = new int[H+1];
		min = Integer.MAX_VALUE;
		cnt = 0;
		for(int i=0; i<N/2; i++) {
			bot[Integer.parseInt(br.readLine())]++;
			top[Integer.parseInt(br.readLine())]++;
		}
		
		calc();
		System.out.println(min + " " + cnt);
	}

	private static void calc() {
		int[] bot_sum = new int[H+1];
		int[] top_sum = new int[H+1];
		
		for(int i=1; i<=H; i++) {
			bot_sum[i] = bot_sum[i-1] + bot[i];
			top_sum[i] = top_sum[i-1] + top[i];
		}
		
		for(int i=1; i<=H; i++) {
			int c = 0;
			
			c += bot_sum[H] - bot_sum[i-1];
			c += top_sum[H] - top_sum[H-i];
			
			if(min>c) {
				min = c;
				cnt=1;
			}
			else if(min == c) {
				cnt++;
			}
		}
	}
	

}
