package solving.solve_0904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 테트로미노_14500 {

	static int N;
	static int M;
	static int[][] map;
	static boolean[][] visit;
	static int res = Integer.MIN_VALUE;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static int[][][] sp = {
			{{-1, 0}, {1, 0}, {0, 1}},  //ㅏ
			{{0, -1}, {0, 1}, {1, 0}},  //ㅜ
			{{0, -1}, {-1, 0}, {1, 0}}, //ㅏ
			{{0, -1}, {-1, 0}, {0, 1}}  //ㅗ
	};
	
	public static boolean isInside(int x, int y) {
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
		map = new int[N][M];
		visit= new boolean[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				visit[i][j] = true;
				tetro(i, j, 1, map[i][j]);
				visit[i][j] = false;
				chk(i, j);
			}
		}
		System.out.println(res);
	}
	private static void chk(int x, int y) {
		for(int i=0; i<4; i++) {
			int sum = map[x][y];
			boolean flag = true;
			for(int j=0; j<3; j++) {
				int nx = x + sp[i][j][0];
				int ny = y + sp[i][j][1];
				if(isInside(nx, ny)) {
					sum += map[nx][ny];
				}
				else {
					flag = false;
					break;
				}
			}
			if(flag) {
				res = Math.max(res, sum);
			}
		}
	}
	private static void tetro(int x, int y, int cnt, int sum) {
		if(cnt == 4) {
			res = Math.max(res, sum);
			return;
		}
		
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(isInside(nx, ny) && !visit[nx][ny]) {
				visit[nx][ny] = true;
				tetro(nx, ny, cnt+1, sum+map[nx][ny]);
				visit[nx][ny] = false;
			}
		}
	}
}
