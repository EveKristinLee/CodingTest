package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 배열_돌리기_1_16926 {

	static int n, m, r;
	static int[][] map;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0}; //우 -> 하 -> 좌 -> 상
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt = Math.min(n, m)/2; //돌려야하는 줄 수
		while(r-- > 0) { //돌리는 횟수
			turn(cnt);
		}
	
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}


	private static void turn(int cnt) {
		for(int i=0; i<cnt; i++) {
			int dir = 0; //0 : 우, 1 : 하, 2 : 좌, 3 : 상
			int x = i;
			int y = i;
			int tmp = map[x][y];
			while(dir < 4) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				if(nx >= i && ny >= i && nx < n-i && ny < m-i) { //좌표가 범위 내에 있는지 확인해주고
					map[x][y] = map[nx][ny]; //map00 = map01, map01 = map02
					x = nx;
					y = ny;
				}
				else {
					dir++; //범위를 벗어나면 방향 바꿔주기
				}
			}
			map[i+1][i] = tmp;
		}
	}

}
