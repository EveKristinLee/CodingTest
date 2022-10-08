package solving.solve_0911;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 주사위_굴리기_14499 {

	static int N;
	static int M;
	static int sx, sy;
	static int K;
	static int[][] map;
	static int[] order;
	static int[] dice;
	static StringBuilder sb = new StringBuilder();
	
	
	static int[] dx = {0, 0, 0, -1, 1};
	static int[] dy = {0, 1, -1, 0, 0};
	
	private static boolean isInside(int x, int y) {
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
		sx = Integer.parseInt(st.nextToken());
		sy = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		order = new int[K];
		dice = new int[6];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++) {
			order[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<K; i++) {
			move(order[i]);
		}
		System.out.println(sb);
	}
	
	private static void move(int dir) {
		int nx = sx + dx[dir];
		int ny = sy + dy[dir];
		int tmp = -1;
		if(isInside(nx, ny)) {
			switch(dir) {
			case 1:
				tmp = dice[5];
				dice[5] = dice[2];
				dice[2] = dice[0];
				dice[0] = dice[3];
				dice[3] = tmp;
				break;
			case 2:
				tmp = dice[5];
				dice[5] = dice[3];
				dice[3] = dice[0];
				dice[0] = dice[2];
				dice[2] = tmp;
				break;
			case 3:
				tmp = dice[1];
				dice[1] = dice[0];
				dice[0] = dice[4];
				dice[4] = dice[5];
				dice[5] = tmp;
				break;
			case 4:
				tmp = dice[5];
				dice[5] = dice[4];
				dice[4] = dice[0];
				dice[0] = dice[1];
				dice[1] = tmp;
				break;
			}
			if(map[nx][ny] == 0) {
				map[nx][ny] = dice[5];
			}
			else {
				dice[5] = map[nx][ny];
				map[nx][ny] = 0;
			}
			sx = nx;
			sy = ny;
			sb.append(dice[0] + "\n");
		}
	}
}
