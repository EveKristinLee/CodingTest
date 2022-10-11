package solving.solve_1011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//SWEA 모의
public class 등산로_조성_1949 {

	static int N;
	static int K;
	static int[][] map;
	static boolean[][] visit;
	static int resDist;
	static List<Integer[]> pos;
	
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
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			visit = new boolean[N][N];
			pos = new ArrayList<Integer[]>();
			int high = 0;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					high = Math.max(high, map[i][j]);
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] == high) {
						pos.add(new Integer[] {i, j});
					}
				}
			}
			
			resDist = Integer.MIN_VALUE;
			for (Integer[] in : pos) {
				visit[in[0]][in[1]] = true;
				dfs(in[0], in[1], 1, false);
				visit[in[0]][in[1]] = false;
			}
			sb.append("#"+t+" "+resDist+"\n");
		}
		System.out.println(sb);
	}

	private static void dfs(Integer x, Integer y, int dist, boolean flag) {
		resDist = Math.max(dist, resDist);
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(isInside(nx, ny) && !visit[nx][ny]) {
				if(map[x][y] > map[nx][ny]) {
					visit[nx][ny] = true;
					//안깍고 바로
					dfs(nx, ny, dist+1, flag);
					visit[nx][ny] = false;
				}
				if(!flag) { //깍을 수 있을때
					int dif = Math.abs(map[x][y] - map[nx][ny]) + 1;
					if(dif <= K) {
						map[nx][ny] -= dif;
						dfs(nx, ny, dist+1, true);
						map[nx][ny] += dif;
					}
				}
			}
		}
	}
}
