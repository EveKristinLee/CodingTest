package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 숨바꼭질_1697 {
	
	static int[] map;
	static boolean[] visit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		map = new int[100001];
		visit = new boolean[100001];
		
		bfs(n, k);
	}

	private static void bfs(int start, int to) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(start);
		visit[start] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			if(cur == to) {
				System.out.println(map[to]);
			}
			
			if(cur +1 <= 100000 && !visit[cur+1]) {
				q.offer(cur+1);
				visit[cur+1] = true;
				map[cur+1] = map[cur] + 1;
			}
			if(cur *2 <= 100000 && !visit[cur*2]) {
				q.offer(cur*2);
				visit[cur*2] = true;
				map[cur*2] = map[cur] + 1;
			}
			if(cur -1 >= 0 && !visit[cur-1]) {
				q.offer(cur-1);
				visit[cur-1] = true;
				map[cur-1] = map[cur] + 1;
			}
		}
	}
	


}
