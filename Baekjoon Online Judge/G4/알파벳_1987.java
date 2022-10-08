package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 알파벳_1987 {

	static int r; 
	static int c; 
	static char[][] board;
	static boolean[] alpha;
	static int maxCnt;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	private static boolean isInside(int x, int y) {
		if(x<0 || y<0 || x>=r || y>=c) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		board = new char[r][c];
		alpha = new boolean[27];
		maxCnt = 0;
		
		for(int i=0; i<r; i++) {
			String line = br.readLine();
			for(int j=0; j<c; j++) {
				board[i][j] = line.charAt(j);
			}
		}
		alpha[board[0][0] - 'A'] = true; 
		dfs(0, 0, 1);
		System.out.println(maxCnt);
	}

	private static void dfs(int x, int y, int cnt) {
		if(maxCnt < cnt) {
			maxCnt = cnt;
		}
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(isInside(nx, ny) && !alpha[board[nx][ny] - 'A']) {
				alpha[board[nx][ny] - 'A'] = true;
				dfs(nx, ny, cnt+1);
				alpha[board[nx][ny] - 'A'] = false;
			}
		}
	}
}
