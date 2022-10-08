package solving.solve_1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 달이_차오른다_가자_1194 {

	static int N;
	static int M;
	static char[][] map;
	static boolean[][][] visit;
	static int key;
	static int sx, sy;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	private static boolean isInside(int x, int y) {
		if(x<0 || y<0 || x>=N || y>=M) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visit = new boolean[N][M][64];
		key = 0;
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] == '0') {
					sx = i;
					sy = j;
				}
			}
		}
		System.out.println(bfs(sx, sy));
	}

	private static int bfs(int x, int y) {
		Queue<Integer[]> q = new ArrayDeque<Integer[]>();
		q.offer(new Integer[] {x, y, 0, 0});
		visit[x][y][key] = true;
		
		while(!q.isEmpty()) {
			Integer[] top = q.poll();
			x = top[0];
			y = top[1];
			int nKey = top[2];
			int cnt = top[3];
			visit[x][y][nKey] = true;
			if(map[x][y] == '1') {
				return cnt;
			}
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				nKey = top[2]; //키값 갱신될 수 있으므로 다시 받아오기
				if(isInside(nx, ny) && !visit[nx][ny][nKey]) {
					if(map[nx][ny] == '.' || map[nx][ny] == '1' || map[nx][ny] == '0') {
						visit[nx][ny][nKey] = true;
						q.offer(new Integer[] {nx, ny, nKey, cnt+1});
					}
					else if(map[nx][ny] >= 'a' && map[nx][ny] <= 'f') {
						visit[nx][ny][nKey] = true;
						nKey = nKey | (1 << 'f' - map[nx][ny]);
						visit[nx][ny][nKey] = true;
						q.offer(new Integer[] {nx, ny, nKey, cnt+1});
					}
					else if(map[nx][ny] >= 'A' && map[nx][ny] <= 'F') {
						if(((1<<'F' - map[nx][ny]) & nKey) != 0) {
							visit[nx][ny][nKey] = true;
							q.offer(new Integer[] {nx, ny, nKey, cnt+1});
						}
					}
				}
			}
		}
		//탈출 실패
		return -1;
	}
}
