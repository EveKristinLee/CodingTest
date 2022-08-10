package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 정사각형_방_1861 {
	
	static int n;
	static int[][] map;
	static int maxCnt = Integer.MIN_VALUE;
	static int cnt = 0;
	static int idx = Integer.MAX_VALUE;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static boolean isInside(int x, int y) {
		return x>=0 && y>=0 && x<n && y<n;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			maxCnt = Integer.MIN_VALUE;
			idx = Integer.MAX_VALUE;
			
			for(int i=0; i<n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					cnt = 0;
					dfs(i, j);
					if(cnt >= maxCnt) {
						if(cnt > maxCnt) {
							idx = map[i][j];
							maxCnt = cnt;
							
						}
						else if (cnt == maxCnt){
							idx = Math.min(idx, map[i][j]);
						}
					}
				}
			}
			sb.append("#"+t+" "+idx+" "+maxCnt).append("\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int x, int y) {
		cnt++;
		
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(isInside(nx, ny)) {
				if(map[nx][ny] == map[x][y] + 1) {
					dfs(nx, ny);
				}
			}
		}
	}
}
