package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 토마토_7576 {

	static int n;
	static int m;
	static int[][] map;
	static boolean[][] visit;
	static int minCnt = 0;
	static Queue<Integer[]> q;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static boolean isInside(int x, int y) {
		if(x<0 || y<0 || x>=n || y>=m) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visit = new boolean[n][m];
		q = new LinkedList<Integer[]>();
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					q.offer(new Integer[] {i, j, 0});
					visit[i][j] = true;
				}
			}
		}
		
		bfs();
	
		boolean flag = true;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j] == 0) {
					flag = false;
					break;
				}
			}
		}
		if(flag) {
			System.out.println(minCnt);
		}
		else {
			System.out.println("-1");
		}
	}

	private static void bfs() {
		while(!q.isEmpty()) {
			Integer[] top = q.poll();
			int x = top[0];
			int y = top[1];
			minCnt = top[2];
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(isInside(nx, ny)) {
					if(!visit[nx][ny] && map[nx][ny] == 0) {
						q.offer(new Integer[] {nx, ny, minCnt+1});
						visit[nx][ny] = true;
						map[nx][ny] = 1;
					}
				}
			}
		}
	}

}
