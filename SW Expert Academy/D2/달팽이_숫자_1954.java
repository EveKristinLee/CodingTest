package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 달팽이_숫자_1954 {
	
	static int n;
	
	static boolean isInside(int x, int y) {
		return (x >= 0) && (y >= 0) && (x < n) && (y < n);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		int[] dx = {0, 1, 0, -1};
		int[] dy = {1, 0, -1, 0}; //우 -> 하 -> 좌 -> 상
		for(int t=1; t<=T; t++) {
			n = Integer.parseInt(br.readLine());
			int[][] map = new int[n][n];
			int x = 0;
			int y = 0;
			int now = 1;
			int dir = 0; //0 : 우, 1 : 하, 2 : 좌, 3 : 상
			while(now <= n * n) {
				map[x][y] = now++;
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				if(!isInside(nx, ny) || map[nx][ny] != 0) {
					dir = (dir + 1) % 4;
					x += dx[dir];
					y += dy[dir];
				}
				else {
					x = nx;
					y = ny;
				}
			}
			
			System.out.println("#" + t);
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
		}
	}

}
