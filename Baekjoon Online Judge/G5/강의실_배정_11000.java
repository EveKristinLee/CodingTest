package algo_0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 강의실_배정_11000 {
	
	public static class Class implements Comparable<Class> {
		int startTime;
		int endTime;
		public Class(int startTime, int endTime) {
			super();
			this.startTime = startTime;
			this.endTime = endTime;
		}
		
		@Override
		public int compareTo(Class o) {
			int time = startTime - o.startTime;
			if(time == 0) {
				time = endTime - o.endTime;
			}
			return time;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		ArrayList<Class> arr = new ArrayList<>();
		int cnt = 1;
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr.add(new Class(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Collections.sort(arr);
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		q.offer(arr.get(0).endTime);
		for(int i=1; i<n; i++) {
			if(q.peek() <= arr.get(i).startTime) {
				q.poll();  //다른 강의실이 필요 없으므로 앞선 수업의 끝나는 시간만 빼주기
			}
			q.offer(arr.get(i).endTime); //갱신  or 강의실 추가 
		}
		System.out.println(q.size());
	}
}
