package solving.solve_0831;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//BOJ
public class 파이프_옮기기_1_17070 {

	static int N;
	static int[][] map;
	static long res = 0;

	private static boolean isInside(int x, int y) {
		if(x<=0 || y<=0 || x>N || y>N) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		move(1, 2, 1, 1, 0);
		System.out.println(res);
	}
	
	public static void move(int fx, int fy, int bx, int by, int dir) {
		if(fx == N && fy == N) {
			res++;
			return;
		}
		
		if(dir == 0) { //가로일때
			if(right(fx, fy)) {
				move(fx, fy+1, fx, fy, 0);
			}
			if(diag(fx, fy)) {
				move(fx+1, fy+1, fx, fy, 2);
			}
		}
		if(dir == 1) {
			if(down(fx, fy)) {
				move(fx+1, fy, fx, fy, 1);
			}
			if(diag(fx, fy)) {
				move(fx+1, fy+1, fx, fy, 2);
			}
		}
		if(dir == 2) {
			if(down(fx, fy)) {
				move(fx+1, fy, fx, fy, 1);
			}
			if(right(fx, fy)) {
				move(fx, fy+1, fx, fy, 0);
			}
			if(diag(fx, fy)) {
				move(fx+1, fy+1, fx, fy, 2);
			}
		}
	}
	
	//세로, 대각선
	public static boolean down(int x, int y) {
		int nx = x+1;
		int ny = y;
		if(isInside(nx, ny) && map[nx][ny] == 0) {
			return true;
		}
		return false;
	}
	
	//가로, 대각선
	public static boolean right(int x, int y) {
		int nx = x;
		int ny = y+1;
		if(isInside(nx, ny) && map[nx][ny] == 0) {
			return true;
		}
		return false;
	}
	
	//가로, 세로, 대각선
	public static boolean diag(int x, int y) {
		int nx = x+1;
		int ny = y+1;
		if(isInside(nx, ny)) {
			if(map[x+1][y] == 0 && map[x][y+1] == 0 && map[nx][ny] == 0) {
				return true;
			}
		}
		return false;
	}
}
