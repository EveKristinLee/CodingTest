package solving.solve_0911;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 경사로_14890 {

	static int N;
	static int L;
	static int[][] map;
	static int res = 0;
	
	static boolean isInside(int x, int y) {
		if(x<0 || y<0 || x>=N || y>=N) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//가로 길 찾기
		for(int i=0; i<N; i++) {
			if(findRowPath(i)) {
				res++;
			}
			if(findColPath(i)) {
				res++;
			}
		}
		System.out.println(res);
	}

	private static boolean findRowPath(int row) {
		boolean[] chk = new boolean[N];
		int before = map[row][0];
		for(int i=1; i<N; i++) {
			if(Math.abs(before - map[row][i]) >= 2) { //오차가 2이상이면
				return false;
			}
			//다음이 한칸 낮을때
			if(before - map[row][i] == 1) {
				int tmp = map[row][i];
				for(int j=0; j<L; j++) {
					if(isInside(row, i+j)) {
						if(chk[i+j]) { //경사로 못놓을때
							return false;
						}
						if(tmp != map[row][i+j]) { //경사로 놓을 곳이 같은 높이가 아닐때
							return false;
						}
						chk[i+j] = true;
					}
					else {
						return false;
					}
				}
			}
			//다음칸이 한칸 높을때
			if(before - map[row][i] == -1) {
				int tmp = map[row][i-1];
				for(int j=1; j<=L; j++) {
					if(isInside(row, i-j)) {
						if(chk[i-j]) {
							return false;
						}
						if(tmp != map[row][i-j]) {
							return false;
						}
						chk[i-j] = true;
					}
					else {
						return false;
					}
				}
			}
			before = map[row][i];
		}
		return true;
	}
	
	private static boolean findColPath(int col) {
		boolean[] chk = new boolean[N];
		int before = map[0][col];
		for(int i=1; i<N; i++) {
			if(Math.abs(before - map[i][col]) >= 2) { //오차가 2이상이면
				return false;
			}
			//다음이 한칸 낮을때
			if(before - map[i][col] == 1) {
				int tmp = map[i][col];
				for(int j=0; j<L; j++) {
					if(isInside(i+j, col)) {
						if(chk[i+j]) { //경사로 못놓을때
							return false;
						}
						if(tmp != map[i+j][col]) { //경사로 놓을 곳이 같은 높이가 아닐때
							return false;
						}
						chk[i+j] = true;
					}
					else {
						return false;
					}
				}
			}
			//다음칸이 한칸 높을때
			if(before - map[i][col] == -1) {
				int tmp = map[i-1][col];
				for(int j=1; j<=L; j++) {
					if(isInside(i-j, col)) {
						if(chk[i-j]) {
							return false;
						}
						if(tmp != map[i-j][col]) {
							return false;
						}
						chk[i-j] = true;
					}
					else {
						return false;
					}
				}
			}
			before = map[i][col];
		}
		return true;
	}
}
