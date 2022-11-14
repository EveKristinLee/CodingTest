package solving.solve_A형준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//SWEA D4
public class 수영대회_결승전_4193 {
	
	private static class SWIRL {
		int x;
		int y;
		int time;
		public SWIRL(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
	
	static int N;
	static int[][] map;
	static int sx, sy;
	static int ex, ey;
	static SWIRL[][] sw;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	private static boolean isInside(int x, int y) {
		if(x<0 || y<0 || x>=N || y>=N) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			sw = new SWIRL[N][N];
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 2) {
						sw[i][j] = new SWIRL(i, j, 2);
					}
				}
			}
			StringTokenizer st = new StringTokenizer(br.readLine());
			sx = Integer.parseInt(st.nextToken());
			sy = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			ex = Integer.parseInt(st.nextToken());
			ey = Integer.parseInt(st.nextToken());
			
			sb.append("#"+t+" "+bfs(sx, sy)+"\n");
		}
		System.out.println(sb);
	}

	private static int bfs(int x, int y) {
		Queue<Integer[]> q = new ArrayDeque<>();
		boolean[][] visit = new boolean[N][N];
		q.offer(new Integer[] {x, y});
		visit[x][y] = true;
		int time = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			for(int s=0; s<size; s++) {
				Integer[] top = q.poll();
				x = top[0];
				y = top[1];
				
				if(x == ex && y == ey) {
					return time;
				}
				
				for(int i=0; i<4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if(isInside(nx, ny)) {
						if(!visit[nx][ny] && map[nx][ny] == 0) {
							visit[nx][ny] = true;
							q.offer(new Integer[] {nx, ny});
						}
						else if(!visit[nx][ny] && map[nx][ny] == 2) {
							if((time-2) % 3 == 0)  {
								visit[nx][ny] = true;
								q.offer(new Integer[] {nx, ny});
							}
							else {
								q.offer(new Integer[] {x, y});
								
							}
						}
					}
				}
			}
			time++;
		}
		return -1;
	}
}
