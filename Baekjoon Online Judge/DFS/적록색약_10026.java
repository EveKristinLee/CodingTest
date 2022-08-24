package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//BOJ DFS
public class 적록색약_10026 {

	static int n;
	static char[][] map;
	static boolean[][] visit;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	private static boolean isInside(int x, int y) {
		if(x<0 || y<0 || x>=n || y>=n) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		
		map = new char[n][n];
		visit = new boolean[n][n];
		for(int i=0; i<n; i++) {
			String line = br.readLine();
			map[i] = line.toCharArray();
		}
		
		int cnt = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(!visit[i][j]) {
					dfs(i, j);
					cnt++;
				}
			}
		}
		sb.append(cnt+" ");
		cnt = 0;
		for(int i=0; i<n; i++) {
			Arrays.fill(visit[i], false);
		}
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j] == 'R') {
					map[i][j] = 'G';
				}
			}
		}
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(!visit[i][j]) {
					dfs(i, j);
					cnt++;
				}
			}
		}
		sb.append(cnt+"\n");
		System.out.println(sb);
	}

	private static void dfs(int x, int y) {
		char color = map[x][y];
		visit[x][y] = true;
		
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(isInside(nx, ny) && !visit[nx][ny]) {
				if(color == map[nx][ny]) {
					dfs(nx, ny);
				}
			}
		}
	}
}
