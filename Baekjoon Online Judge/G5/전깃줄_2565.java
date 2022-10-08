package solving.solve_1007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//BOJ G5
public class 전깃줄_2565 {
	
	public static class Line implements Comparable<Line> {
		int a;
		int b;
		
		public Line(int a, int b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo(Line o) {
			return this.a - o.a;
		}
	}
	
	static int N; //전깃줄의 개수
	static int[] dp;
	static Line[] link;
	static int[] C;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		link = new Line[N];
		dp = new int[N];
		C = new int[N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			link[i] = new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(link);
		for(int i=0; i<N; i++) {
			dp[i] = link[i].b;
		}
		
		int size = 0;
		for(int k=0; k<N; k++) {
			int pos = Arrays.binarySearch(C, 0, size, dp[k]);
			if(pos >= 0) continue;
			
			int iPos = Math.abs(pos)-1;
			C[iPos] = dp[k];
			if(iPos == size) {
				size++;
			}
		}
		System.out.println(N - size);
	}
}
