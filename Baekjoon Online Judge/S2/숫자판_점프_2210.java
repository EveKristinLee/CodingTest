package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 숫자판_점프_2210 {

	static int[][] map;
	static Set<String> num;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static boolean isInside(int x, int y) {
		return x>=0 && y>=0 && x<5 && y<5;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[5][5];
		num = new HashSet<>();
		
		for(int i=0; i<5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<5; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				makeNum(i, j, 0, "");
			}
		}
		System.out.println(num.size());
	}
	
	private static void makeNum(int x, int y, int cnt, String now) {
		if(cnt == 6) {
			num.add(now);
			return;
		}
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(isInside(nx, ny)) {
				makeNum(nx, ny, cnt+1, now + map[x][y]+"");
			}
		}
	}
}
