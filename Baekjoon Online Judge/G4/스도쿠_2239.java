package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 스도쿠_2239 {
	
	static int[][] map;
	static boolean flag = false;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[9][9];
		
		for(int i=0; i<9; i++) {
			String s = br.readLine();
			for(int j=0; j<9; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		
		dfs(0);
		
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}


	private static void dfs(int idx) {
		if(idx >= 81) {
			flag = true;
			return;
		}
		
		int x = idx / 9;
		int y = idx % 9;
		if(map[x][y] != 0) {
			dfs(idx+1);
		}
		else {
			for(int i=1; i<=9; i++) {
				if(isOk(x, y, i)) {
					map[x][y] = i;
					dfs(idx+1);
					if(flag) { //이미 완료
						return;
					}
					map[x][y] = 0;
				}
			}
		}
	}


	private static boolean isOk(int x, int y, int num) {
		for(int i=0; i<9; i++) {
			if(map[x][i] == num) { //가로 체크
				return false;
			}
			if(map[i][y] == num) { //세로 체크
				return false;
			}
		}
		
		int sx = x / 3 * 3; //네모칸 체크
		int sy = y / 3 * 3;
		for(int i=sx; i<sx+3; i++) {
			for(int j=sy; j<sy+3; j++) {
				if(map[i][j] == num) {
					return false;
				}
			}
		}
		return true;
	}

}
