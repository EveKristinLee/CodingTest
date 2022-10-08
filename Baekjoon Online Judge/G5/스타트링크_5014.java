package algo_0829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 스타트링크_5014 {
		
	static int F; 
	static int S;
	static int G;
	static int U;
	static int D;
	static boolean[] visit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		visit = new boolean[F+1];
		
		bfs(S);
	}

	private static void bfs(int now) {
		Queue<Integer[]> q = new LinkedList<>();
		q.offer(new Integer[] {now, 0});
		visit[now] = true;
		
		while(!q.isEmpty()) {
			Integer[] top = q.poll();
			now = top[0];
			int cnt = top[1];
			if(now == G) {
				System.out.println(cnt);
				return;
			}
			if(now + U <= F && U > 0 && !visit[now + U]) {
				q.offer(new Integer[] {now+U, cnt+1});
				visit[now + U] = true;
			}
			if(now - D >= 1 && D > 0 && !visit[now - D]) {
				q.offer(new Integer[] {now-D, cnt+1});
				visit[now - D] = true;
			}
		}
		System.out.println("use the stairs");
	}

}
