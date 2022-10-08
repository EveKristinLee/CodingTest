package solving.solve_1007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 로봇_청소기_14503 {

	static int N;
	static int M;
	static int[][] map;
	static int dir; //0:위, 1:오, 2:아래, 3:왼
	static int sx, sy;
	static int resCnt;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1}; //위, 오, 아, 왼
	
	static boolean isInside(int x, int y) {
		if(x<0 || y<0 || x>=N || y>=M) {
			return false;
		}
		return true;
	}
	
	public static int turn(int dir) {
		int ret = -1;
		if(dir == 0) {
			ret = 3;
		}else if(dir == 1) {
			ret = 0;
		}else if(dir == 2) {
			ret = 1;
		}else if(dir == 3) {
			ret = 2;
		}
		return ret;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		sx = Integer.parseInt(st.nextToken());
		sy = Integer.parseInt(st.nextToken());
		dir = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		clean();
		System.out.println(resCnt);
	}

	private static void clean() {

		resCnt = 1; //현재칸 청소
		map[sx][sy] = 2;
		
		int nx = 0, ny = 0, nd = 0;
		while(true) {
			int ori_d = dir;
			boolean can = false;
			int cant = 0;
			
			for(int i=0; i<4; i++) {
				nd = turn(dir);
				nx = sx + dx[nd];
				ny = sy + dy[nd];
				if(isInside(nx, ny)) {
					if(map[nx][ny] == 0) {
						can = true;
						break;
					}
					else if(map[nx][ny] == 2 || map[nx][ny] == 1) {
						dir = nd;
						cant++;
					}
				}
				else {
					dir = nd;
					cant++;
				}
			}
			
			if(can) {
				map[nx][ny] = 2;
				resCnt++;
				dir = nd;
			}
			
			if(cant == 4) {
				nx = sx - dx[ori_d];
				ny = sy - dy[ori_d];
				dir = ori_d;
				if(!isInside(nx, ny) || map[nx][ny] == 1) { //벽
					break; //작동 끝
				}
			}
			
			sx = nx;
			sy = ny;
		}
	}
}
