package solving.solve_1013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//SWEA 모의
public class 디저트_카페_2105 {

	static int N;
	static int[][] map;
	static boolean[][] visit;
	static int res;
	static boolean[] dessert;
	
	static int[] dx = {1, 1, -1, -1};
	static int[] dy = {1, -1, -1, 1};
	
	private static boolean isInside(int x, int y) {
		if(x<0 || y<0 || x>=N || y>=N) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visit = new boolean[N][N];
			res = Integer.MIN_VALUE;
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					dessert = new boolean[101];
					dfs(i, j, 0, i, j, 0);
				}
			}
			if(res == Integer.MIN_VALUE) {
				sb.append("#"+t+" -1\n");
			}
			else {
				sb.append("#"+t+" "+ res +"\n");
			}
		}
		System.out.println(sb);
	}

	private static void dfs(int x, int y, int dir, int sx, int sy, int cnt) {
		if(dir == 3 && x == sx && y == sy) {
			res = Math.max(res, cnt);
			return;
		}
		
		for(int i=0; i<2; i++) {
			dir += i;
			if(dir == 4) {
				dir = 0;
			}
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			if(isInside(nx, ny)) {
				if(!visit[nx][ny] && !dessert[map[nx][ny]]) {
					visit[nx][ny] = true;
					dessert[map[nx][ny]] = true;
					dfs(nx, ny, dir, sx, sy, cnt+1);
					visit[nx][ny] = false;
					dessert[map[nx][ny]] = false;
				}
			}
		}
	}
}
