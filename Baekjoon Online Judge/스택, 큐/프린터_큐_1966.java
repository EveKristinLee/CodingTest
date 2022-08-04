package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 프린터_큐_1966 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		while(T>0) {
			Queue<Integer> q = new LinkedList<>();
			Queue<Integer> idx = new LinkedList<>();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				q.offer(Integer.parseInt(st.nextToken()));
				idx.offer(i);
			}
			
			int cnt = 1;
			while(!q.isEmpty()) {
				int maxNum = Collections.max(q); // 중요도 높은 문서
				int nowIdx = idx.poll();
				int now = q.poll();
				
				if(now == maxNum) { //가장 큰 수 일때 출력 출력 개수 증가
					if(m == nowIdx) {
						break;
					}
					cnt++;
				}
				else {
					q.offer(now);
					idx.offer(nowIdx);
				}
			}
			System.out.println(cnt);
			T--;
		}
	}
}
