package solving.solve_1007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//BOJ G5
public class 도서관_1461 {

	static int N; //책의 개수
	static int M; //한번에 들 수 있는 책의 개수
	static int[] loc;
	static int nowLoc;
	static int sumDist = 0;
	static PriorityQueue<Integer> minus;
	static PriorityQueue<Integer> plus;
	
	static int min;
	static int max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		minus = new PriorityQueue<>();
		plus = new PriorityQueue<>(Collections.reverseOrder());
		loc = new int[N];
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			loc[i] = Integer.parseInt(st.nextToken());
			if(loc[i] > 0) {
				plus.offer(loc[i]);
				max = Math.max(max, loc[i]);
			}
			else {
				minus.offer(loc[i]);
				min = Math.min(min, loc[i]);
			}
		}
		
		while(!minus.isEmpty()) {
			if(minus.size() >= M) {
				int before = -1;
				for(int i=0; i<M; i++) {
					if(before == -1) {
						before = Math.abs(minus.poll());
						sumDist += before;
					}
					else {
						int now = Math.abs(minus.poll());
						sumDist += Math.abs(before - now);
						before = now;
					}
				}
				sumDist += before; //0으로 돌아가기
			}
			else {
				int before = -1;
				int size = minus.size();
				for(int i=0; i<size; i++) {
					if(before == -1) {
						before = Math.abs(minus.poll());
						sumDist += before;
					}
					else {
						int now = Math.abs(minus.poll());
						sumDist += Math.abs(before - now);
						before = now;
					}
				}
				sumDist += before; //0으로 돌아가기
			}
		}
		
		while(!plus.isEmpty()) {
			if(plus.size() >= M) {
				int before = -1;
				for(int i=0; i<M; i++) {
					if(before == -1) {
						before = plus.poll();
						sumDist += before;
					}
					else {
						int now = plus.poll();
						sumDist += Math.abs(before - now);
						before = now;
					}
				}
				sumDist += before; //0으로 돌아가기
			}
			else {
				int before = -1;
				int size = plus.size();
				for(int i=0; i<size; i++) {
					if(before == -1) {
						before = plus.poll();
						sumDist += before;
					}
					else {
						int now = plus.poll();
						sumDist += Math.abs(before - now);
						before = now;
					}
				}
				sumDist += before; //0으로 돌아가기
			}
		}
		
		if(min == Integer.MAX_VALUE) {
			min = 0;
		}
		if(max == Integer.MIN_VALUE) {
			max = 0;
		}
		
		sumDist -= Math.max(Math.abs(min), Math.abs(max));
		System.out.println(sumDist);
	}

}
