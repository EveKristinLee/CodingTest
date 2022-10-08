package solving.solve_0925;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 사다리_조작_15684 {
	static int N;
	static int M;
	static int H;
	static int[][] map;
	static int res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H+1][N+1];
		res = -1;
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 1; //오른쪽으로
			map[a][b+1] = 2; //왼쪽으로
		}
		
		dfs(1, 0); //1번 가로줄부터 시작
		if(res == -1) {
			System.out.println("-1");
		}
		else {
			System.out.println(res);
		}
		
	}

	private static void dfs(int x, int cnt) {
		if(cnt >= 4) {
			return;
		}
		if(check()) {
			if(res != -1) {
				res = Math.min(res, cnt);				
			}
			else {
				res = cnt;
			}
			return;
		}
		
		for(int i=x; i<=H; i++) {
			for(int j=1; j<N; j++) {
				if(map[i][j] == 0 && map[i][j+1] == 0) {
					map[i][j] = 1;
					map[i][j+1] = 2;
					dfs(i, cnt+1);
					map[i][j] = 0;
					map[i][j+1] = 0;
				}
			}
		}
	}

	private static boolean check() {
		for(int i=1; i<=N; i++) {
			int x = 1;
			int y = i;
			for(int j=0; j<H; j++) {
				if(map[x][y] == 1) {
					y++;
				}
				else if(map[x][y] == 2) {
					y--;
				}
				x++;
			}
			if(y != i) {
				return false;
			}
		}
		return true;
	}
}
