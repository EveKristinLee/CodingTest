package algo_0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 유기농_배추_1012 {

	static int m; //가로
	static int n; //세로
	static int k; //배추
	static int[][] map; 
	static boolean[][] visit; 
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	public static boolean isInside(int x, int y) {
		if(x<0 || y<0 || x>=n || y>=m) {
			return false;
		}
		return true;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		while(T-- >0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			map = new int[n][m];
			visit = new boolean[n][m];
			
			for(int i=0; i<k; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				map[b][a] = 1;
			}
			
			int cnt = 0;
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(map[i][j] == 1 && !visit[i][j]) {
						cnt++;
						dfs(i, j);
					}
				}
			}
			System.out.println(cnt);
		}
		
	}
	private static void dfs(int x, int y) {
		visit[x][y] = true;
		
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(isInside(nx, ny)) {
				if(!visit[nx][ny] && map[nx][ny] == 1) {
					dfs(nx, ny);
				}
			}
		}
	}

}
