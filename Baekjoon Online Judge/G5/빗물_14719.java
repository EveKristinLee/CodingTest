package solving.solve_0904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 빗물_14719 {

	static int H, W;
	static int[] rain;
	static int res = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		rain = new int[W];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<W; i++) {
			rain[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<W-1; i++) {
			int cur = rain[i];
			int front = cur;
			int back = cur;
			for(int j=i-1; j>=0; j--) {
				if(front < rain[j]) {
					front = rain[j];
				}
			}
			for(int j=i+1; j<W; j++) {
				if(back < rain[j]) {
					back = rain[j];
				}
			}
			
			if(Math.min(back, front) > cur) {
				res += Math.min(front, back) - cur;
			}
		}
		System.out.println(res);
	}
}
