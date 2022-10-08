package algo_0829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 데스_나이트_16948 {

	static int N;
	static boolean[][] visit;
	static int sx, sy;
	static int ex, ey;
	
	static int[] dx = {-2, -2, 0, 0, 2, 2};
	static int[] dy = {-1, 1, -2, 2, -1, 1};
	
	public static boolean isInside(int x, int y) {
		if(x<0 || y<0 || x>=N || y>=N) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		sx = Integer.parseInt(st.nextToken());
		sy = Integer.parseInt(st.nextToken());
		ex = Integer.parseInt(st.nextToken());
		ey = Integer.parseInt(st.nextToken());
		visit = new boolean[N][N];
		
		bfs(sx, sy);
	}

	private static void bfs(int x, int y) {
		Queue<Integer[]> q = new LinkedList<Integer[]>();
		q.offer(new Integer[] {x, y, 0});
		visit[x][y] = true;
		
		while(!q.isEmpty()) {
			Integer[] top = q.poll();
			x = top[0];
			y = top[1];
			int dist = top[2];
			if(x == ex && y == ey) {
				System.out.println(dist);
				return;
			}
			for(int i=0; i<6; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(isInside(nx, ny) && !visit[nx][ny]) {
					q.offer(new Integer[] {nx, ny, dist+1});
					visit[nx][ny] = true;
				}
			}
		}
		System.out.println(-1);
	}

}
