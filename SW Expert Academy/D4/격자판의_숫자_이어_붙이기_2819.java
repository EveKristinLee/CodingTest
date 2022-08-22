package solving.solve_0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class 격자판의_숫자_이어_붙이기_2819 {

	static char[][] map;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static Set<String> set;
	
	private static boolean isInside(int x, int y) {
		if(x<0 || y<0 || x>=4 || y>=4) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			map = new char[4][4];
			set = new HashSet<>();
			for(int i=0; i<4; i++) {
				String line = br.readLine();
				for(int j=0; j<4; j++) {
					map[i][j] = line.charAt(j*2);
				}
			}
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					dfs(i, j, "");
				}
			}
			sb.append("#"+t+" "+set.size()).append("\n");
		}
		System.out.println(sb);
	}
	
	

	private static void dfs(int x, int y, String num) {
		if(num.length() == 7) {
			set.add(num);
			return;
		}
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(isInside(nx, ny)) {
				dfs(nx, ny, num + map[x][y]);
			}
		}
	} 

}
