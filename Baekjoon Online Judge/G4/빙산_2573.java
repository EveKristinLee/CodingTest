package solving.solve_1101;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//BOJ G4
public class 빙산_2573 {
	
	static int N; //세로
	static int M; //가로
	static int[][] map; 
	static int res;
	static boolean[][] visit;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
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
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		res = 0;
		boolean isZero = false;
		while(isNotSeperate()) {
			visit = new boolean[N][M];
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j] > 0  && !visit[i][j]) {
						melt(i, j);
					}
				}
			}
			res++;
			if(!isNotZero()) {
				isZero = true;
				break;
			}
		}
		if(isZero) {
			System.out.println(0);
		}else {
			System.out.println(res);
		}
	}
	
	//끝까지 분리되지 않고 빙산이 다 0이 되면 false
	private static boolean isNotZero() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] != 0) {
					return true;
				}
			}
		}
		return false;
	}

	private static void melt(int x, int y) {
		Queue<Integer[]> q = new ArrayDeque<>();
		q.offer(new Integer[] {x, y});
		visit[x][y] = true;
		
		while(!q.isEmpty()) {
			Integer[] top = q.poll();
			x = top[0];
			y = top[1];
			int cnt = 0;
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(isInside(nx, ny)) {
					if(!visit[nx][ny] && map[nx][ny] > 0) {
						visit[nx][ny] = true;
						q.offer(new Integer[] {nx, ny});
					}
					else if(map[nx][ny] == 0 && !visit[nx][ny]) {
						cnt++;
					}
				}
			}
			//해당 칸에서 얼음이 몇개인지
			if(cnt >= map[x][y]) {
				map[x][y] = 0;
			}else {
				map[x][y] -= cnt;
			}
		}
	}

	//분리되면 false, 한덩어리면 true
	private static boolean isNotSeperate() {
		boolean[][] tmp = new boolean[N][M];
		Queue<Integer[]> q = new ArrayDeque<>();
		boolean stop = false;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] > 0) {
					q.offer(new Integer[] {i, j});
					tmp[i][j] = true;
					stop = true;
					break;
				}
			}
			if(stop) {
				break;
			}
		}
		
		while(!q.isEmpty()) {
			Integer[] top = q.poll();
			int x = top[0];
			int y = top[1];
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(isInside(nx, ny)) {
					if(!tmp[nx][ny] && map[nx][ny] > 0) {
						tmp[nx][ny] = true;
						q.offer(new Integer[] {nx, ny});
					}
				}
 			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				//빙산이 있고 방문하지 않았으면..분리된 빙산 존재
				if(map[i][j] > 0 && !tmp[i][j]) {
					return false;
				}
			}
		}
		return true; //분리된 빙산 없음
	}

}
