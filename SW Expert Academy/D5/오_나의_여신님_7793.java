package solving.solve_A형보충;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 오_나의_여신님_7793 {
	
	static int N;
	static int M;
	static char[][] map;
	static boolean[][] visit;
	static int sx;
	static int sy;
	static int destx;
	static int desty;
	static Queue<Integer[]> q;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	private static boolean isInside(int x, int y) {
		if(x<0 || y<0 || x>=N || y>=M) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			visit = new boolean[N][M];
			q = new ArrayDeque<Integer[]>();
			
			for(int i=0; i<N; i++) {
				String s = br.readLine();
				for(int j=0; j<M; j++) {
					map[i][j] = s.charAt(j);
					if(map[i][j] == 'S') {
						sx = i;
						sy = j;
					}
					if(map[i][j] == '*') {
						q.offer(new Integer[] {i, j, -1});
					}
					if(map[i][j] == 'D') {
						destx = i;
						desty = j;
					}
				}
			}
			q.offer(new Integer[] {sx, sy, 0});
			visit[sx][sy] = true;
			int res = bfs();
			if(res != -1) {
				sb.append("#"+t+" "+res+"\n");
			}else {
				sb.append("#"+t+" GAME OVER\n");
			}
		}
		System.out.println(sb);
	}

	private static int bfs() {
		while(!q.isEmpty()) {
			Integer[] top = q.poll();
			int x = top[0];
			int y = top[1];
			int cnt = top[2];
			visit[x][y] = true;
			char nowChar = map[x][y];
			if(x == destx && y == desty) {
				return cnt;
			}
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(isInside(nx, ny) && !visit[nx][ny]) {
					if(map[nx][ny] != 'X') {
						if(nowChar == '*') {
							if(map[nx][ny] != 'D') {
								visit[nx][ny] = true;
								map[nx][ny] = nowChar;
								q.offer(new Integer[] {nx, ny, -1});
							}
						}
						else  if(nowChar == 'S') {
							visit[nx][ny] = true;
							map[nx][ny] = nowChar;
							q.offer(new Integer[] {nx, ny, cnt+1});
						}
					}
					 
				}
			}
		}
		return -1;
	}
}
