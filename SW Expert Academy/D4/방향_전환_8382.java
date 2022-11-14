package solving.solve_A형준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//SWEA D4
public class 방향_전환_8382 {
	static int sx, sy;
	static int ex, ey;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0}; //가로  | 세로
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			sx = Integer.parseInt(st.nextToken());
			sy = Integer.parseInt(st.nextToken());
			ex = Integer.parseInt(st.nextToken());
			ey = Integer.parseInt(st.nextToken());
			
			sb.append("#"+t+" "+bfs(sx, sy)+"\n");
		}
		System.out.println(sb);
	}

	private static int bfs(int x, int y) {
		Queue<Integer[]> q = new ArrayDeque<>();
		if(sy < ey) 
			q.offer(new Integer[] {x, y, 0, 0});
		else	
			q.offer(new Integer[] {x, y, 1, 0});
		if(sx < ex)
			q.offer(new Integer[] {x, y, 2, 0});
		else
			q.offer(new Integer[] {x, y, 3, 0});
		
		while(!q.isEmpty()) {
			Integer[] top = q.poll();
			x = top[0];
			y = top[1];
			int dir = top[2];
			int cnt = top[3];
			
			if(x == ex && y == ey) {
				return cnt;
			}
			
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			if(dir == 0 || dir == 1) {
				if(nx < ex)
					q.offer(new Integer[] {nx, ny, 2, cnt+1});
				else
					q.offer(new Integer[] {nx, ny, 3, cnt+1});
			}
			if(dir == 2 || dir == 3) {
				if(ny < ey) 
					q.offer(new Integer[] {nx, ny, 0, cnt+1});
				else
					q.offer(new Integer[] {nx, ny, 1, cnt+1});
			}
		}
		return 0;
	}
}
