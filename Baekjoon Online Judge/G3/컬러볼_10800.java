package solving_2.solve_12.solve_19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//BOJ G3
public class 컬러볼_10800 {
	public static class Ball implements Comparable<Ball>{
		int idx;
		int color;
		int size;
		public Ball(int idx, int color, int size) {
			super();
			this.idx = idx;
			this.color = color;
			this.size = size;
		}
		@Override
		public int compareTo(Ball o) {
			if(this.size == o.size) {
				return this.color - o.color;
			}
			return this.size - o.size;
		}
	}
	
	static Ball[] balls;
	static int[] colors;
	static int N;
	static int sum;
	static int[] result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		balls = new Ball[N];
		colors = new int[N+1];
		result = new int[N];
		sum = 0;
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			balls[i] = new Ball(i, Integer.parseInt(st.nextToken()), 
									Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(balls);
		
		int index = 0;
		for(int i=0; i<N; i++) {
			while(balls[index].size < balls[i].size) {
				sum += balls[index].size;
				colors[balls[index].color] += balls[index].size;
				index++;
			}
			result[balls[i].idx] = sum - colors[balls[i].color];
		}

		for(int i=0; i<N; i++) {
			sb.append(result[i] + "\n");
		}
		System.out.println(sb);
	}
}
