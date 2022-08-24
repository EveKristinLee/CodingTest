package solving.solve_0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//BOJ BFS
public class 탈출_3055 {

	static int R; //세로
	static int C; //가로
	static char[][] map;
	static boolean[][] visit;
	static int nowX; //고슴도치 위치
	static int nowY;
	static Queue<Integer[]> q = new LinkedList<Integer[]>();
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	private static boolean isInside(int x, int y) {
		if(x<0 || y<0 || x>=R || y>=C) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		visit = new boolean[R][C];
		for(int i=0; i<R; i++) {
			String line = br.readLine();
			map[i] = line.toCharArray();
			for(int j=0; j<C; j++) {
				if(map[i][j] == 'S') {
					nowX = i;
					nowY = j;
				}
				if(map[i][j] == '*') {
					q.offer(new Integer[] {i, j});
					visit[i][j] = true;
				}
			}
		}
		q.offer(new Integer[] {nowX, nowY, 0});
		visit[nowX][nowY] = true;
		
		bfs();
	}
	
	private static void bfs() {
		while(!q.isEmpty()) {
			Integer[] top = q.poll();
			int x = top[0];
			int y = top[1];
			int cnt = -1;
			if(map[x][y] == 'S') {
				cnt = top[2];
			}
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(isInside(nx, ny) && !visit[nx][ny]) {
					if(map[x][y] == '*' && map[nx][ny] != 'X' && map[nx][ny] != 'D') { //물일때
						map[nx][ny] = '*';
						visit[nx][ny] = true;
						q.offer(new Integer[] {nx, ny});
					}
					else if(map[x][y] == 'S' && map[nx][ny] != '*' && map[nx][ny] != 'X') { //고습도치일때
						 if(map[nx][ny] == 'D') {
							 System.out.println(cnt+1);
							 return;
						 }
						 map[nx][ny] = 'S';
						 visit[nx][ny] = true;
						 q.offer(new Integer[] {nx, ny, cnt+1});
					}
				}
			}
		}
		System.out.println("KAKTUS");
	}

}
