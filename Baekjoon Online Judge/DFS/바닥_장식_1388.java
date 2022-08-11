package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 바닥_장식_1388 {

	static int n;
	static int m;
	static char[][] map;
	static boolean[][] visit;
	static int cnt = 0;
	
	static int[] ux = {1, -1};
	static int[] uy = {0, 0}; //위, 아래
	
	static int[] rx = {0, 0};
	static int[] ry = {1, -1}; //오, 왼
	
	static boolean isInside(int x, int y) {
		if(x<0 || y<0 || x >=n || y>=m) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		visit = new boolean[n][m];

		for(int i=0; i<n; i++) {
			String line = br.readLine();
			map[i] = line.toCharArray();
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(!visit[i][j]) {
					dfs(i, j, map[i][j]);
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}

	private static void dfs(int x, int y, char c) {
		visit[x][y] = true;
		
		if(c == '-') {
			for(int i=0; i<2; i++) {
				int nx = x + rx[i];
				int ny = y + ry[i];
				if(isInside(nx, ny) && !visit[nx][ny] && map[nx][ny] == c) {
					dfs(nx, ny, map[nx][ny]);
				}
			}
		}
		if(c == '|') {
			for(int i=0; i<2; i++) {
				int nx = x + ux[i];
				int ny = y + uy[i];
				if(isInside(nx, ny) && !visit[nx][ny] && map[nx][ny] == c) {
					dfs(nx, ny, map[nx][ny]);
				}
			}
		}
	}

}
