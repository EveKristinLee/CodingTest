package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Contact_1238 {
	
	static int n;
	static int start;
	static int[][] link;
	static int[] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=10; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			link = new int[101][101];
			visit = new int[101];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				link[from][to] = 1;
			} 
			
			sb.append("#"+t+" "+bfs(start)).append("\n");
		}
		System.out.println(sb);
	}
	private static int bfs(int now) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(now);
		visit[now] = 1;
		
		while(!q.isEmpty()) {
			now = q.poll();
			
			for(int i=1; i<=100; i++) {
				if(link[now][i] == 1 && visit[i] == 0) {
					q.offer(i);
					visit[i] = visit[now] + 1;
					
				}
			}
		}
		int maxDist = Integer.MIN_VALUE;
		int res = 0;
		for(int i=1; i<=100; i++) {
			if(maxDist <= visit[i]) {
				maxDist = visit[i];
				res = i;
			}
		}
		return res;
	}

}
